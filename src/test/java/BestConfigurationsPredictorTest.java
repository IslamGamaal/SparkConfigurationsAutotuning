import classification.Classifier;
import classification.ClassifierImp;
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

import static org.mockito.Mockito.*;

public class BestConfigurationsPredictorTest {
    BestConfigurationsPredictor bestConfigurationPredictor;
    FeaturesExtractionHandler featuresExtractionHandler;
    Classifier classifier;

    @Before
    public void before() throws IOException {
        bestConfigurationPredictor = mock(BestConfigurationsPredictorImpl.class);
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        classifier = mock(ClassifierImp.class);

        when(featuresExtractionHandler.extract(any(SparkApplication.class))).thenReturn(new ArrayList<Feature>());
        when(classifier.classify(ArgumentMatchers.<Feature>anyList())).thenReturn(new ArrayList<Configuration>());

        SparkApplication sparkApplication = new SparkApplication();
        List<Configuration> configurations = simulatePrediction(sparkApplication);

        when(bestConfigurationPredictor.predictBestConfigurations(any(SparkApplication.class)))
                .thenReturn(configurations);

    }


    @Test
    public void PredictSuitableConfigurationsTest() {

        Assert.assertEquals(new ArrayList<Configuration>(), bestConfigurationPredictor.predictBestConfigurations(new SparkApplication()));
    }

    private List<Configuration> simulatePrediction(SparkApplication sparkApplication) throws IOException {
        return classifier.classify(featuresExtractionHandler.extract(sparkApplication));
    }
}
