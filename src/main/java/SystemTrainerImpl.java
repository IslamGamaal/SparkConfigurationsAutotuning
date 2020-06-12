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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemTrainerImpl implements SystemTrainer {
    FeaturesExtractionHandler featuresExtractionHandler;
    ConfigurationsHandler configurationsHandler;
    Classifier classifier;
    Logger logger;

    public SystemTrainerImpl() {
        featuresExtractionHandler = new FeaturesExtractionHandlerImp();
        configurationsHandler = new ConfigurationsHandlerImp();
        classifier = new ClassifierImp();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    public boolean trainSystem(List<SparkApplication> applications , String mode) {

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
            logger.log(Level.INFO , "application name1 : " + sparkApplication.getName() + "\n");
            logger.log(Level.INFO , "features : \n");
        }

        classifier.trainModel(dataSetPairs);
        return true;
    }


}
