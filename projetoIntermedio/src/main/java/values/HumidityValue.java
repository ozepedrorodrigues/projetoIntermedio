package values;


/**
 * <p>Represents a humidity value in a sensor system. This class implements
 * the Value interface, providing methods to convert the humidity value
 * to a string representation and perform equality checks.</p>
 *
 * <p></p>
 */
public class HumidityValue implements Value {

    private int humidityValue;

    /**
     * Constructs a new instance of the HumidityValue class.
     * with the humidity value initialized to an undefined state.
     */
    public HumidityValue() {
        this.humidityValue = humidityValue;
    }

    /**
     * Gets the humidity value as an integer.
     *
     * @return The humidity value.
     */
    public int getHumidityValue() {
        return humidityValue;
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
