package configurations;

import spark.utilites.SparkApplication;

import java.util.List;

public interface ConfigurationsHandler {


    /** @param sparkApplication : Containing the application to be applied
     * @return List<String> : Containing best configurations.
     */
    List<List<String>> applyApplication(SparkApplication sparkApplication);

}
