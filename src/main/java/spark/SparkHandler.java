package spark;

import spark.utilites.SparkApplicationUtilites;

import java.util.List;

public interface SparkHandler {

    /**
     *
     * @param configuration List<String> : Configurations to be used in submitting application.
     * @return SparkApplicationUtilites : Containing the submitted application info.
     */
    SparkApplicationUtilites HandleApplication(List<String> configuration);

    /**
     *
     * @return SparkApplicationUtilites : Containing the submitted application info.
     */
    SparkApplicationUtilites HandleApplication();
}
