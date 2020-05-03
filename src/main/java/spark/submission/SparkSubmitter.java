package spark.submission;

import spark.utilites.SparkApplication;
import spark.utilites.SparkRunInfo;

import java.util.List;

public interface SparkSubmitter {

    /** This function is responsible for submitting new application using spark.
     *
     */
    void submitApplication(SparkApplication sparkApplication , SparkRunInfo sparkRunInfo);
}
