import classification.Classifier;
import classification.ClassifierImp;
import classification.utils.DataSetPair;
import configurations.ConfigurationsHandler;
import configurations.ConfigurationsHandlerImp;
import configurations.utilites.Configuration;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import featuresextraction.utilities.Feature;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemTrainerImpl implements SystemTrainer {
    FeaturesExtractionHandler featuresExtractionHandler;
    ConfigurationsHandler configurationsHandler;
    Classifier classifier;

    public SystemTrainerImpl() {
        featuresExtractionHandler = new FeaturesExtractionHandlerImp();
        configurationsHandler = new ConfigurationsHandlerImp();
        classifier = new ClassifierImp();
    }

    public boolean trainSystem(List<SparkApplication> applications) {

        List<DataSetPair> dataSetPairs = new ArrayList<>();
        for (SparkApplication sparkApplication: applications) {
            List<Feature> features = featuresExtractionHandler.extract(sparkApplication);
            if (features == null || features.size() == 0)
                continue;
            List<Configuration> configurations = configurationsHandler.applyApplication(sparkApplication , new HashMap<>());
            if (configurations == null || configurations.size() == 0)
                continue;
            DataSetPair newDataSetPair = new DataSetPair(features , configurations);
            dataSetPairs.add(newDataSetPair);
        }

        classifier.trainModel(dataSetPairs);
        return true;
    }


}
