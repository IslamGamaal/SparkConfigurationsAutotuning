package classification;

import java.util.List;

public interface Classifier {
    /**
     * This function loads the pre-trained model and predict the best
     * configurations for the given set of features
     *
     * @param applicationFeatures List: extracted by FeaturesExtraction module.
     * @return List: containing the predicted configurations converted to String
     **/
    List<String> classify(List<Double> applicationFeatures);
}
