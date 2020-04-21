package spark.historyserver.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import spark.historyserver.util.CustomJsonDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Job implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long jobId;
    private String name;
    @JsonDeserialize(using = CustomJsonDateSerializer.class)
    private Date submissionTime;
    @JsonDeserialize(using = CustomJsonDateSerializer.class)
    private Date completionTime;
    private Long[] stageIds;
    private String status;
    private Long numTasks;
    private Long numActiveTasks;
    private Long numCompletedTasks;
    private Long numSkippedTasks;
    private Long numFailedTasks;
    private Long numKilledTasks;
    private Long numCompletedIndices;
    private Long numActiveStages;
    private Long numCompletedStages;
    private Long numSkippedStages;
    private Long numFailedStages;
    private Object killedTasksSummary;
    
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getSubmissionTime() {
        return submissionTime;
    }
    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }
    public Date getCompletionTime() {
        return completionTime;
    }
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
    public Long[] getStageIds() {
        return stageIds;
    }
    public void setStageIds(Long[] stageIds) {
        this.stageIds = stageIds;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public Long getNumCompletedTasks() {
        return numCompletedTasks;
    }
    public void setNumCompletedTasks(Long numCompletedTasks) {
        this.numCompletedTasks = numCompletedTasks;
    }
    public Long getNumSkippedTasks() {
        return numSkippedTasks;
    }
    public void setNumSkippedTasks(Long numSkippedTasks) {
        this.numSkippedTasks = numSkippedTasks;
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
    public Long getNumActiveStages() {
        return numActiveStages;
    }
    public void setNumActiveStages(Long numActiveStages) {
        this.numActiveStages = numActiveStages;
    }
    public Long getNumCompletedStages() {
        return numCompletedStages;
    }
    public void setNumCompletedStages(Long numCompletedStages) {
        this.numCompletedStages = numCompletedStages;
    }
    public Long getNumSkippedStages() {
        return numSkippedStages;
    }
    public void setNumSkippedStages(Long numSkippedStages) {
        this.numSkippedStages = numSkippedStages;
    }
    public Long getNumFailedStages() {
        return numFailedStages;
    }
    public void setNumFailedStages(Long numFailedStages) {
        this.numFailedStages = numFailedStages;
    }
    public Object getKilledTasksSummary() {
        return killedTasksSummary;
    }
    public void setKilledTasksSummary(Object killedTasksSummary) {
        this.killedTasksSummary = killedTasksSummary;
    }
}
