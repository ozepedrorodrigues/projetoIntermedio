package values;

public class PositionScaleValue implements Value{
    private int positionScaleValue;

    public PositionScaleValue(int positionScaleValue) {
        this.positionScaleValue = positionScaleValue;
    }

    @Override
    public String valueToString() {
        return String.valueOf(positionScaleValue);}
    }