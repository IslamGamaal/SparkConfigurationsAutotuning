package classification.utils;

import configurations.utilites.Configuration;
import featuresextraction.utilities.Feature;

import java.util.List;

public class DataSetPair {
    List<Feature> features;
    List<Configuration> configurations;

    public DataSetPair(List<Feature> features, List<Configuration> configurations) {
        this.features = features;
        this.configurations = configurations;
    }
}
