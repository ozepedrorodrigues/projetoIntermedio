package values;

/**
 * Represents a specific value for wind speed readings. Instances of this class
 * encapsulate a wind speed value and provide methods to retrieve and represent
 * the wind speed.
 */

public class WindSpeedValue implements Value {

    /**
     * The value of the sensor.
     */
    private double windSpeed;

    /**
     * Constructs a new instance of the WindSpeedValue class
     * with the wind speed value initialized to an undefined state.
     *
     * @param windSpeed The wind speed value.
     */

    public WindSpeedValue(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Gets the current wind speed value.
     *
     * @return The current wind speed value.
     */

    @Override
    public Double getValue() {
        return windSpeed;
    }

    /**
     * Converts the wind speed value to a string representation.
     *
     * @return A string representation of the wind speed value.
     */

    public String valueToString() {
        return String.valueOf(windSpeed);
    }
}
