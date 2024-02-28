package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

/**
 * Represents a sensor of type ON_OFF.
 */
public class SensorOfOnOff implements Sensor {

    private int id;
    private SensorType type;

    private Value value;
    private ValueFactory valueFactory;

    /**
     * Constructs a SensorOfOnOff instance using the provided ValueFactory.
     *
     * @param valueFactory The ValueFactory used to create the initial ON/OFF value for the sensor.
     * @throws IllegalArgumentException If the provided ValueFactory is null.
     */
    public SensorOfOnOff(ValueFactory valueFactory) throws IllegalArgumentException {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        this.type = SensorType.ON_OFF;
        this.valueFactory = valueFactory;
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
        this.value = valueFactory.createOnOffValue(true);
        return this.value;
    }

    /**
     * Checks if the provided ValueFactory is not null.
     *
     * @param valueFactory The ValueFactory to validate.
     * @return true if the ValueFactory is not null, false otherwise.
     */
    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }
}
