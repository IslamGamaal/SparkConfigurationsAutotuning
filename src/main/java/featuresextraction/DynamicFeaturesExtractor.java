package featuresextraction;

import java.util.List;

public interface DynamicFeaturesExtractor {
    /**
     * This function extracts the dynamic features of the
     * application (after a single run) from spark history server logs
     *
     * @param stagesJson String: json containing the response to the History server API.
     * @return List of Features: containing dynamic features extracted.
     **/
    List<Feature> extract(String stagesJson);
}
