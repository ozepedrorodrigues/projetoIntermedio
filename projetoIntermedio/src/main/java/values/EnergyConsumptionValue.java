package values;

/**
 * This class represents the value of energy consumption.
 * It implements the Value interface, providing methods to convert the energy consumption value
 * to a string representation and perform equality checks.
 */
public class EnergyConsumptionValue implements Value {
    /**
     * The energy consumption value.
     */
    private double energyConsumptionValue;

    /**
     * Constructs a new instance of the EnergyConsumptionValue class.
     * with the energy consumption value initialized to an undefined state.
     *
     * @param energyConsumptionValue The energy consumption value.
     */
    public EnergyConsumptionValue(double energyConsumptionValue) {
        this.energyConsumptionValue = energyConsumptionValue;
    }

    /**
     * Converts the energy consumption value to a string representation.
     *
     * @return The string representation of the energy consumption value.
     */
    public String valueToString() {
        return String.valueOf(energyConsumptionValue);
    }
}
