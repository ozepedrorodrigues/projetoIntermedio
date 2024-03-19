package SmartHome.domain.sensors.values;

/**
 * This class represents a wind value.
 * It implements the Value interface.
 * The value has a wind direction and a wind speed.
 */

public class WindValue implements Value {

    /**
     * The wind direction of the value.
     */
    private double windDirection;

    /**
     * The wind speed of the value.
     */
    private double windSpeed;

    /**
     * Constructor for the WindValue class.
     *
     * @param windDirection the wind direction of the value
     * @param windSpeed the wind speed of the value
     */
    public WindValue(double windDirection, double windSpeed) {
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    /**
     * Converts the wind value to a string representation.
     *
     * @return A string representation of the wind value.
     */
    public String valueToString() {
        return "Wind Direction: " + windDirection + ", Wind Speed: " + windSpeed;
    }
}
