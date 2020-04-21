package spark.historyserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.historyserver.model.*;
import spark.historyserver.report.Report;
import spark.historyserver.report.Summary;

public class HistoryClient {
    
    private static final String APPLICATIONS_API            = "http://%s/api/v1/applications?status=completed&limit=%s&minDate=%s&maxDate=%s"; 
    private static final String APPLICATION_EXECUTORS_API   = "http://%s/api/v1/applications/%s/executors";
    private static final String APPLICATION_JOBS_API        = "http://%s/api/v1/applications/%s/jobs";
    private static final String APPLICATION_STAGES_API      = "http://%s/api/v1/applications/%s/stages";
    private static final String APPLICATION_ENVIRONMENT_API        = "http://%s/api/v1/applications/%s/environment";

    private static InputStream request(DefaultHttpClient httpClient, String url) throws ClientProtocolException, IOException {
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        return response.getEntity().getContent();
    }
    
    private static String read(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            output.append(line);
        }
        return output.toString();
    }
    
    private static <O, T> T aggregate(O[] arr, Function<O, T> e, BiFunction<T, T, T> acc) {
        T total = null;
        for(O o: arr) {
            T v = e.apply(o);
            total = total == null ? v : acc.apply(total, v);
        }
        return total;
    }
    
    private static void info(String message) {
        System.out.println(message);
    }

    private static void error(String message, Throwable exception) {
        System.err.println(message);
        exception.printStackTrace();
    }
    
    private static void process(String host, int limit, String filter, Date from, Date to, String outputPath) throws ClientProtocolException, IOException {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String minDate = formatter.format(from);
        String maxDate = formatter.format(to);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (limit == Integer.MAX_VALUE)
                info(String.format("Connecting to %s without limit", host));
            else
                info(String.format("Connecting to %s with limit %s", host, limit));
            try (InputStream appInputStream = request(httpClient, String.format(APPLICATIONS_API, host, limit, minDate, maxDate))) {
                Application[] history = objectMapper.readValue(read(appInputStream).getBytes(), Application[].class);
                Stream<Application> applications = Arrays.stream(history);
                if (filter == null) {
                    info(String.format("Found %s applications between %s and %s", history.length, minDate, maxDate));
                } else {
                    long matched = applications.filter(application -> application.getName().contains(filter)).count();
                    applications = Arrays.stream(history).filter(application -> application.getName().contains(filter));
                    info(String.format("Found %s applications between %s and %s with filter '%s'", matched, minDate, maxDate, filter)); 
                }
                Report report = new Report();
                applications.map(application -> {
                    try {
                        Summary summary = new Summary();
                        summary.setApplicationName(application.getName());
                        try (InputStream inputStream = request(httpClient, String.format(APPLICATION_ENVIRONMENT_API, host, application.getId()))) {
                            Environment env = objectMapper.readValue(read(inputStream).getBytes(), Environment.class);
                            Arrays.sort(env.getSparkProperties(), Comparator.comparing(p -> p[0]));
                            for(String[] sparkProp: env.getSparkProperties()) {
                                summary.addSparkProperty(sparkProp[0], sparkProp[1]);
                            }
                        }
                        summary.setAttemptsCount(application.getAttempts().length);
                        summary.setDuration(application.getAttempts()[application.getAttempts().length - 1].getDuration());
                        try (InputStream inputStream = request(httpClient, String.format(APPLICATION_JOBS_API, host, application.getId()))) {
                            Job[] jobs = objectMapper.readValue(read(inputStream).getBytes(), Job[].class);
                            summary.setJobsCount(jobs.length);
                            summary.setTasksPerJob(aggregate(jobs, j -> String.valueOf(j.getNumTasks()), (v1, v2) -> v1 + " + " + v2));
                        }
                        try(InputStream inputStream = request(httpClient, String.format(APPLICATION_STAGES_API, host, application.getId()))) {
                            Stage[] stages = objectMapper.readValue(read(inputStream).getBytes(), Stage[].class);
                            summary.setStagesCount(stages.length);
                            summary.setStagesInputBytes(aggregate(stages, s -> s.getInputBytes(), Math::addExact));
                            summary.setStagesInputRecords(aggregate(stages, s -> s.getInputRecords(), Math::addExact));
                            summary.setStagesOutputBytes(aggregate(stages, s -> s.getOutputBytes(), Math::addExact));
                            summary.setStagesOutputRecords(aggregate(stages, s -> s.getOutputRecords(), Math::addExact));
                            summary.setStagesShuffleReadBytes(aggregate(stages, s -> s.getShuffleReadBytes(), Math::addExact));
                            summary.setStagesShuffleReadRecords(aggregate(stages, s -> s.getShuffleReadRecords(), Math::addExact));
                            summary.setStagesShuffleWriteBytes(aggregate(stages, s -> s.getShuffleWriteBytes(), Math::addExact));
                            summary.setStagesShuffleWriteRecords(aggregate(stages, s -> s.getShuffleWriteRecords(), Math::addExact));
                            summary.setStagesMemorySpill(aggregate(stages, s -> s.getMemoryBytesSpilled(), Math::addExact));
                            summary.setStagesDiskSpill(aggregate(stages, s -> s.getDiskBytesSpilled(), Math::addExact));
                        }
                        try (InputStream inputStream = request(httpClient, String.format(APPLICATION_EXECUTORS_API, host, application.getId()))) {
                            Executor[] vms = objectMapper.readValue(read(inputStream).getBytes(), Executor[].class);
                            Executor[] executors = Arrays.stream(vms).filter(e -> !e.getId().equals("driver")).toArray(Executor[]::new);
                            summary.setExecutorsCount(executors.length);
                            summary.setCoresPerExecutor(aggregate(executors, e -> e.getTotalCores(), Math::addExact) / executors.length);
                            summary.setMaxMemoryPerExecutor(aggregate(executors, e -> e.getMaxMemory(), Math::max));
                            summary.setExecutorsProcessingTime(aggregate(executors, e -> e.getTotalDuration(), Math::addExact));
                            summary.setExecutorsGCTime(aggregate(executors, e -> e.getTotalGCTime(), Math::addExact));
                            summary.setExecutorsInputBytes(aggregate(executors, e -> e.getTotalInputBytes(), Math::addExact));
                            summary.setTasksCount(aggregate(executors, e -> e.getTotalTasks(), Math::addExact));
                            summary.setExecutorsFailedTasks(aggregate(executors, e -> e.getFailedTasks(), Math::addExact));
                            summary.setExecutorsShuffleReadBytes(aggregate(executors, e -> e.getTotalShuffleRead(), Math::addExact));
                            summary.setExecutorsShuffleWriteBytes(aggregate(executors, e -> e.getTotalShuffleWrite(), Math::addExact));
                        }
                        return summary;
                    } catch (Exception exception) {
                        error("Failed to parse " + application.getId(), exception);
                        return null;
                    }
                })
                  .forEach(summary -> {
                      info(summary.toString());
                      report.addRow(summary);
                  });
                report.save(outputPath);
            }
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
    
    public static void main(String[] args) {
        final String host = "localhost:18080";
        final int limit = Integer.MAX_VALUE;
        final String filter = "AE_LINES_";
        Calendar calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        Date from = calendar.getTime();
        String outputPath = String.format("history_%s_%s_%s.csv", filter, from.getTime(), to.getTime());
        try {
            process(host, limit, filter, from, to, outputPath);
        } catch (IOException e) {
            error("Failed to connect to History Server", e);
        }
    }
}
