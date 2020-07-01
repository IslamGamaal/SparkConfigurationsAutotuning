package spark.submission;

import spark.utilites.SparkApplication;
import spark.utilites.SparkRunInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SparkSubmitterImp implements SparkSubmitter {
    public int submitApplication(SparkApplication sparkApplication , SparkRunInfo sparkRunInfo) {
        String sparkSubmitCommand = createSparkSubmitCommand(sparkApplication , sparkRunInfo);
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash" , "-c" , sparkRunInfo.getSparkDirectory() + "/bin/" + sparkSubmitCommand);
        try {
            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            return exitCode;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private String createSparkSubmitCommand(SparkApplication sparkApplication, SparkRunInfo sparkRunInfo) {
        StringBuilder sparkSubmitCommand = new StringBuilder();
        sparkSubmitCommand.append("sudo spark-submit --name \"")
                .append(sparkApplication.getName())
                .append("\" ")
                .append("--class ")
                .append(sparkApplication.getMainClassName())
                .append(" --master ")
                .append(sparkRunInfo.getSparkMaster())
                .append("--deploy-mode cluster")
                .append(" ");
        if (sparkRunInfo.getConfigsFilePath() != null && sparkRunInfo.getConfigsFilePath().length() != 0) {
            sparkSubmitCommand.append("--properties-file ").append(sparkRunInfo.getConfigsFilePath()).append(" ");
        }

        sparkSubmitCommand.append(sparkApplication.getJarPathWithParamsCommand());
        return sparkSubmitCommand.toString();
    }


}
