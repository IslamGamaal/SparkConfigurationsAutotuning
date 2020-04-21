package configurations.utilites;

public class ConfigurationImpl implements Configuration{

    private float value;
    private boolean isBoolean;
    private boolean isInteger;
    private String name;

    @Override
    public boolean isBoolean() {
        return isBoolean;
    }

    @Override
    public void setBoolean(boolean bool) {
        this.isBoolean = bool;
    }

    @Override
    public boolean isInteger() {
        return isInteger;
    }

    @Override
    public void setInteger(boolean bool) {
        this.isInteger = bool;
    }

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


}
