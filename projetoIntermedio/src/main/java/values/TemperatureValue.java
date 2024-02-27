package values;
import sensors.SensorOfTemperature;
/**
 * <p>Represents a specific value for temperature readings. Instances of this class
 * encapsulate a temperature value and provide methods to retrieve and represent
 * the temperature.</p>
 *
 * <p></p>
 *
 * @see Value
 * @see SensorOfTemperature
 */
public class TemperatureValue implements Value {

    private double temperatureValue;

    /**
     * Constructs a new instance of the TemperatureValue class
     * with the temperature value initialized to an undefined state.
     */
    public TemperatureValue() {
        this.temperatureValue = temperatureValue;
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
