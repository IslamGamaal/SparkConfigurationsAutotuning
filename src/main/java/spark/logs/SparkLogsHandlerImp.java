package spark.logs;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

import java.io.*;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataOutputStream;


public class SparkLogsHandlerImp implements SparkLogsHandler {
    public String getLatestAppPhysicalPlan() {
        return readFromFile("resources/physical.txt"); //TODO pass the file path of the logical plan
    }

    public String getLatestAppOptimizedLogicalPlan() {
        return readFromFile("resources/logical.txt"); //TODO pass the file path of the logical plan
    }

    private String readFromFile(String filePath) {
        filePath ="hdfs://172.31.3.0:8020"+"/" + filePath;
        StringBuilder fileContents = new StringBuilder();
        FileSystem fs = null;
        try {
            fs = FileSystem.get(new Configuration());
            assert fs != null;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(fs.open(new Path(filePath))));
            String line ;
            while ((line = bufferedReader.readLine()) != null) {
                fileContents.append(line).append('\n');
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContents.toString();
    }
}
