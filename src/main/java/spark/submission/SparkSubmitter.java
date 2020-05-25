package spark.submission;

import spark.utilites.SparkApplication;
import spark.utilites.SparkRunInfo;

public interface SparkSubmitter {

    /** This function is responsible for submitting new application using spark.
     *
     * @return
     */
    int submitApplication(SparkApplication sparkApplication , SparkRunInfo sparkRunInfo);
}
