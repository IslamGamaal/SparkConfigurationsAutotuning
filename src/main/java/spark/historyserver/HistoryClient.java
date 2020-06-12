package spark.historyserver;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    private static final String APPLICATIONS_API            = "http://%s/api/v1/applications?status=completed";
    private static final String APPLICATION_EXECUTORS_API   = "http://%s/api/v1/applications/%s/executors";
    private static final String APPLICATION_JOBS_API        = "http://%s/api/v1/applications/%s/jobs";
    private static final String APPLICATION_STAGES_API      = "http://%s/api/v1/applications/%s/stages";
    private static final String APPLICATION_ENVIRONMENT_API = "http://%s/api/v1/applications/%s/environment";

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

    private static String convertInputStreamToString(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
    }

    private static void writeStream(InputStream inputStream, String targetFile) {
        try {
            String inputStreamString = convertInputStreamToString(inputStream);
            BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
            writer.write(inputStreamString);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static InputStream clone(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private static void process(String host, int limit, String outputPath) throws ClientProtocolException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (limit == Integer.MAX_VALUE)
                info(String.format("Connecting to %s without limit", host));
            else
                info(String.format("Connecting to %s with limit %s", host, limit));
            try (InputStream appInputStream = request(httpClient, String.format(APPLICATIONS_API, host, limit))) {
                Application[] history = objectMapper.readValue(read(appInputStream).getBytes(), Application[].class);
                List<Application> applications = Collections.singletonList(history[0]);
                Report report = new Report();
                applications.stream().forEach(application -> {
                    try {
                        try (InputStream inputStream = request(httpClient, String.format(APPLICATION_ENVIRONMENT_API, host, application.getId()))) {
                            String targetFilePath = "resources/environment.json";
                            writeStream(inputStream, targetFilePath);
                        }
                        try (InputStream inputStream = request(httpClient, String.format(APPLICATION_JOBS_API, host, application.getId()))) {
                            String targetFilePath = "resources/jobs.json";
                            writeStream(inputStream, targetFilePath);
                        }
                        try(InputStream inputStream = request(httpClient, String.format(APPLICATION_STAGES_API, host, application.getId()))) {
                            String targetFilePath = "resources/stages.json";
                            writeStream(inputStream, targetFilePath);
                        }
                        try (InputStream inputStream = request(httpClient, String.format(APPLICATION_EXECUTORS_API, host, application.getId()))) {
                            String targetFilePath = "resources/executors.json";
                            writeStream(inputStream, targetFilePath);
                        }
                    } catch (Exception exception) {
                        error("Failed to parse " + application.getId(), exception);
                    }
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
        final String filter = null;
        Calendar calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -100);
        Date from = calendar.getTime();
        String outputPath = String.format("history_%s_%s_%s.csv", filter, from.getTime(), to.getTime());
        try {
            process(host, limit, outputPath);
        } catch (IOException e) {
            error("Failed to connect to History Server", e);
        }
    }
}
