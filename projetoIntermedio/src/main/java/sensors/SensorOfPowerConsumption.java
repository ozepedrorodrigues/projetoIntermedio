package sensors;

import domain.SensorType;
import values.PowerConsumptionValue;
import values.Value;

public class SensorOfPowerConsumption implements Sensor {

    private SensorType type;
    private int id;
    private Value DEFAULT = new PowerConsumptionValue(1);

    /**
     * Constructs a new SensorOfPowerConsumption with its type predefined as POWER_CONSUMPTION.
     */
    public SensorOfPowerConsumption() {
                   this.type = SensorType.POWER_CONSUMPTION;
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
     * @param newId The new unique identifier to assign to the sensor.
     * @return the updated identifier for this sensor.
     */
    @Override
    public int setId(int newId) {
        return id = newId;
    }

    /**
     * Returns the sensor's type, specifically POWER_CONSUMPTION for this implementation.
     *
     * @return the sensor type as {@link SensorType#POWER_CONSUMPTION}.
     */
    @Override
    public SensorType getType() {
        return type;
    }

    /**
     * Retrieves the current value measured by the sensor.
     * In this implementation, it returns a default value.
     *
     * @return the current value of energy consumption as a {@link Value} object.
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }
}