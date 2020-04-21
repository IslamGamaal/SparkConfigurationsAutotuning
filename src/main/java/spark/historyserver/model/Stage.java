package spark.historyserver.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import spark.historyserver.util.CustomJsonDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stage implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String status;
    private Long stageId;
    private Long attemptId;
    private Long numTasks;
    private Long numActiveTasks;
    private Long numCompleteTasks;
    private Long numFailedTasks;
    private Long numKilledTasks;
    private Long numCompletedIndices;
    private Long executorRunTime;
    private Long executorCpuTime;
    @JsonDeserialize(using = CustomJsonDateSerializer.class)
    private Date submissionTime;
    @JsonDeserialize(using = CustomJsonDateSerializer.class)
    private Date firstTaskLaunchedTime;
    private String failureReason;
    @JsonDeserialize(using = CustomJsonDateSerializer.class)
    private Date completionTime;
    private Long inputBytes;
    private Long inputRecords;
    private Long outputBytes;
    private Long outputRecords;
    private Long shuffleReadBytes;
    private Long shuffleReadRecords;
    private Long shuffleWriteBytes;
    private Long shuffleWriteRecords;
    private Long memoryBytesSpilled;
    private Long diskBytesSpilled;
    private String name;
    private String details;
    private String schedulingPool;
    private Long[] rddIds;
    private Object[] accumulatorUpdates;
    private Object killedTasksSummary;
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getStageId() {
        return stageId;
    }
    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
    public Long getAttemptId() {
        return attemptId;
    }
    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }
    public Long getNumTasks() {
        return numTasks;
    }
    public void setNumTasks(Long numTasks) {
        this.numTasks = numTasks;
    }
    public Long getNumActiveTasks() {
        return numActiveTasks;
    }
    public void setNumActiveTasks(Long numActiveTasks) {
        this.numActiveTasks = numActiveTasks;
    }
    public Long getNumCompleteTasks() {
        return numCompleteTasks;
    }
    public void setNumCompleteTasks(Long numCompleteTasks) {
        this.numCompleteTasks = numCompleteTasks;
    }
    public Long getNumFailedTasks() {
        return numFailedTasks;
    }
    public void setNumFailedTasks(Long numFailedTasks) {
        this.numFailedTasks = numFailedTasks;
    }
    public Long getNumKilledTasks() {
        return numKilledTasks;
    }
    public void setNumKilledTasks(Long numKilledTasks) {
        this.numKilledTasks = numKilledTasks;
    }
    public Long getNumCompletedIndices() {
        return numCompletedIndices;
    }
    public void setNumCompletedIndices(Long numCompletedIndices) {
        this.numCompletedIndices = numCompletedIndices;
    }
    public Long getExecutorRunTime() {
        return executorRunTime;
    }
    public void setExecutorRunTime(Long executorRunTime) {
        this.executorRunTime = executorRunTime;
    }
    public Long getExecutorCpuTime() {
        return executorCpuTime;
    }
    public void setExecutorCpuTime(Long executorCpuTime) {
        this.executorCpuTime = executorCpuTime;
    }
    public Date getSubmissionTime() {
        return submissionTime;
    }
    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }
    public Date getFirstTaskLaunchedTime() {
        return firstTaskLaunchedTime;
    }
    public void setFirstTaskLaunchedTime(Date firstTaskLaunchedTime) {
        this.firstTaskLaunchedTime = firstTaskLaunchedTime;
    }
    public String getFailureReason() {
        return failureReason;
    }
    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    public Date getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
    public Long getInputBytes() {
        return inputBytes;
    }
    public void setInputBytes(Long inputBytes) {
        this.inputBytes = inputBytes;
    }
    public Long getInputRecords() {
        return inputRecords;
    }
    public void setInputRecords(Long inputRecords) {
        this.inputRecords = inputRecords;
    }
    public Long getOutputBytes() {
        return outputBytes;
    }
    public void setOutputBytes(Long outputBytes) {
        this.outputBytes = outputBytes;
    }
    public Long getOutputRecords() {
        return outputRecords;
    }
    public void setOutputRecords(Long outputRecords) {
        this.outputRecords = outputRecords;
    }
    public Long getShuffleReadBytes() {
        return shuffleReadBytes;
    }
    public void setShuffleReadBytes(Long shuffleReadBytes) {
        this.shuffleReadBytes = shuffleReadBytes;
    }
    public Long getShuffleReadRecords() {
        return shuffleReadRecords;
    }
    public void setShuffleReadRecords(Long shuffleReadRecords) {
        this.shuffleReadRecords = shuffleReadRecords;
    }
    public Long getShuffleWriteBytes() {
        return shuffleWriteBytes;
    }
    public void setShuffleWriteBytes(Long shuffleWriteBytes) {
        this.shuffleWriteBytes = shuffleWriteBytes;
    }
    public Long getShuffleWriteRecords() {
        return shuffleWriteRecords;
    }
    public void setShuffleWriteRecords(Long shuffleWriteRecords) {
        this.shuffleWriteRecords = shuffleWriteRecords;
    }
    public Long getMemoryBytesSpilled() {
        return memoryBytesSpilled;
    }
    public void setMemoryBytesSpilled(Long memoryBytesSpilled) {
        this.memoryBytesSpilled = memoryBytesSpilled;
    }
    public Long getDiskBytesSpilled() {
        return diskBytesSpilled;
    }
    public void setDiskBytesSpilled(Long diskBytesSpilled) {
        this.diskBytesSpilled = diskBytesSpilled;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getSchedulingPool() {
        return schedulingPool;
    }
    public void setSchedulingPool(String schedulingPool) {
        this.schedulingPool = schedulingPool;
    }
    public Long[] getRddIds() {
        return rddIds;
    }
    public void setRddIds(Long[] rddIds) {
        this.rddIds = rddIds;
    }
    public Object[] getAccumulatorUpdates() {
        return accumulatorUpdates;
    }
    public void setAccumulatorUpdates(Object[] accumulatorUpdates) {
        this.accumulatorUpdates = accumulatorUpdates;
    }
    public Object getKilledTasksSummary() {
        return killedTasksSummary;
    }
    public void setKilledTasksSummary(Object killedTasksSummary) {
        this.killedTasksSummary = killedTasksSummary;
    }
}
