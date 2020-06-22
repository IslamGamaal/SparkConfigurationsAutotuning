package configurations;

import configurations.picker.BestConfigurationPicker;
import configurations.picker.BestConfigurationsPickerImp;
import configurations.samples.*;
import configurations.utilites.Configuration;
import configurations.utilites.utils;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationsHandlerImp implements ConfigurationsHandler {
    private ConfigurationsSampler configurationsSampler;
    private BestConfigurationPicker bestConfigurationPicker;
    private Map<String , Object> confsSpecs;
    private ConfigurationsFilesHandler configurationsFilesHandler;

    public ConfigurationsHandlerImp(){
        this.configurationsSampler = new BinaryHyperCubeSampler();
        this.bestConfigurationPicker = new BestConfigurationsPickerImp();
        this.configurationsFilesHandler = new ConfigurationsFilesHandlerImp();
        this.confsSpecs = new HashMap<>();
        utils.loadSpecs(confsSpecs);
    }



    public List<Configuration> applyApplication(SparkApplication sparkApplication , Map<String, Object> parameters) {
        List<List<Float>> configurationsSamples = configurationsSampler.generateSamples((Integer) parameters.getOrDefault("samplesNum" , 3), (List<Float>) confsSpecs.get("ub"), (List<Float>) confsSpecs.get("lb"));
        List<List<Configuration>> configurations = new ArrayList<>();
        for (List<Float> configurationsSample : configurationsSamples) {
            List<Configuration> configurationsList = utils.getConfigurationsList(configurationsSample , this.confsSpecs);
            if (configurationsList != null && configurationsList.size()!=0)
                configurations.add(configurationsList);
        }
        List<Configuration> bestConfigurations = bestConfigurationPicker.pickBestConfigurationsForApplication(configurations , sparkApplication);
        return bestConfigurations;
    }
}
