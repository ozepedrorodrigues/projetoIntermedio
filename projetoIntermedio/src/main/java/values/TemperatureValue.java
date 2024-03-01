package values;
/**
 * Represents a specific value for temperature readings. Instances of this class
 * encapsulate a temperature value and provide methods to retrieve and represent
 * the temperature.
 */
public class TemperatureValue implements Value {

    /**
     * The value of the sensor.
     */
    private double temperatureValue;

    /**
     * Constructs a new instance of the TemperatureValue class
     * with the temperature value initialized to an undefined state.
     *
     * @param temperatureValue The temperature value.
     */
    public TemperatureValue(double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }


    /**
     * Converts the temperature value to a string representation.
     *
     * @return A string representation of the temperature value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(temperatureValue);
    }
}
