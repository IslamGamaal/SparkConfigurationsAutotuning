package spark.utilites;

public class SparkApplication {
    private String optimizedQueryPlan = "";
    private String physicalPlan = "";
    private String stagesJson = "";

    public long getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(long lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    private long lastRunTime;

    public String getOptimizedQueryPlan() {
        return optimizedQueryPlan;
    }

    public String getPhysicalPlan() {
        return physicalPlan;
    }

    public String getStagesJson() {
        return stagesJson;
    }
}
