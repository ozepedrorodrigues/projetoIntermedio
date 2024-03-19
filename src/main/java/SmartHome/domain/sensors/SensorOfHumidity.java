package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.HumidityValue;
import SmartHome.domain.sensors.values.Value;
/**
 * This class represents a humidity sensor.
 * It implements the Sensor interface and has a type.
 * The sensor has an ID, a type, and a value.
 * The ID can be set using the setID method.
 */
public class SensorOfHumidity implements Sensor {
    /**
     * The ID of the sensor.
     */
    private int id;
    /**
     * The SensorType of the sensor.
     */
    private SensorType type;

    /**
     * The default value of the sensor.
     */

    private Value DEFAULT = new HumidityValue(25.0);

    /**
     * Constructs a new SensorOfHumidity with a HumidityValue and sets the type to SensorType.HUMIDITY.
     */
    public SensorOfHumidity() {
        this.type = SensorType.HUMIDITY;
    }

    /**
     * Returns the ID of the sensor.
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
        return id = IdGenerator.generateSensorId();}

    /**
     * Returns the Sensortype of the sensor.
     *
     * @return the Sensortype of the sensor
     */
    public SensorType getType() {
        return type;

    }

    /**
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    public Value getValue() {
        return this.DEFAULT; // for now is a default value.
    }
}