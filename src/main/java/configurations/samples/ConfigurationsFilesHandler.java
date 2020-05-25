package configurations.samples;

import configurations.utilites.Configuration;

import java.util.List;

public interface ConfigurationsFilesHandler {

    /**
     *
     **/
    String writeConfigurationsInFile(List<Configuration> configurations, List<String> sparkhistory);

}
