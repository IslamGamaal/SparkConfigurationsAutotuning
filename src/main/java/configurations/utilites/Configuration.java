package configurations.utilites;

public class Configuration {

    private float value;
    private boolean isBoolean;
    private boolean isInteger;
    private String name;
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    
    public void setBoolean(boolean bool) {
        this.isBoolean = bool;
    }

    
    public boolean isInteger() {
        return isInteger;
    }

    
    public void setInteger(boolean bool) {
        this.isInteger = bool;
    }

    
    public float getValue() {
        return value;
    }

    
    public void setValue(float value) {
        this.value = value;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }


}
