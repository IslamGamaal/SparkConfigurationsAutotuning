package spark.submission;

import spark.utilites.SparkApplication;

import java.util.List;

public interface SparkSubmitter {

    /** This function is responsible for submitting new application using spark.
     *
     * @param configurations List<String> : Containing the configurations to be used in application submission.
     */
    void submitApplication(List<String> configurations, SparkApplication sparkApplication);
}
