package configurations.picker;

import configurations.utilites.Configuration;
import spark.SparkHandler;
import spark.SparkHandlerImp;
import spark.utilites.SparkApplication;

import java.util.List;
import java.util.Map;

public class BestConfigurationsPickerImp implements BestConfigurationPicker {
    private SparkHandler sparkHandler ;

    public BestConfigurationsPickerImp(){
        this.sparkHandler = new SparkHandlerImp();
    }

    public List<Configuration> pickBestConfigurationsForApplication(List<List<Configuration>> configurations, SparkApplication sparkApplication) {
        long bestTime = Long.MAX_VALUE;
        List<Configuration> bestConfigurations = null;
        for (List<Configuration> sampleConfigurations: configurations) {
            SparkApplication tempSparkApplication = sparkHandler.HandleApplication(sampleConfigurations , sparkApplication);
            if (tempSparkApplication == null)
                continue;
            if (tempSparkApplication.getLastRunTime() < bestTime) {
                //TODO FIX REPLACING
                replaceActualConfigurations(sampleConfigurations , tempSparkApplication.getLastRunActualConfigurations());
                bestTime = tempSparkApplication.getLastRunTime();
                bestConfigurations = sampleConfigurations;
            }
        }
        return bestConfigurations;
    }

    private void replaceActualConfigurations(List<Configuration> sampleConfigurations, Map<String, String> lastRunActualConfigurations) {
        sampleConfigurations.get(2).setValue((float) Math.ceil(Float.parseFloat(lastRunActualConfigurations.get("MaxMemory"))/ (1024 * 1024 * 1024)));
        sampleConfigurations.get(6).setValue(Float.parseFloat(lastRunActualConfigurations.get("spark.executor.cores")));
    }
}
