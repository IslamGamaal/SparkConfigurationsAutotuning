package spark.historyserver;

import spark.historyserver.model.Environment;

import java.util.Map;

public interface SparkHistoryServerHandler {
    /**
     * This function calls the suitable API to extract the
     * latest application logs from the history server.
     *
     * @return String: the API response containing the JSON for Application Stages logs.
     **/
    String getLatestAppStagesJson();

    /**
     * This functions extracts the Environment settings for the latest
     * submitted application from spark history server logs.
     *
     * @return Environment: the Environment settings.
     **/
    Environment getLatestAppEnvironmentSettings();

    /**
     * This functions extracts the Executor settings for the latest
     * submitted application from spark history server logs.
     *
     * @return Map<String, String>: the Executor settings.
     **/
    Map<String, String> getLatestAppExecutorSettings();

    /**
     * This functions extracts the Executor settings for the latest
     * submitted application from spark history server logs.
     *
     * @return Map<String, String>: the Executor settings.
     **/
    Long getLatestAppDuration();
}
