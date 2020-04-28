package featuresextraction;

import featuresextraction.utilities.Feature;
import spark.utilites.SparkApplication;

import java.util.List;

public interface FeaturesExtractionHandler {
    /**
     * This function extracts all possible static and dynamic features from a
     * given application
     *
     * @param sparkApplication SparkApplication: Spark Application.
     * @return List: containing all features extracted
     **/
    List<Feature> extract(SparkApplication sparkApplication);
}

/*
    Create application class containing -> jar , dataset, ...
 */