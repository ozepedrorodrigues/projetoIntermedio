package sensors;

import domain.SensorType;
import values.Value;
import values.WindSpeedValue;

/**
 * This class represents a sensor of wind speed.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.WIND_SPEED, and the value is a WindSpeedValue.
 * The ID can be set using the setID method.
 */
public class SensorOfWindSpeed implements Sensor {

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
    private Value DEFAULT = new WindSpeedValue(25.0);

    /**
     * Constructor for the SensorOfWindSpeed class.
     */

    public SensorOfWindSpeed() {
        this.type = SensorType.WIND_SPEED;
    }

    /**
     * Returns the ID of the sensor.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the sensor.
     * @param newId the new ID of the sensor
     * @return the new ID of the sensor
     */
    public int setId(int newId) {
        this.id = newId;
        return id;
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
        ;
        return this.DEFAULT;// for now is a default value.
    }

}

