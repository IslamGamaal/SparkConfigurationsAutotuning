package featuresextraction;

import classification.Classifier;
import classification.ClassifierImp;
import configurations.utilites.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import spark.SparkHandler;
import spark.SparkHandlerImp;
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
    SparkHandler sparkHandler;


    @Before
    public void before() {
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        staticFeaturesExtractor = mock(StaticFeaturesExtractorImp.class);
        dynamicFeaturesExtractor = mock(DynamicFeaturesExtractorImp.class);
        sparkHandler = mock(SparkHandlerImp.class);

        when(staticFeaturesExtractor.extract(anyString(), anyString())).thenReturn(new ArrayList<Feature>());
        when(dynamicFeaturesExtractor.extract(anyString())).thenReturn(new ArrayList<Feature>());
        when(sparkHandler.HandleApplication(any(SparkApplication.class))).thenReturn(new SparkApplication());
        List<Feature> extractedFeatures = simulateExtractFeatures(new SparkApplication());
        when(featuresExtractionHandler.extract(any(SparkApplication.class))).thenReturn(extractedFeatures);
    }


    @Test
    public void extractAllFeaturesTest() {
        List<Feature> actual = featuresExtractionHandler.extract(new SparkApplication());
        Assert.assertEquals(new ArrayList<Feature>(), actual);
    }

    private List<Feature> simulateExtractFeatures(SparkApplication sparkApplication) {
        SparkApplication outputApplication = sparkHandler.HandleApplication(sparkApplication);
        String optimizedQueryPlan = outputApplication.getOptimizedQueryPlan();
        String physicalPlan = outputApplication.getPhysicalPlan();
        String stagesJson = outputApplication.getStagesJson();
        List<Feature> staticFeatures = staticFeaturesExtractor.extract(physicalPlan, optimizedQueryPlan);
        List<Feature> dynamicFeatures = dynamicFeaturesExtractor.extract(stagesJson);
        List<Feature> features = new ArrayList<Feature>();
        features.addAll(staticFeatures);
        features.addAll(dynamicFeatures);
        return features;
    }
}
