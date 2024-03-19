package SmartHome.domain.actuators;

import SmartHome.domain.sensors.values.Value;

/**
 * This class represents a double value for actuators.
 */
public class ValueDouble implements Value {

    /**
     * The double value
     */
    private double value;

    /**
     * Constructs a ValueDouble object with the specified double value.
     *
     * @param value the double value to be stored
     */
    public ValueDouble(double value) {
        this.value = value;
    }

    /**
     * Converts the double value to its string representation.
     *
     * @return the string representation of the double value
     */
    @Override
    public String valueToString() {
        return String.valueOf(value);
    }
}
