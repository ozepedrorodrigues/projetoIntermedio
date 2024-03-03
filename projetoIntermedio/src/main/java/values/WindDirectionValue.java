package values;

/**
 * Represents a specific value of wind direction. Instances of this class
 * encapsulate a wind direction value and provide methods to retrieve and represent
 * the wind direction.
 */
public class WindDirectionValue implements Value {

    /**
     * The value of the sensor.
     */
    private  int windDirection;

    /**
     * Constructs a new instance of the WindDirectionValue class
     * with
     * the wind direction value initialized to an undefined state.
     * @param windDirection The wind direction value.
     */
    public WindDirectionValue(int windDirection) {
        this.windDirection = windDirection; // default value
    }


    /**
    * Converts the wind direction value to a string representation.
    * @return A string representation of the wind direction value.
     */
    public String valueToString() {
        return String.valueOf(windDirection);
    }
}
