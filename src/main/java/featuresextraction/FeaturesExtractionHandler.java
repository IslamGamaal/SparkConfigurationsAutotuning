package featuresextraction;

import java.util.List;

public interface FeaturesExtractionHandler {
    /**
     * This function extracts all possible static and dynamic features from a
     * given application
     *
     * @param pathToApplicationJar String: the absolute path to the wanted application jar.
     * @return List: containing all features extracted
     **/
    List<Double> extract(String pathToApplicationJar);
}

/*
    Create application class containing -> jar , dataset, ...
 */