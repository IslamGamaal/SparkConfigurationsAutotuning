package spark.historyserver;

public interface SparkHistoryServerHandler {
    /**
     * This function calls the suitable API to extract the
     * latest application logs from the history server.
     *
     * @return String: the API response containing the JSON for Application Stages logs.
     **/
    String getLatestAppStagesJson();
}
