package classification;

import configurations.utilites.Configuration;
import featuresextraction.utilities.Feature;

import java.util.List;

public interface Classifier {
    /**
     * This function loads the pre-trained model and predict the best
     * configurations for the given set of features
     *
     * @param applicationFeatures List: extracted by FeaturesExtraction module.
     * @return List: containing the predicted configurations converted to String
     **/
    List<Configuration> classify(List<Feature> applicationFeatures);

    //boolean trainModel(List<Double> inputFeatures, List<String> outputConfigurations);
    boolean trainModel(List<List<Feature>> features , List<List<Configuration>> configurations);
}
