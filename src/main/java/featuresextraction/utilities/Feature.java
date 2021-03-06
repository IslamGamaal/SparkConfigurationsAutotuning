package featuresextraction.utilities;

public class Feature {
    private SupportedFeatures featureName;
    private Object value;
    private String valueType;
    //float ,int, array of float

    public Feature(SupportedFeatures featureName, Object value , String valueType) {
        this.featureName = featureName;
        this.value = value;
        this.valueType = valueType;
    }

    public String getFeatureName() {
        return this.featureName.toString();
    }

    public Object getValue() {
        return this.value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
