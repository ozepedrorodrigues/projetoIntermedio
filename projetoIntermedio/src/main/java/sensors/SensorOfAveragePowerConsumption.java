package sensors;

import domain.SensorType;
import values.AveragePowerConsumptionValue;
import values.PowerConsumptionValue;
import values.Value;

public class SensorOfAveragePowerConsumption implements Sensor {
    private SensorType type;
    private int id;
    private Value DEFAULT = new AveragePowerConsumptionValue(1);

    /**
     * Constructs a new SensorOfAveragePowerConsumption with its type predefined as AVERAGE_POWER_CONSUMPTION.
     */
    public SensorOfAveragePowerConsumption() {
        this.type = SensorType.AVERAGE_POWER_CONSUMPTION;
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
     * Returns the sensor's type, specifically AVERAGE_POWER_CONSUMPTION for this implementation.
     *
     * @return the sensor type as {@link SensorType#AVERAGE_POWER_CONSUMPTION}.
     */
    @Override
    public SensorType getType() {
        return type;
    }

    /**
     * Retrieves the current value measured by the sensor.
     * n this implementation, it returns a default value.
     *
     * @return the current value of energy consumption as a {@link Value} object.
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }
}
