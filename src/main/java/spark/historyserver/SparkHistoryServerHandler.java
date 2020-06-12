package spark.historyserver;

import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

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
     * @return Environment: the optimized logical plan for the latest application.
     **/
    Environment getLatestAppEnvironmentSettings();

    /**
     * This functions extracts the Executor settings for the latest
     * submitted application from spark history server logs.
     *
     * @return Executor: the optimized logical plan for the latest application.
     **/
    Executor[] getLatestAppExecutorSettings();
}
