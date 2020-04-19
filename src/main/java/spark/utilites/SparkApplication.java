package spark.utilites;

public class SparkApplication {
    private String optimizedQueryPlan = "";
    private String physicalPlan = "";
    private String stagesJson = "";

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
