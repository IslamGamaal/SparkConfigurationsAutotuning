package datasetcreation;

import java.util.List;

public interface ConfigurationSampler {

    /**
     * This function generate the configurations samples values.
     * @param samplesNum int: containing samples number needed to be generated.
     * @param upperBounds List : containing the upper bounds of the configurations.
     * @param lowerBounds List : containing the lower bounds of the configurations.
     * @return List<List<Float>>: containing lists of configurations samples.
     **/

    List<List<Float>> generateSamples(int samplesNum , List<Float> upperBounds , List<Float> lowerBounds);


    /**
     * This functions writes the configurations samples in files .conf
     *
     * @param configurationsFolderPath String : containing path of the folder where the configurations will be written.
     * @param samples List<List<Float>> : containing lists of configurations samples.
     **/
    void writeConfigurationsSamplesInFiles(String configurationsFolderPath , List<List<Float>> samples);
}
