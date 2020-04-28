package configurations.picker;

import configurations.utilites.Configuration;
import spark.SparkHandler;
import spark.SparkHandlerImp;
import spark.utilites.SparkApplication;

import java.util.List;

public class BestConfigurationsPickerImp implements BestConfigurationPicker {
    private SparkHandler sparkHandler ;

    public BestConfigurationsPickerImp(){
        this.sparkHandler = new SparkHandlerImp();
    }

    public List<Configuration> pickBestConfigurationsForApplication(List<List<Configuration>> configurations, SparkApplication sparkApplication) {
        long bestTime = Long.MAX_VALUE;
        List<Configuration> bestConfigurations = null;
        for (List<Configuration> sampleConfigurations: configurations) {
            sparkApplication = sparkHandler.HandleApplication(sampleConfigurations , sparkApplication);
            if (sparkApplication.getLastRunTime() < bestTime) {
                bestConfigurations = sampleConfigurations;
            }
        }
        return bestConfigurations;
    }
}
