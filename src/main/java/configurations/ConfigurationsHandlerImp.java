package configurations;

import configurations.picker.BestConfigurationPicker;
import configurations.picker.BestConfigurationsPickerImp;
import configurations.samples.ConfigurationsFilesHandler;
import configurations.samples.ConfigurationsFilesHandlerImp;
import configurations.samples.ConfigurationsSampler;
import configurations.samples.ConfigurationsSamplerImp;
import configurations.utilites.Configuration;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigurationsHandlerImp implements ConfigurationsHandler {
    private ConfigurationsSampler configurationsSampler;
    private BestConfigurationPicker bestConfigurationPicker;
    private ConfigurationsFilesHandler configurationsFilesHandler;

    public ConfigurationsHandlerImp(){
        this.configurationsSampler = new ConfigurationsSamplerImp();
        this.bestConfigurationPicker = new BestConfigurationsPickerImp();
        this.configurationsFilesHandler = new ConfigurationsFilesHandlerImp();
    }

    public List<Configuration> applyApplication(SparkApplication sparkApplication , Map<String, Object> parameters) {
        configurationsSampler.generateSamples((Integer) parameters.getOrDefault("samplesNum" , 200), new ArrayList<>() , new ArrayList<>());
        return null;
    }
}
