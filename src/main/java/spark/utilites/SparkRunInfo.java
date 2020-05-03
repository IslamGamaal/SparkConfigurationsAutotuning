package spark.utilites;

public class SparkRunInfo {
    private String sparkDirectory;
    private String sparkMaster;
    private String configsFilePath;

    public String getSparkDirectory() {
        return sparkDirectory;
    }

    public void setSparkDirectory(String sparkDirectory) {
        this.sparkDirectory = sparkDirectory;
    }

    public String getSparkMaster() {
        return sparkMaster;
    }

    public void setSparkMaster(String sparkMaster) {
        this.sparkMaster = sparkMaster;
    }

    public String getConfigsFilePath() {
        return configsFilePath;
    }

    public void setConfigsFilePath(String configsFilePath) {
        this.configsFilePath = configsFilePath;
    }
}
