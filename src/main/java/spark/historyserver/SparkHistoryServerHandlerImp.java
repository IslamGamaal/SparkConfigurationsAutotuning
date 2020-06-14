package spark.historyserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.historyserver.model.Application;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
            if (!executor.getId().equals("driver")) {
                maxMemory += executor.getMaxMemory();
            }
        }
        executorSettings.put("TotalCores", String.valueOf(totalCores));
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
