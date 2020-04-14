package featuresextraction;

public class Feature {
    private String featureName;
    private double value;

    public Feature(String featureName, double value) {
        this.featureName = featureName;
        this.value = value;
    }

    public String getFeatureName() {
        return this.featureName;
    }

    public double getValue() {
        return this.value;
    }
}
