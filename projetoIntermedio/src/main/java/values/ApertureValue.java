package values;

import values.Value;

public class ApertureValue implements Value {
    private float apertureValue;
    public ApertureValue() {
        this.apertureValue = apertureValue;}

    public float getApertureValue() {
        return apertureValue;}

    @Override
    public String toValue() {
        return "ApertureValue{"+ "value=" + "}";
    }
}
