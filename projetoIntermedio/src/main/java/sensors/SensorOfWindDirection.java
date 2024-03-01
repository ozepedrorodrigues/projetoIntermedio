package sensors;

import domain.SensorType;
import values.Value;
import values.WindDirectionValue;

/**
 * This class represents a sensor of wind direction
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.WIND_DIRECTION, and the value is a WindDirectionValue.
 * The ID can be set using the setID method.
 */

public class SensorOfWindDirection implements Sensor {

    /**
     * The id of the sensor
     */
    private int id;

    /**
     * The type of the sensor
     */
    private SensorType type;

    /**
     * The default value of the sensor
     */
    private Value DEFAULT = new WindDirectionValue(315);

    /**
     * Constructor for the SensorOfWindDirection class
     */
    public SensorOfWindDirection() {
        this.type = SensorType.WIND_DIRECTION;
    }

    /**
     * Returns the ID of the sensor
     *
     * @return the ID of the sensor
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the sensor
     *
     * @param newId the new ID of the sensor
     * @return the ID of the sensor
     */
    public int setId(int newId) {
        return id = newId;
    }

    /**
     * Returns the type of the sensor
     *
     * @return the type of the sensor
     */
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the value of the sensor
     *
     * @return the value of the sensor
     */
    public Value getValue() {
        return this.DEFAULT; // for now is a default value.
    }
}

