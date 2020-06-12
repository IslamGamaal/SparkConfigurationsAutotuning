package spark.historyserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
    public Executor[] getLatestAppExecutorSettings() {
        ObjectMapper mapper = new ObjectMapper();
        String executorJson = readFromFile("resources/executors.json");
        Executor[] executorList = null;
        try {
            executorList = mapper.readValue(executorJson, Executor[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return executorList;
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
