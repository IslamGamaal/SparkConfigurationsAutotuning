import classification.Classifier;
import classification.ClassifierImp;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

        when(featuresExtractionHandler.extract("some/path/to/jar/application"))
                .thenReturn(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0)));

        when(classifier.classify(featuresExtractionHandler.extract("some/path/to/jar/application")))
                .thenReturn(new ArrayList<String>(Arrays.asList("5 MB", "2", "3 GB")));

    }


    @Test
    public void PredictSuitableConfigurationsTest() {
        String pathToJar = "some/path/to/jar/application";
        SingleApplicationHandler singleApplicationHandler = mock(SingleApplicationHandlerImpl.class);
        configurations = classifier.classify(featuresExtractionHandler.extract(pathToJar));
        when(singleApplicationHandler.predictSuitableConfigurations(pathToJar))
                .thenReturn(configurations);

        Assert.assertEquals(Arrays.asList("5 MB", "2", "3 GB"), singleApplicationHandler.predictSuitableConfigurations(pathToJar));
    }
}
