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

    public List<Feature> extract(SparkApplication sparkApplication) {
        SparkHandler sparkHandler = new SparkHandlerImp();
        StaticFeaturesExtractor staticFeaturesExtractor = new StaticFeaturesExtractorImp();
        DynamicFeaturesExtractor dynamicFeaturesExtractor = new DynamicFeaturesExtractorImp();

        SparkApplication executedApplication = sparkHandler.HandleApplication(sparkApplication);
        return getFeatures(staticFeaturesExtractor, dynamicFeaturesExtractor, executedApplication);
    }

    public List<Feature> extract(List<Configuration> configurations, SparkApplication sparkApplication) {
        SparkHandler sparkHandler = new SparkHandlerImp();
        StaticFeaturesExtractor staticFeaturesExtractor = new StaticFeaturesExtractorImp();
        DynamicFeaturesExtractor dynamicFeaturesExtractor = new DynamicFeaturesExtractorImp();

        SparkApplication executedApplication = sparkHandler.HandleApplication(configurations, sparkApplication);
        return getFeatures(staticFeaturesExtractor, dynamicFeaturesExtractor, executedApplication);
    }

    private List<Feature> getFeatures(StaticFeaturesExtractor staticFeaturesExtractor, DynamicFeaturesExtractor dynamicFeaturesExtractor, SparkApplication executedApplication) {
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
