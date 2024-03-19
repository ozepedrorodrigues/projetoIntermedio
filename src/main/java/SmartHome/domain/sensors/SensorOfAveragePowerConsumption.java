package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.AveragePowerConsumptionValue;
import SmartHome.domain.sensors.values.Value;

/**
 * Represents a sensor that measures average power consumption within a smart home environment.
 * This sensor tracks the average amount of power consumed and provides the information as a {@link AveragePowerConsumptionValue}.
 */
public class SensorOfAveragePowerConsumption implements Sensor {

    /**
     * The unique identifier for this sensor.
     */
    private int id;

    /**
     * The type of sensor, specifically for average power consumption monitoring.
     */
    SensorType type;

    /**
     * The default value for average power consumption, initialized to 1 watt.
     */
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
     * Generates a new ID for this sensor.
     *
     * @return the updated identifier for this sensor.
     */
    @Override
    public int generateId() {
        return id = IdGenerator.generateSensorId();
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