package values;

/**
 * This class represents the value of average power consumption.
 * It implements the Value interface, providing methods to convert the average power consumption value
 * to a string representation and perform equality checks.
 */
public class AveragePowerConsumptionValue implements Value {
    /**
     * The average power consumption value.
     */
    private double averagePowerConsumptionValue;

    /**
     * Constructs a new instance of the AveragePowerConsumptionValue class.
     * with the average power consumption value initialized to an undefined state.
     *
     * @param averagePowerConsumptionValue The average power consumption value.
     */
    public AveragePowerConsumptionValue(double averagePowerConsumptionValue) {
        this.averagePowerConsumptionValue = averagePowerConsumptionValue;
    }

    /**
     * Converts the average power consumption value to a string representation.
     *
     * @return The string representation of the average power consumption value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(averagePowerConsumptionValue);
    }
}
