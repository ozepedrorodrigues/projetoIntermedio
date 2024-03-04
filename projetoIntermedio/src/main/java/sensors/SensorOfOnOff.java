package sensors;

import domain.SensorType;
import values.OnOffValue;
import values.Value;

/**
 * This class represents a binary sensor that measures ON/OFF values.
 */
public class SensorOfOnOff implements Sensor {

    /**
     * The unique identifier of the sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     */
    private SensorType type;

    /**
     * The default ON/OFF value of the sensor.
     * This value is used when the sensor is not connected to a physical device.
     */
    private final Value DEFAULT = new OnOffValue(true);

    /**
     * Creates a new instance of the SensorOfOnOff class.
     */
    public SensorOfOnOff() {
        this.type = SensorType.ON_OFF;
    }

    /**
     * Retrieves the unique identifier of the sensor.
     *
     * @return The unique identifier of the sensor.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Sets a new unique identifier for the sensor.
     *
     * @param newId The new unique identifier to assign to the sensor.
     * @return The updated unique identifier for the sensor.
     */
    @Override
    public int setId(int newId) {
        this.id = newId;
        return this.id;
    }

    /**
     * Retrieves the type of the sensor.
     *
     * @return The type of the sensor as defined by the SensorType enum.
     */
    @Override
    public SensorType getType() {
        return this.type;
    }

    /**
     * Returns the current reading measured by the sensor.
     *
     * @return The current reading from the sensor represented by an implementation of the
     * Value interface.
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }
}
