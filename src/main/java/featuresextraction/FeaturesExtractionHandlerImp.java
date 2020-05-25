package featuresextraction;

import configurations.utilites.Configuration;
import featuresextraction.dynamicfeatures.DynamicFeaturesExtractor;
import featuresextraction.dynamicfeatures.DynamicFeaturesExtractorImp;
import featuresextraction.staticfeatures.StaticFeaturesExtractor;
import featuresextraction.staticfeatures.StaticFeaturesExtractorImp;
import featuresextraction.utilities.Feature;
import spark.SparkHandler;
import spark.SparkHandlerImp;
import spark.utilites.SparkApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FeaturesExtractionHandlerImp implements FeaturesExtractionHandler {
    SparkHandler sparkHandler;
    StaticFeaturesExtractor staticFeaturesExtractor;
    DynamicFeaturesExtractor dynamicFeaturesExtractor;

    public FeaturesExtractionHandlerImp() {
        sparkHandler = new SparkHandlerImp();
        staticFeaturesExtractor = new StaticFeaturesExtractorImp();
        dynamicFeaturesExtractor = new DynamicFeaturesExtractorImp();
    }

    public List<Feature> extract(SparkApplication sparkApplication) {
        SparkApplication executedApplication = sparkHandler.HandleApplication(sparkApplication);
        if (executedApplication == null)
            return null;
        return getFeatures(executedApplication);
    }

    public List<Feature> extract(List<Configuration> configurations, SparkApplication sparkApplication) {
        SparkApplication executedApplication = sparkHandler.HandleApplication(configurations, sparkApplication);
        if (executedApplication == null)
            return null;
        return getFeatures(executedApplication);
    }

    private List<Feature> getFeatures(SparkApplication executedApplication) {
        String physicalPlan = executedApplication.getPhysicalPlan();
        String optimizedLogicalPlan =  executedApplication.getOptimizedQueryPlan();
        List<Feature> staticFeatures = staticFeaturesExtractor.extract(physicalPlan, optimizedLogicalPlan);
        String stagesJson = executedApplication.getStagesJson();
        List<Feature> dynamicFeatures = dynamicFeaturesExtractor.extract(stagesJson);
        List<Feature> allExtractedfeatures = Stream.concat(staticFeatures.stream(), dynamicFeatures.stream())
                .collect(Collectors.toList());
        return allExtractedfeatures;
    }
}
