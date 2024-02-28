package values;

/**
 * This class represents the value of power consumption.
 * It implements the Value interface, providing methods to convert the power consumption value
 * to a string representation and perform equality checks.
 */
public class PowerConsumptionValue implements Value {
    private int powerConsumptionValue;

    /**
     * Constructs a new instance of the PowerConsumptionValue class.
     * with the power consumption value initialized to an undefined state.
     */
    public PowerConsumptionValue() {
        this.powerConsumptionValue = powerConsumptionValue;
    }

    /**
     * Gets the power consumption value as an integer.
     *
     * @return The power consumption value.
     */
    public int getPowerConsumptionValue() {
        return powerConsumptionValue;
    }

    /**
     * Converts the power consumption value to a string representation.
     *
     * @return The string representation of the power consumption value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(powerConsumptionValue);
    }
}
