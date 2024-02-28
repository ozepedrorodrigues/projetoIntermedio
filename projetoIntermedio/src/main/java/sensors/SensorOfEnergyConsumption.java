package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

/**
 * This class represents a sensor that measures energy consumption.
 * It implements the Sensor interface and contains an id, a type, and a value.
 */
public class SensorOfEnergyConsumption implements Sensor {

    /*
     * The id of the sensor
     */
    private int id;
    /*
     * The type of the sensor
     */
    private SensorType type;
    /*
     * The value of the sensor
     */
    private Value value;

    /**
     * Constructs a new SensorOfEnergyConsumption with the specified ValueFactory.
     *
     * @param valueFactory the ValueFactory to create the sensor's value.
     * @throws IllegalArgumentException if the provided ValueFactory is null.
     */
    public SensorOfEnergyConsumption(ValueFactory valueFactory) throws IllegalArgumentException {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.ENERGY_CONSUMPTION;
            this.value = valueFactory.createEnergyConsumptionValue();
            this.id = 0;
        }
    }

    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }

    @Override
    public SensorType getType() {
        return type;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int setId(int newId) {
        this.id = newId;
        return id;
    }
}

