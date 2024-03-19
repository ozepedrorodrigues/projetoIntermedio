package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;
import SmartHome.domain.sensors.values.WindValue;

/**
 * This class represents a sensor of wind.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.WIND and the value is a WindValue.
 * The ID can be set using the setID method.
 */
public class SensorOfWind implements Sensor {

    /**
     * The ID of the sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     */
    private SensorType type;

    /**
     * The default value of the sensor.
     */
    private Value DEFAULT = new WindValue(Math.PI/2, 25.0);

    /**
     * Constructor for the SensorOfWind class.
     */
    public SensorOfWind() {
        this.type = SensorType.WIND;
    }

    /**
     * Returns the ID of the sensor.
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
     */
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the value of the sensor.
     */
    public Value getValue() {
        return this.DEFAULT;
    }
}
