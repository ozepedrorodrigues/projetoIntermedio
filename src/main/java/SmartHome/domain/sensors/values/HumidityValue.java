package SmartHome.domain.sensors.values;


/**
 * <p>Represents a humidity value in a sensor system. This class implements
 * the Value interface, providing methods to convert the humidity value
 * to a string representation and perform equality checks.</p>
 *
 * <p></p>
 */
public class HumidityValue implements Value {

    /**
     * The value of the sensor.
     */

    private double humidityValue;

    /**
     * Constructs a new instance of the HumidityValue class
     * with the humidity value initialized to an undefined state.
     *
     * @param humidityValue The humidity value.
     */
    public HumidityValue(double humidityValue) {
        this.humidityValue = humidityValue;
    }

    /**
     * Converts the humidity value to a string representation.
     *
     * @return The string representation of the humidity value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(humidityValue);
    }
}
