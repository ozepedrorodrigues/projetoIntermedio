package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;
import SmartHome.domain.sensors.values.TemperatureValue;
/**
 * This class represents a temperature sensor.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.*/
public class SensorOfTemperature implements Sensor {
    /**
     * The ID of the sensor.
     */
    private int id;

    /**
     * The Sensortype of the sensor.
     */
    private SensorType type;

    /**
     * The default value of the sensor.
     */
    private Value DEFAULT = new TemperatureValue(25.0);

    /**
     * Constructor for the SensorOfTemperature class.
     */
    public SensorOfTemperature() {
            this.type = SensorType.TEMPERATURE;
    }

    /**
     * Returns the ID of the sensor.
     *
     * @return the ID of the sensor
     */
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
     * Returns the type of the sensor.
     * @return the type of the sensor
     */
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the value of the sensor.
     * @return the value of the sensor
     */
    public Value getValue() {
        return this.DEFAULT; // for now is a default value.
    }

}