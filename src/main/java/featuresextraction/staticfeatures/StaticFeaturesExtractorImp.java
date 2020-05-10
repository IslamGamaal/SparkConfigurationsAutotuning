package featuresextraction.staticfeatures;

import featuresextraction.utilities.Feature;
import featuresextraction.utilities.SupportedFeatures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;

public class StaticFeaturesExtractorImp implements StaticFeaturesExtractor {
    public List<Feature> extract(String physicalPlan, String logicalPlan) {
        List<Feature> logicalPlanFeatures = parseLogicalPlan(logicalPlan);
        List<Feature> physicalPlanFeatures = parsePhysicalPlan(physicalPlan);
        List<Feature> staticFeatures = new ArrayList<>(logicalPlanFeatures);
        staticFeatures.addAll(physicalPlanFeatures);
        return staticFeatures;
    }


    private List<Feature> parseLogicalPlan(String logicalPlan) {
        List<String> logicalPlanLines = new ArrayList<>();
        String[] lines = logicalPlan.split("\n");
        for (String line: lines) {
            logicalPlanLines.add(line);
        }
        List <String> formattedLines = formatLines(logicalPlanLines);
        List <String> transformations = extractTransformations(formattedLines);
        Map <String, Integer> transformationsMap = listToMap(transformations);

        boolean aggregation = isAggregate(transformations);
        boolean projection = isProject(transformationsMap);
        boolean join = isJoin(transformationsMap);

        return createFeaturesList(aggregation, projection, join);
    }

    private List<Feature> parsePhysicalPlan(String physicalPlan) {
        return new ArrayList<>();

    }

    private List<String> extractTransformations(List<String> formattedLines) {
        List<String> transformations = new ArrayList<>();
        List<String> lineElementsList = new ArrayList<>();
        for (String line : formattedLines) {
            String[] lineElements = line.split(" ");
            lineElementsList.addAll(new ArrayList<>(Arrays.asList(lineElements)));
            transformations.add(lineElements[0].toLowerCase());
        }
        return transformations;
    }

    private Map<String, Integer> listToMap (List<String> transformations) {
        Map<String, Integer> freq = new HashMap<>();
        for (String item: transformations) {
            if (freq.containsKey(item)) {
                freq.put(item, freq.get(item)+1);
            }
            else {
                freq.put(item, 1);
            }
        }
        return freq;
    }

    private boolean isAggregate(List<String> transformations) {
        while (transformations.get(0).equals("sort") || transformations.get(0).contains("limit")) {
            transformations.remove(0);
        }
        if (transformations.get(0).contains("aggregate")) {
            return true;
        }
        return false;
    }

    private boolean isProject(Map<String, Integer> transformationsMap) {
        for (Map.Entry<String, Integer> entry: transformationsMap.entrySet()) {
            if (entry.getKey().contains("project")) {
                return true;
            }
        }
        return false;
    }

    private boolean isJoin(Map<String, Integer> transformationsMap) {
        for (Map.Entry<String, Integer> entry: transformationsMap.entrySet()) {
            if (entry.getKey().contains("join")) {
                return true;
            }
        }
        return false;
    }

    private List<String> formatLines(List<String> lines) {
        List<String> formattedLines = new ArrayList<>();
        for (String line: lines) {
            line = line.replace('+', ' ');
            line = line.replace('-', ' ');
            line = line.replace('(', ' ');
            line = line.replace(')', ' ');
            line = line.replace('=', ' ');
            line = line.replace(',', ' ');
            line = line.replace(':', ' ');
            line = line.replace('[', ' ');
            line = line.replace(']', ' ');
//            #line = line.replace(' +', )
            line = line.trim();
            formattedLines.add(line);
        }
        return formattedLines;
    }

    private List<Feature> createFeaturesList(boolean aggregation, boolean projection, boolean join) {
        List<Feature> features = new ArrayList<>();
        features.add(new Feature(SupportedFeatures.IS_AGGREGATE, aggregation));
        features.add(new Feature(SupportedFeatures.IS_PROJECTION, projection));
        features.add(new Feature(SupportedFeatures.IS_JOIN, join));
        return features;
    }
}
