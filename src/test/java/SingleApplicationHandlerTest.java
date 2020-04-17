import classification.Classifier;
import classification.ClassifierImp;
import featuresextraction.Feature;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SingleApplicationHandlerTest {

    FeaturesExtractionHandler featuresExtractionHandler;
    Classifier classifier;
    List<String> configurations;

    @Before
    public void before() {
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        classifier = mock(ClassifierImp.class);

        when(featuresExtractionHandler.extract(new SparkApplication()))
                .thenReturn(new ArrayList<Feature>());

        when(classifier.classify(featuresExtractionHandler.extract(new SparkApplication())))
                .thenReturn(new ArrayList<String>());

    }


    @Test
    public void PredictSuitableConfigurationsTest() {
        String pathToJar = "some/path/to/jar/application";
        SingleApplicationHandler singleApplicationHandler = mock(SingleApplicationHandlerImpl.class);
        configurations = classifier.classify(featuresExtractionHandler.extract(new SparkApplication()));
        when(singleApplicationHandler.predictSuitableConfigurations(new SparkApplication()))
                .thenReturn(configurations);

        Assert.assertEquals(Arrays.asList("5 MB", "2", "3 GB"), singleApplicationHandler.predictSuitableConfigurations(new SparkApplication()));
    }
}
