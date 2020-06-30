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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            logApplication(sparkApplication,features,configurations);
        }

        if (mode.equalsIgnoreCase("train")) {
            classifier.trainModel(dataSetPairs);
        }
        return true;
    }

    private void logApplication(SparkApplication sparkApplication, List<Feature> features, List<Configuration> configurations) {

        try {
            File file = new File(System.getProperty("user.dir")+ "/resources/appslogs");
            FileWriter fr = new FileWriter(file, true);
            fr.write("app name : " + sparkApplication.getName() + "\n");
            fr.write("features \n" );
            for (Feature feature : features) {
                fr.write(feature.getFeatureName() + " ");
                if (feature.getValueType().equalsIgnoreCase("String")){
                    fr.write((String) feature.getValue());
                }
                else if (feature.getValueType().equalsIgnoreCase("boolean")){
                    fr.write(String.valueOf((boolean) feature.getValue()));
                }
                fr.write("\n");
            }
            fr.write("configurations\n");
            for (Configuration configuration : configurations) {
                fr.write(configuration.getName() + " " );
                if(configuration.isInteger()){
                    if (configuration.isBoolean()) {
                        fr.write((int) configuration.getValue() == 1 ? "true" : "false");
                    }
                    else{
                        fr.write((int) configuration.getValue() + "");
                    }
                }
                else{
                    fr.write(configuration.getValue() + "");
                }
                if (!configuration.getUnit().equalsIgnoreCase("-")){
                    fr.write(configuration.getUnit());
                }
                fr.write("\n");


            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
