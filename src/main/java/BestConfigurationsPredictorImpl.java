import classification.Classifier;
import classification.ClassifierImp;
import configurations.utilites.Configuration;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import featuresextraction.utilities.Feature;
import spark.utilites.SparkApplication;

import java.util.List;

public class BestConfigurationsPredictorImpl implements BestConfigurationsPredictor {
    private final float THRESHOLD = 2;
    private final int TIME_TO_LIVE = 7;

    public List<Configuration> predictBestConfigurations(SparkApplication sparkApplication) {
        FeaturesExtractionHandler featuresExtractionHandler = new FeaturesExtractionHandlerImp();
        List<Feature> features = featuresExtractionHandler.extract(sparkApplication);
        Classifier classifier = new ClassifierImp();
        List<Configuration> prevConfigurations, currentConfigurations;
        prevConfigurations = classifier.classify(features);
        float deltaConfigs;
        int timeToLive = TIME_TO_LIVE;
        do {
            features = featuresExtractionHandler.extract(prevConfigurations, sparkApplication);
            currentConfigurations = classifier.classify(features);
            deltaConfigs = calculateDeltaConfigs(prevConfigurations, currentConfigurations);
            timeToLive--;
        } while (deltaConfigs > THRESHOLD && timeToLive > 0);
        return currentConfigurations;
    }

    private float calculateDeltaConfigs(List<Configuration> prevConfigurations, List<Configuration> currentConfigurations) {
        int len = prevConfigurations.size();
        float deltaConfigs = 0;
        for(int i = 0; i < len; i++) {
            float delta = prevConfigurations.get(i).getValue() - currentConfigurations.get(i).getValue();
            delta = Math.abs(delta);
            deltaConfigs += delta;
        }
        return deltaConfigs;
    }
}
