package featuresextraction;

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
        //submitting application to spark to be runned once and returns the logs.
        SparkApplication executedApplication = sparkHandler.HandleApplication(sparkApplication);
        //extracting the static features from the static feature extractor
        String physicalPlan = executedApplication.getPhysicalPlan();
        String optimizedLogicalPlan =  executedApplication.getOptimizedQueryPlan();
        List<Feature> staticFeatures = staticFeaturesExtractor.extract(physicalPlan, optimizedLogicalPlan);
        //extracting the dynamic features from the dynamic features extractor
        String stagesJson = executedApplication.getStagesJson();
        List<Feature> dynamicFeatures = dynamicFeaturesExtractor.extract(stagesJson);
        //merging the static and dynamic features into one list
        List<Feature> allExtractedfeatures = Stream.concat(staticFeatures.stream(), dynamicFeatures.stream())
                .collect(Collectors.toList());
        return allExtractedfeatures;
    }
}
