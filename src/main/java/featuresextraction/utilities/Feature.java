package featuresextraction.utilities;

public class Feature {
    private SupportedFeatures featureName;
    private Object value;

    public Feature(SupportedFeatures featureName, Object value) {
        this.featureName = featureName;
        this.value = value;
    }

    public String getFeatureName() {
        return this.featureName.toString();
    }

    public Object getValue() {
        return this.value;
    }
}
