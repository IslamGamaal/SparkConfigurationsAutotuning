package featuresextraction;

import classification.Classifier;
import classification.ClassifierImp;
import configurations.utilites.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeaturesExtractionHandlerTest {
    FeaturesExtractionHandler featuresExtractionHandler;
    StaticFeaturesExtractor staticFeaturesExtractor;
    DynamicFeaturesExtractor dynamicFeaturesExtractor;


    @Before
    public void before() {
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        staticFeaturesExtractor = mock(StaticFeaturesExtractor.class);
        dynamicFeaturesExtractor = mock(DynamicFeaturesExtractor.class);

        when(staticFeaturesExtractor.extract(anyString(), anyString())).thenReturn(new ArrayList<Feature>());
        when(dynamicFeaturesExtractor.extract(anyString())).thenReturn(new ArrayList<Feature>());

    }


    @Test
    public void PredictSuitableConfigurationsTest() {

    }
}
