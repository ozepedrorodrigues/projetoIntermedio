package values;
import sensors.SensorOfTemperature;
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
     */
    public TemperatureValue() {
        this.temperatureValue = 25; // 25 degrees Celsius, for now is a default value.
    }

    /**
     * Gets the current temperature value.
     *
     * @return The current temperature value.
     */
    public double getTemperatureValue() {
        return temperatureValue;
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
