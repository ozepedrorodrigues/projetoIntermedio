package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.PowerConsumptionValue;
import SmartHome.domain.sensors.values.Value;

/**
 * Represents a sensor that measures power consumption within a smart home environment.
 * This sensor tracks the amount of power consumed and provides the information as a {@link PowerConsumptionValue}.
 */
public class SensorOfPowerConsumption implements Sensor {

    /**
     * The unique identifier for this sensor.
     */
    private int id;

    /**
     * The type of sensor, specifically for power consumption monitoring.
     */
    SensorType type;

    /**
     * The default value for power consumption, initialized to 15.0 watts.
     */
    private Value DEFAULT = new PowerConsumptionValue(15.0);

    /**
     * Constructs a new SensorOfPowerConsumption with a default type set to POWER_CONSUMPTION.
     */
    public SensorOfPowerConsumption() {
        this.type = SensorType.POWER_CONSUMPTION;
    }

    /**
     * Returns the type of sensor.
     *
     * @return The sensor type as {@link SensorType#POWER_CONSUMPTION}.
     */
    @Override
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the current value of power consumption.
     *
     * @return The default power consumption value.
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }

    /**
     * Generates a new ID for this sensor.
     *
     * @return the updated identifier for this sensor.
     */
    @Override
    public int generateId() {
        return id = IdGenerator.generateSensorId();
    }

    /**
     * Retrieves the unique identifier of this sensor.
     *
     * @return The ID of the sensor.
     */
    @Override
    public int getId() {
        return this.id;
    }
}
