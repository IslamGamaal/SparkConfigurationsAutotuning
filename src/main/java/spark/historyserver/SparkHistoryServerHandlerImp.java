package spark.historyserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.historyserver.model.Application;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.jfree.util.Log.error;

public class SparkHistoryServerHandlerImp implements SparkHistoryServerHandler {
    public String getLatestAppStagesJson() {
        return readFromFile("resources/stages.json");
    }

    @Override
    public Environment getLatestAppEnvironmentSettings() {
        ObjectMapper mapper = new ObjectMapper();
        String environmentJson = readFromFile("resources/environment.json");
        Environment environment = new Environment();
        try {
            environment = mapper.readValue(environmentJson, Environment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return environment;
    }

    @Override
    public Map<String, String> getLatestAppExecutorSettings() {
        ObjectMapper mapper = new ObjectMapper();
        String executorJson = readFromFile("resources/executors.json");
        Executor[] executorList = null;
        try {
            executorList = mapper.readValue(executorJson, Executor[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return setExecutorSettings(executorList);
    }


    private Map<String, String> setExecutorSettings(Executor[] executors) {
        Map<String, String> executorSettings = new HashMap<>();
        Long totalCores = 0L;
        for (Executor executor : executors) {
            if (!executor.getId().equals("driver") && totalCores < executor.getTotalCores()) {
                totalCores = executor.getTotalCores();
            }
        }
        Long maxMemory = 0L;
        for (Executor executor : executors) {
            if(!executor.getId().equalsIgnoreCase("driver"))
                maxMemory += executor.getMaxMemory();

        }
        executorSettings.put("spark.executor.cores", String.valueOf(totalCores));
        executorSettings.put("MaxMemory", String.valueOf(maxMemory));
        return executorSettings;
    }

    @Override
    public Long getLatestAppDuration() {
        ObjectMapper mapper = new ObjectMapper();
        String applicationsJson = readFromFile("resources/applications.json");
        Application[] applicationsList = null;
        try {
            applicationsList = mapper.readValue(applicationsJson, Application[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return applicationsList[0].getAttempts()[applicationsList[0].getAttempts().length-1].getDuration();
    }

    @Override
    public void runHistoryServerClient() {
        HistoryClient historyClient = new HistoryClient();
        final String host = "localhost:18080";
        final int limit = Integer.MAX_VALUE;
        final String filter = null;
        Calendar calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, -100);
        Date from = calendar.getTime();
        String outputPath = String.format("history_%s_%s_%s.csv", filter, from.getTime(), to.getTime());
        try {
            HistoryClient.process(host, limit, outputPath);
        } catch (IOException e) {
            HistoryClient.error("Failed to connect to History Server", e);
        }
    }

    private String readFromFile(String filePath) {
        StringBuilder fileContents = new StringBuilder();
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                fileContents.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileContents.toString();
    }
}
