package configurations.samples;

import java.util.List;

public interface ConfigurationsFilesHandler {

    /**
     * This functions writes the configurations samples in files .conf
     * @param configurationsFolderPath String : containing path of the folder where the configurations will be written.
     * @param samples List<List<Float>> : containing lists of configurations samples.
     **/
    void writeConfigurationsSamplesInFiles(String configurationsFolderPath , List<List<Float>> samples);

}
