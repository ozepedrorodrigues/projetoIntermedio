package values;

public class PositionScaleValue implements Value{
    private int positionScaleValue;

    public PositionScaleValue() {
        this.positionScaleValue = positionScaleValue;
    }

    public int getPositionScaleValue() {
        return positionScaleValue;
    }

    @Override
    public String toValue() {
        return "Position Value{" + "value=" + positionScaleValue + "%}";
    }
}
