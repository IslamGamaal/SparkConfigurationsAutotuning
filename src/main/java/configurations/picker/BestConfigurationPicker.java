package configurations.picker;

import configurations.utilites.Configuration;
import spark.utilites.SparkApplication;

import java.util.List;

public interface BestConfigurationPicker {

    /**
     * @param sparkApplication : Containing the spark application.
     * @param configurations List<List<String>> : Containing configurations samples.
     * @return List<String> : Containing best configurations.
     */
    List<Configuration> pickBestConfigurationsForApplication(List<List<Configuration>> configurations , SparkApplication sparkApplication);
}
