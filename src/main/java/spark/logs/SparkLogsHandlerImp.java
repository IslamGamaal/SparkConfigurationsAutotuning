package spark.logs;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SparkLogsHandlerImp implements SparkLogsHandler {
    public String getLatestAppPhysicalPlan() {
        return readFromFile("resources/physical.txt"); //TODO pass the file path of the logical plan
    }

    public String getLatestAppOptimizedLogicalPlan() {
        return readFromFile("resources/logical.txt"); //TODO pass the file path of the logical plan
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
