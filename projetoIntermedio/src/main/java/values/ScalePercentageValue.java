package values;

/**
 * Represents a specific value for scale percentage readings. Instances of this class
 * encapsulate a scale percentage value and provide methods to retrieve and represent
 * the scale percentage.
 */
public class ScalePercentageValue implements Value {

    /**
     * The value of the sensor.
     */
    private int percentageValue;

    /**
     * Constructor for the ScalePercentageValue class.
     */
    public ScalePercentageValue() {
        this.percentageValue = 0;       // 0% by default, for now.
    }


    /**
     * Returns the percentage value of the sensor.
     */
    public int getPercentageValue() {
        return percentageValue;
    }

    /**
     * Converts the value to a string representation.
     *
     * @return A string representation of the value.
     */
    @Override
    public String valueToString () {
        return String.valueOf(percentageValue);
    }
}
