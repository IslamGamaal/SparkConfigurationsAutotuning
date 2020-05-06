import classification.Classifier;
import classification.ClassifierImp;
import configurations.ConfigurationsHandler;
import configurations.ConfigurationsHandlerImp;
import configurations.utilites.Configuration;
import featuresextraction.utilities.Feature;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import spark.utilites.SparkApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemTrainerTest {
    SystemTrainer systemTrainer;
    FeaturesExtractionHandler featuresExtractionHandler;
    ConfigurationsHandler configurationsHandler;
    Classifier classifier;
    SparkApplication sparkApplication;
    List<Feature> features;
    List<SparkApplication> startUpApplication;
    Boolean success;

    @Before
    public void before() throws IOException {
        sparkApplication = new SparkApplication();
        startUpApplication = new ArrayList<SparkApplication>();
        for (int i = 0; i < 5; i++) {
            startUpApplication.add(new SparkApplication());
        }
        systemTrainer = mock(SystemTrainerImpl.class);
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        configurationsHandler = mock(ConfigurationsHandlerImp.class);
        classifier = mock(ClassifierImp.class);

        when(featuresExtractionHandler.extract(any(SparkApplication.class))).thenReturn(new ArrayList<Feature>());
        //when(configurationsHandler.applyApplication(any(SparkApplication.class))).thenReturn(new ArrayList<Configuration>());
        when(classifier.trainModel(ArgumentMatchers.<List<Feature>>anyList() , ArgumentMatchers.<List<Configuration>>anyList())).thenReturn(true);

        boolean simulationResult = simulateSystemTraining(startUpApplication);

        when(systemTrainer.trainSystem(ArgumentMatchers.<SparkApplication>anyList())).thenReturn(simulationResult);

    }


    @Test
    public void trainSystemTest() {
        Assert.assertTrue(systemTrainer.trainSystem(startUpApplication));
    }

    private boolean simulateSystemTraining(List<SparkApplication> startUpApplication) throws IOException {
        List<List<Feature>> features = new ArrayList<List<Feature>>();
        List<List<Configuration>> configurations = new ArrayList<List<Configuration>>();
        for (int i = 0; i < startUpApplication.size() ; i++) {
            List<Feature> singleApplicationFeatures = featuresExtractionHandler.extract(startUpApplication.get(i));
            features.add(singleApplicationFeatures);
            //List<Configuration> singleApplicationConfiguration = configurationsHandler.applyApplication(startUpApplication.get(i));
            //configurations.add(singleApplicationConfiguration);
        }
        return classifier.trainModel(features , configurations);
    }
}
