package configurations.samples;

import configurations.utilites.Configuration;

import java.util.List;

public class ConfigurationsFilesHandlerImp implements ConfigurationsFilesHandler {
    public void writeConfigurationsSamplesInFiles(String configurationsFolderPath, List<List<Float>> samples) {
        //TODO this function need to just given one List<Configurations> write it in a file then return the path of the written file.
    }

    @Override
    public String writeConfigurationsInFile(List<Configuration> configurations, List<String> sparkhistory) {
        return null;
    }
}
