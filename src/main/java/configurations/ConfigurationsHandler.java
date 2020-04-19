package configurations;

import configurations.utilites.Configuration;
import spark.utilites.SparkApplication;

import java.util.List;

public interface ConfigurationsHandler {


    /** @param sparkApplication : Containing the application to be applied
     * @return List<Configuration> : Containing best configurations.
     */
    List<Configuration> applyApplication(SparkApplication sparkApplication);

}
