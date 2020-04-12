package spark.logs;

public interface SparkLogsHandler {
    /**
     * This functions extracts the physical plan for the latest submitted
     * application from spark logs.
     *
     * @return String: the physical plan for the latest application.
     **/
    String getLatestAppPhyicalPlan();

    /**
     * This functions extracts the optimized logical plan for the latest
     * submitted application from spark logs.
     *
     * @return String: the optimized logical plan for the latest application.
     **/
    String getLatestAppOptimizedLogicalPlan();
}
