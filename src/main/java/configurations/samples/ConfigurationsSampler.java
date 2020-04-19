package configurations.samples;

import configurations.utilites.Configuration;

import java.util.List;

public interface ConfigurationsSampler {

    /**
     * This function generate the configurations samples values.
     * @param samplesNum int: containing samples number needed to be generated.
     * @param upperBounds List : containing the upper bounds of the configurations.
     * @param lowerBounds List : containing the lower bounds of the configurations.
     * @return List<List<Configuration>>: containing lists of configurations samples.
     **/

    List<List<Configuration>> generateSamples(int samplesNum , List<Float> upperBounds , List<Float> lowerBounds);

}
