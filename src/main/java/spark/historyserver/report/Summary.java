package spark.historyserver.report;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Summary {

    // Application Info
    private String applicationName;
    private Map<String, String> sparkProperties = new LinkedHashMap<>();
    private Long duration;
    // Statistics
    private Integer attemptsCount;
    private Integer jobsCount;
    private Long tasksCount;
    private Integer stagesCount;
    private Integer executorsCount;
    private String tasksPerJob;
    // Stages
    private Long stagesInputBytes;
    private Long stagesInputRecords;
    private Long stagesOutputBytes;
    private Long stagesOutputRecords;
    private Long stagesShuffleReadBytes;
    private Long stagesShuffleReadRecords;
    private Long stagesShuffleWriteBytes;
    private Long stagesShuffleWriteRecords;
    private Long stagesMemorySpill;
    private Long stagesDiskSpill;
    // Executors
    private Long coresPerExecutor;
    private Long maxMemoryPerExecutor;
    private Long executorsProcessingTime;
    private Long executorsGCTime;
    private Long executorsInputBytes;
    private Long executorsShuffleReadBytes;
    private Long executorsShuffleWriteBytes;
    private Long executorsFailedTasks;
    
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Map<String, String> getSparkProperties() {
        return sparkProperties;
    }

    public void addSparkProperty(String key, String value) {
        this.sparkProperties.put(key, value);
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
    
    public Integer getAttemptsCount() {
        return attemptsCount;
    }

    public void setAttemptsCount(Integer attemptsCount) {
        this.attemptsCount = attemptsCount;
    }

    public Integer getJobsCount() {
        return jobsCount;
    }

    public void setJobsCount(Integer jobsCount) {
        this.jobsCount = jobsCount;
    }

    public Long getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(Long tasksCount) {
        this.tasksCount = tasksCount;
    }

    public Integer getStagesCount() {
        return stagesCount;
    }

    public void setStagesCount(Integer stagesCount) {
        this.stagesCount = stagesCount;
    }

    public Integer getExecutorsCount() {
        return executorsCount;
    }

    public void setExecutorsCount(Integer executorsCount) {
        this.executorsCount = executorsCount;
    }

    public String getTasksPerJob() {
        return tasksPerJob;
    }

    public void setTasksPerJob(String tasksPerJob) {
        this.tasksPerJob = tasksPerJob;
    }

    public void setSparkProperties(Map<String, String> sparkProperties) {
        this.sparkProperties = sparkProperties;
    }

    public Long getStagesInputBytes() {
        return stagesInputBytes;
    }

    public void setStagesInputBytes(Long stagesInputBytes) {
        this.stagesInputBytes = stagesInputBytes;
    }

    public Long getStagesInputRecords() {
        return stagesInputRecords;
    }

    public void setStagesInputRecords(Long stagesInputRecords) {
        this.stagesInputRecords = stagesInputRecords;
    }

    public Long getStagesOutputBytes() {
        return stagesOutputBytes;
    }

    public void setStagesOutputBytes(Long stagesOutputBytes) {
        this.stagesOutputBytes = stagesOutputBytes;
    }

    public Long getStagesOutputRecords() {
        return stagesOutputRecords;
    }

    public void setStagesOutputRecords(Long stagesOutputRecords) {
        this.stagesOutputRecords = stagesOutputRecords;
    }

    public Long getStagesShuffleReadBytes() {
        return stagesShuffleReadBytes;
    }

    public void setStagesShuffleReadBytes(Long stagesShuffleReadBytes) {
        this.stagesShuffleReadBytes = stagesShuffleReadBytes;
    }

    public Long getStagesShuffleReadRecords() {
        return stagesShuffleReadRecords;
    }

    public void setStagesShuffleReadRecords(Long stagesShuffleReadRecords) {
        this.stagesShuffleReadRecords = stagesShuffleReadRecords;
    }

    public Long getStagesShuffleWriteBytes() {
        return stagesShuffleWriteBytes;
    }

    public void setStagesShuffleWriteBytes(Long stagesShuffleWriteBytes) {
        this.stagesShuffleWriteBytes = stagesShuffleWriteBytes;
    }

    public Long getStagesShuffleWriteRecords() {
        return stagesShuffleWriteRecords;
    }

    public void setStagesShuffleWriteRecords(Long stagesShuffleWriteRecords) {
        this.stagesShuffleWriteRecords = stagesShuffleWriteRecords;
    }

    public Long getStagesMemorySpill() {
        return stagesMemorySpill;
    }

    public void setStagesMemorySpill(Long stagesMemorySpill) {
        this.stagesMemorySpill = stagesMemorySpill;
    }

    public Long getStagesDiskSpill() {
        return stagesDiskSpill;
    }

    public void setStagesDiskSpill(Long stagesDiskSpill) {
        this.stagesDiskSpill = stagesDiskSpill;
    }

    public Long getCoresPerExecutor() {
        return coresPerExecutor;
    }

    public void setCoresPerExecutor(Long coresPerExecutor) {
        this.coresPerExecutor = coresPerExecutor;
    }

    public Long getMaxMemoryPerExecutor() {
        return maxMemoryPerExecutor;
    }

    public void setMaxMemoryPerExecutor(Long maxMemoryPerExecutor) {
        this.maxMemoryPerExecutor = maxMemoryPerExecutor;
    }

    public Long getExecutorsProcessingTime() {
        return executorsProcessingTime;
    }

    public void setExecutorsProcessingTime(Long executorsProcessingTime) {
        this.executorsProcessingTime = executorsProcessingTime;
    }

    public Long getExecutorsGCTime() {
        return executorsGCTime;
    }

    public void setExecutorsGCTime(Long executorsGCTime) {
        this.executorsGCTime = executorsGCTime;
    }

    public Long getExecutorsInputBytes() {
        return executorsInputBytes;
    }

    public void setExecutorsInputBytes(Long executorsInputBytes) {
        this.executorsInputBytes = executorsInputBytes;
    }

    public Long getExecutorsShuffleReadBytes() {
        return executorsShuffleReadBytes;
    }

    public void setExecutorsShuffleReadBytes(Long executorsShuffleReadBytes) {
        this.executorsShuffleReadBytes = executorsShuffleReadBytes;
    }

    public Long getExecutorsShuffleWriteBytes() {
        return executorsShuffleWriteBytes;
    }

    public void setExecutorsShuffleWriteBytes(Long executorsShuffleWriteBytes) {
        this.executorsShuffleWriteBytes = executorsShuffleWriteBytes;
    }

    public Long getExecutorsFailedTasks() {
        return executorsFailedTasks;
    }

    public void setExecutorsFailedTasks(Long executorsFailedTasks) {
        this.executorsFailedTasks = executorsFailedTasks;
    }
    
    private static long BYTES_PER_GIGA = 1024 * 1024 * 1024;
    private static long MILLI_PER_MINUTE = 1000 * 60;

    private static float bytes2GB(Long bytes) {  return (float)bytes / BYTES_PER_GIGA; }
    
    private static float milli2Min(Long millis) {  return (float)millis / MILLI_PER_MINUTE; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n").append("________________________________________________________________________________________");
        builder.append("\n").append(String.format("Application:                   %50s", getApplicationName()));
        builder.append("\n").append("----------------------------------------------------------------------------------------");
        for(Entry<String, String> sparkProp: getSparkProperties().entrySet()) {
            builder.append("\n").append(String.format("\t%s = %s", sparkProp.getKey(), sparkProp.getValue()));
        }
        builder.append("\n").append("----------------------------------------------------------------------------------------");
        builder.append("\n").append(String.format("Attemps:                       %,50d", getAttemptsCount()));
        builder.append("\n").append(String.format("Duration:                      %50.2f", milli2Min(getDuration())));
        builder.append("\n");
        builder.append("\n").append(String.format("Executors:                     %,50d", getExecutorsCount()));
        builder.append("\n").append(String.format("Jobs:                          %,50d", getJobsCount()));
        builder.append("\n").append(String.format("Tasks:                         %50s", getTasksCount() + "(" + getTasksPerJob() + ")"));
        builder.append("\n").append(String.format("Stages:                        %,50d", getStagesCount()));
        builder.append("\n");
        builder.append("\n").append(String.format("Stage Input (GB):              %50.2f", bytes2GB(getStagesInputBytes())));
        builder.append("\n").append(String.format("Stage Input (Rows):            %,50d", getStagesInputRecords()));
        builder.append("\n").append(String.format("Stage Output (GB):             %50.2f", bytes2GB(getStagesOutputBytes())));
        builder.append("\n").append(String.format("Stage Output (Rows):           %,50d", getStagesOutputRecords()));
        builder.append("\n").append(String.format("Stage Shuffle Read (GB):       %50.2f", bytes2GB(getStagesShuffleReadBytes())));
        builder.append("\n").append(String.format("Stage Shuffle Read (Rows):     %,50d", getStagesShuffleReadRecords()));
        builder.append("\n").append(String.format("Stage Shuffle Write (GB):      %50.2f", bytes2GB(getStagesShuffleWriteBytes())));
        builder.append("\n").append(String.format("Stage Shuffle Write (Rows):    %,50d", getStagesShuffleWriteRecords()));
        builder.append("\n").append(String.format("Stage Memory Spill (GB):       %50.2f", bytes2GB(getStagesMemorySpill())));
        builder.append("\n").append(String.format("Stage Disk Spill (GB):         %50.2f", bytes2GB(getStagesDiskSpill())));
        builder.append("\n");
        builder.append("\n").append(String.format("Executors Cores:               %,50d", getCoresPerExecutor()));
        builder.append("\n").append(String.format("Executors Max Memory:          %50.2f", bytes2GB(getMaxMemoryPerExecutor())));
        builder.append("\n").append(String.format("Executors Time (minutes):      %50.2f", milli2Min(getExecutorsProcessingTime())));
        builder.append("\n").append(String.format("Executors GC Time (minutes):   %50.2f", milli2Min(getExecutorsGCTime())));
        builder.append("\n").append(String.format("Executors Input (GB):          %50.2f", bytes2GB(getExecutorsInputBytes())));
        builder.append("\n").append(String.format("Executors Failed Tasks:        %,50d", getExecutorsFailedTasks()));
        builder.append("\n").append(String.format("Executors Shuffle Read (GB):   %50.2f", bytes2GB(getExecutorsShuffleReadBytes())));
        builder.append("\n").append(String.format("Executors Shuffle Write (GB):  %50.2f", bytes2GB(getExecutorsShuffleWriteBytes())));
        return builder.toString();
    }
}
