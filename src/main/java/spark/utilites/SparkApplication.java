package spark.utilites;

import java.util.Map;

public class SparkApplication {
    private String optimizedQueryPlan = "";
    private String physicalPlan = "";
    private String stagesJson = "";
    private long lastRunTime;
    private Map<String , String> lastRunActualConfigurations;
    private String name;
    private String JarPathWithParamsCommand;
    private String mainClassName;

    public String getOptimizedQueryPlan() {
        return optimizedQueryPlan;
    }

    public void setOptimizedQueryPlan(String optimizedQueryPlan) {
        this.optimizedQueryPlan = optimizedQueryPlan;
    }

    public String getPhysicalPlan() {
        return physicalPlan;
    }

    public void setPhysicalPlan(String physicalPlan) {
        this.physicalPlan = physicalPlan;
    }

    public String getStagesJson() {
        return stagesJson;
    }

    public void setStagesJson(String stagesJson) {
        this.stagesJson = stagesJson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJarPathWithParamsCommand() {
        return JarPathWithParamsCommand;
    }

    public void setJarPathWithParamsCommand(String jarPathWithParamsCommand) {
        JarPathWithParamsCommand = jarPathWithParamsCommand;
    }

    public long getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(long lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public String getMainClassName() {
        return mainClassName;
    }

    public void setMainClassName(String mainClassName) {
        this.mainClassName = mainClassName;
    }

    public Map<String, String> getLastRunActualConfigurations() {
        return lastRunActualConfigurations;
    }

    public void setLastRunActualConfigurations(Map<String, String> lastRunActualConfigurations) {
        this.lastRunActualConfigurations = lastRunActualConfigurations;
    }
}
