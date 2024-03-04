package values;

/**
 * Represents a value specifically for a position scale.
 * This class implements the {@code Value} interface, allowing position scale values to be handled
 * within a standardized framework for values.
 */
public class PositionScaleValue implements Value {
    /** The value representing the position scale. */
    private int positionScaleValue;

    /**
     * Constructs a new PositionScaleValue with the specified position scale value.
     *
     * @param positionScaleValue the integer value to represent the position scale.
     */
    public PositionScaleValue(int positionScaleValue) {
        this.positionScaleValue = positionScaleValue;
    }

    /**
     * Converts the position scale value to a string.
     *
     * @return the position scale value as a string.
     */
    @Override
    public String valueToString() {
        return String.valueOf(positionScaleValue);
    }
}
