package values;
/**
 * Represents a specific value for dew point measured by the SensorOfDewPoint. Instances of this class
 * encapsulate a dew point value and provide methods to retrieve and represent the dew point.
 * It implements the Value interface and provides functionality
 * to convert the dew point value to a string representation.
 * The actual numeric value for the dew point is stored in the dewPointValue attribute,
 * and the valueToString method converts this numeric value to a string representation.
 */
public class DewPointValue implements Value {

    /**
     * The actual numeric value for the dew point.
     */
    private double dewPointValue;

    /**
     * Constructs a new instance of the DewPointValue class
     * with the dew point value initialized to an undefined state.
     *
     * @param dewPointValue The dew point value to initialize the object, expressed in ºC.
     */
    public DewPointValue(double dewPointValue) {
        this.dewPointValue = dewPointValue;
    }


    /**
     * Converts the numeric dew point value to a string representation.
     *
     * @return A string representation of the dew point value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(this.dewPointValue);
    }
}
