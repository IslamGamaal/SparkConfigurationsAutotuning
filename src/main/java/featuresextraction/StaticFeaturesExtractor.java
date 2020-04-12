package featuresextraction;

import java.util.List;

public interface StaticFeaturesExtractor {
    /**
     * This function extracts the static features of the
     * application (before execution) from spark logs
     *
     * @param logicalPlan String: containing the optimized logical plan for a certain app.
     * @param physicalPlan String: containing the physical plan for a certain app.
     * @return List: containing static features extracted.
     **/
    List<Feature> extract(String physicalPlan, String logicalPlan);

}
