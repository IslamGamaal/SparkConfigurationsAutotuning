package configurations.samples;

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

}
