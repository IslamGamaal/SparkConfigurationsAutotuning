package spark.historyserver.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import spark.historyserver.util.CustomJsonDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Application implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attempt implements Serializable{
        private static final long serialVersionUID = 1L;
    
        @JsonDeserialize(using = CustomJsonDateSerializer.class)
        private Date startTime;
        @JsonDeserialize(using = CustomJsonDateSerializer.class)
        private Date endTime;
        @JsonDeserialize(using = CustomJsonDateSerializer.class)
        private Date lastUpdated;
        private Long duration;
        private String sparkUser;
        private Boolean completed;
        private String appSparkVersion;
        private Long startTimeEpoch;
        private Long endTimeEpoch;
        private Long lastUpdatedEpoch;
    
        public Date getStartTime() {
            return startTime;
        }
        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
        public Date getEndTime() {
            return endTime;
        }
        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
        public Date getLastUpdated() {
            return lastUpdated;
        }
        public void setLastUpdated(Date lastUpdated) {
            this.lastUpdated = lastUpdated;
        }
        public Long getDuration() {
            return duration;
        }
        public void setDuration(Long duration) {
            this.duration = duration;
        }
        public String getSparkUser() {
            return sparkUser;
        }
        public void setSparkUser(String sparkUser) {
            this.sparkUser = sparkUser;
        }
        public Boolean getCompleted() {
            return completed;
        }
        public void setCompleted(Boolean completed) {
            this.completed = completed;
        }
        public String getAppSparkVersion() {
            return appSparkVersion;
        }
        public void setAppSparkVersion(String appSparkVersion) {
            this.appSparkVersion = appSparkVersion;
        }
        public Long getStartTimeEpoch() {
            return startTimeEpoch;
        }
        public void setStartTimeEpoch(Long startTimeEpoch) {
            this.startTimeEpoch = startTimeEpoch;
        }
        public Long getEndTimeEpoch() {
            return endTimeEpoch;
        }
        public void setEndTimeEpoch(Long endTimeEpoch) {
            this.endTimeEpoch = endTimeEpoch;
        }
        public Long getLastUpdatedEpoch() {
            return lastUpdatedEpoch;
        }
        public void setLastUpdatedEpoch(Long lastUpdatedEpoch) {
            this.lastUpdatedEpoch = lastUpdatedEpoch;
        }
    }

    private String id;
    private String name;
    private Attempt[] attempts;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Attempt[] getAttempts() {
        return attempts;
    }
    public void setAttempts(Attempt[] attempts) {
        this.attempts = attempts;
    }
}
