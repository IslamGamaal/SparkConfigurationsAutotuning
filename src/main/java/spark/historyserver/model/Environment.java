package spark.historyserver.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Environment  implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Runtime  implements Serializable{
        private static final long serialVersionUID = 1L;
        
        private String javaVersion;
        private String javaHome;
        private String scalaVersion;
        
        public String getJavaVersion() {
            return javaVersion;
        }
        public void setJavaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
        }
        public String getJavaHome() {
            return javaHome;
        }
        public void setJavaHome(String javaHome) {
            this.javaHome = javaHome;
        }
        public String getScalaVersion() {
            return scalaVersion;
        }
        public void setScalaVersion(String scalaVersion) {
            this.scalaVersion = scalaVersion;
        }
    }

    private Runtime runtime;
    private String[][] sparkProperties;
    private String[][] systemProperties;
    private String[][] classpathEntries;
    
    public Runtime getRuntime() {
        return runtime;
    }
    public void setRuntime(Runtime runtime) {
        this.runtime = runtime;
    }
    public String[][] getSparkProperties() {
        return sparkProperties;
    }
    public void setSparkProperties(String[][] sparkProperties) {
        this.sparkProperties = sparkProperties;
    }
    public String[][] getSystemProperties() {
        return systemProperties;
    }
    public void setSystemProperties(String[][] systemProperties) {
        this.systemProperties = systemProperties;
    }
    public String[][] getClasspathEntries() {
        return classpathEntries;
    }
    public void setClasspathEntries(String[][] classpathEntries) {
        this.classpathEntries = classpathEntries;
    }
}
