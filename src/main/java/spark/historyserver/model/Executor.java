package spark.historyserver.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import spark.historyserver.util.CustomJsonDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Executor implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Log  implements Serializable{
        private static final long serialVersionUID = 1L;
        
        private String stdout;
        private String stderr;
        
        public String getStdout() {
            return stdout;
        }
        public void setStdout(String stdout) {
            this.stdout = stdout;
        }
        public String getStderr() {
            return stderr;
        }
        public void setStderr(String stderr) {
            this.stderr = stderr;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Metric implements Serializable{
        private static final long serialVersionUID = 1L;
        
        private Long usedOnHeapStorageMemory;
        private Long usedOffHeapStorageMemory;
        private Long totalOnHeapStorageMemory;
        private Long totalOffHeapStorageMemory;
        
        public Long getUsedOnHeapStorageMemory() {
            return usedOnHeapStorageMemory;
        }
        public void setUsedOnHeapStorageMemory(Long usedOnHeapStorageMemory) {
            this.usedOnHeapStorageMemory = usedOnHeapStorageMemory;
        }
        public Long getUsedOffHeapStorageMemory() {
            return usedOffHeapStorageMemory;
        }
        public void setUsedOffHeapStorageMemory(Long usedOffHeapStorageMemory) {
            this.usedOffHeapStorageMemory = usedOffHeapStorageMemory;
        }
        public Long getTotalOnHeapStorageMemory() {
            return totalOnHeapStorageMemory;
        }
        public void setTotalOnHeapStorageMemory(Long totalOnHeapStorageMemory) {
            this.totalOnHeapStorageMemory = totalOnHeapStorageMemory;
        }
        public Long getTotalOffHeapStorageMemory() {
            return totalOffHeapStorageMemory;
        }
        public void setTotalOffHeapStorageMemory(Long totalOffHeapStorageMemory) {
            this.totalOffHeapStorageMemory = totalOffHeapStorageMemory;
        }
    }

    private String id;
    private String hostPort;
    private Boolean isActive;
    private Long rddBlocks;
    private Long memoryUsed;
    private Long diskUsed;
    private Long totalCores;
    private Long maxTasks;
    private Long activeTasks;
    private Long failedTasks;
    private Long completedTasks;
    private Long totalTasks;
    private Long totalDuration;
    private Long totalGCTime;
    private Long totalInputBytes;
    private Long totalShuffleRead;
    private Long totalShuffleWrite;
    private Boolean isBlacklisted;
    private Long maxMemory;
    @JsonDeserialize(using = CustomJsonDateSerializer.class)
    private Date addTime;
    
    private Log executorLogs;
    
    private Metric memoryMetrics;
    
    private Object blacklistedInStages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getRddBlocks() {
        return rddBlocks;
    }

    public void setRddBlocks(Long rddBlocks) {
        this.rddBlocks = rddBlocks;
    }

    public Long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Long getDiskUsed() {
        return diskUsed;
    }

    public void setDiskUsed(Long diskUsed) {
        this.diskUsed = diskUsed;
    }

    public Long getTotalCores() {
        return totalCores;
    }

    public void setTotalCores(Long totalCores) {
        this.totalCores = totalCores;
    }

    public Long getMaxTasks() {
        return maxTasks;
    }

    public void setMaxTasks(Long maxTasks) {
        this.maxTasks = maxTasks;
    }

    public Long getActiveTasks() {
        return activeTasks;
    }

    public void setActiveTasks(Long activeTasks) {
        this.activeTasks = activeTasks;
    }

    public Long getFailedTasks() {
        return failedTasks;
    }

    public void setFailedTasks(Long failedTasks) {
        this.failedTasks = failedTasks;
    }

    public Long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public Long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(Long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Long getTotalGCTime() {
        return totalGCTime;
    }

    public void setTotalGCTime(Long totalGCTime) {
        this.totalGCTime = totalGCTime;
    }

    public Long getTotalInputBytes() {
        return totalInputBytes;
    }

    public void setTotalInputBytes(Long totalInputBytes) {
        this.totalInputBytes = totalInputBytes;
    }

    public Long getTotalShuffleRead() {
        return totalShuffleRead;
    }

    public void setTotalShuffleRead(Long totalShuffleRead) {
        this.totalShuffleRead = totalShuffleRead;
    }

    public Long getTotalShuffleWrite() {
        return totalShuffleWrite;
    }

    public void setTotalShuffleWrite(Long totalShuffleWrite) {
        this.totalShuffleWrite = totalShuffleWrite;
    }

    public Boolean getIsBlacklisted() {
        return isBlacklisted;
    }

    public void setIsBlacklisted(Boolean isBlacklisted) {
        this.isBlacklisted = isBlacklisted;
    }

    public Long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Log getExecutorLogs() {
        return executorLogs;
    }

    public void setExecutorLogs(Log executorLogs) {
        this.executorLogs = executorLogs;
    }

    public Metric getMemoryMetrics() {
        return memoryMetrics;
    }

    public void setMemoryMetrics(Metric memoryMetrics) {
        this.memoryMetrics = memoryMetrics;
    }

    public Object getBlacklistedInStages() {
        return blacklistedInStages;
    }

    public void setBlacklistedInStages(Object blacklistedInStages) {
        this.blacklistedInStages = blacklistedInStages;
    }
}
