import classification.Classifier;
import classification.ClassifierImp;
import configurations.utilites.Configuration;
import featuresextraction.Feature;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SingleApplicationHandlerTest {
    SingleApplicationHandler singleApplicationHandler;
    FeaturesExtractionHandler featuresExtractionHandler;
    Classifier classifier;

    @Before
    public void before() {
        singleApplicationHandler = mock(SingleApplicationHandlerImpl.class);
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        classifier = mock(ClassifierImp.class);

        when(featuresExtractionHandler.extract(any(SparkApplication.class))).thenReturn(new ArrayList<Feature>());

        when(classifier.classify(ArgumentMatchers.<Feature>anyList()))
                .thenReturn(new ArrayList<Configuration>());

        SparkApplication sparkApplication = new SparkApplication();
        List<Configuration> configurations = simulatePrediction(sparkApplication);

        when(singleApplicationHandler.predictSuitableConfigurations(any(SparkApplication.class)))
                .thenReturn(configurations);

    }


    @Test
    public void PredictSuitableConfigurationsTest() {

        Assert.assertEquals(new ArrayList<Configuration>(), singleApplicationHandler.predictSuitableConfigurations(new SparkApplication()));
    }

    private List<Configuration> simulatePrediction(SparkApplication sparkApplication){
        return classifier.classify(featuresExtractionHandler.extract(sparkApplication));
    }
}
