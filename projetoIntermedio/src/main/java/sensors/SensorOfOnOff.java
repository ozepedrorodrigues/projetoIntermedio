package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.OnOffValue;
import values.Value;

/**
 * Represents a sensor of type ON_OFF.
 */
public class SensorOfOnOff implements Sensor {

    private int id;
    private SensorType type;

    private final Value DEFAULT = new OnOffValue(true);

    /**
     * Constructs a SensorOfOnOff instance using the provided ValueFactory.
     */
    public SensorOfOnOff() {
        this.type = SensorType.ON_OFF;
    }

    /**
     * Retrieves the ID of the sensor.
     *
     * @return The current ID of the sensor.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the sensor to the specified value.
     *
     * @param newId The new ID to set for the sensor.
     * @return The updated ID after the operation.
     */
    @Override
    public int setId(int newId) {
        this.id = newId;
        return this.id;
    }

    /**
     * Retrieves the type of the sensor, which is ON_OFF.
     *
     * @return The SensorType.ON_OFF enumeration representing the sensor type.
     */
    @Override
    public SensorType getType() {
        return this.type;
    }

    /**
     * Retrieves the current ON/OFF value of the sensor.
     *
     * @return The current ON/OFF value as a Value object.
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }
}
