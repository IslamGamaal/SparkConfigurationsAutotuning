package spark;

import spark.utilites.SparkApplication;

import java.util.List;

public interface SparkHandler {

    /**
     *
     * @param configuration List<String> : Configurations to be used in submitting application.
     * @return SparkApplication : Containing the submitted application info.
     */
    SparkApplication HandleApplication(List<String> configuration);

    /**
     *
     * @return SparkApplication : Containing the submitted application info.
     */
    SparkApplication HandleApplication();
}
