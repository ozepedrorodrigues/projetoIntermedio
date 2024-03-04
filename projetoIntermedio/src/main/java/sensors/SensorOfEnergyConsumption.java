package sensors;

import domain.SensorType;
import values.EnergyConsumptionValue;
import values.Value;

/**
 * Represents a sensor dedicated to measuring energy consumption.
 * This sensor is a specific implementation of the {@code Sensor} interface, focusing on energy consumption metrics.
 */
public class SensorOfEnergyConsumption implements Sensor {
    /** The type of sensor, set to ENERGY_CONSUMPTION. */
    private SensorType type;
    /** The unique identifier for this sensor. */
    private int id;
    /** The default value for this sensor, representing a base energy consumption measurement. */
    private Value DEFAULT = new EnergyConsumptionValue(1);

    /**
     * Constructs a new SensorOfEnergyConsumption with its type predefined as ENERGY_CONSUMPTION.
     */
    public SensorOfEnergyConsumption() {
        this.type = SensorType.ENERGY_CONSUMPTION;
    }

    /**
     * Returns the sensor's ID.
     *
     * @return the unique identifier for this sensor.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets a new ID for this sensor.
     *
     * @param newId the new identifier for the sensor.
     * @return the updated identifier for this sensor.
     */
    @Override
    public int setId(int newId) {
        return id = newId;
    }

    /**
     * Returns the sensor's type, specifically ENERGY_CONSUMPTION for this implementation.
     *
     * @return the sensor type as {@link SensorType#ENERGY_CONSUMPTION}.
     */
    @Override
    public SensorType getType() {
        return type;
    }

    /**
     * Retrieves the current value measured by the sensor. In this implementation, it returns a default value.
     *
     * @return the current value of energy consumption as a {@link Value} object.
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }
}
