package sensors;


import domain.SensorType;
import factories.ValueFactory;
import values.Value;

/**
 * This class represents a temperature sensor.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.TEMPERATURE, and the value is a TemperatureValue.
 * The ID can be set using the setID method.
 */
public class SensorOfTemperature implements Sensor {
    /**
     * The ID of the sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     */
    private SensorType type;

    /**
     * The value of the sensor.
     */
    private Value value;

    /**
     * The value factory of the sensor.
     */
    private ValueFactory valueFactory;

    /**
     * Constructor
     *
     * @param valueFactory the ValueFactory of the sensor.
     * @throws IllegalArgumentException if the valueFactory is invalid.
     */
    public SensorOfTemperature(ValueFactory valueFactory) throws IllegalArgumentException {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.TEMPERATURE;
            this.valueFactory = valueFactory;
        }
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
     * Sets the ID of the sensor.
     *
     * @param newId the new ID of the sensor
     */
    public int setId (int newId) {
        this.id = newId;
        return id;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the value of the sensor.
     *
     * @return the value of the sensor
     */
    public Value getValue() {
        this.value = valueFactory.createTemperatureValue();
        return value;
    }

    /**
     * Verifies if the value factory is valid.
     *
     * @param valueFactory the value factory to verify
     * @return true if the value factory is valid, false otherwise
     */
    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }
}