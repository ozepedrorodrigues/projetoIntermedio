package Domain;


import Factories.GPSLocationFactory;
import Factories.ValueFactory;

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
     * Constructor
     *
     * @param valueFactory the ValueFactory of the sensor.
     */
    public SensorOfTemperature(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.TEMPERATURE;
            this.value = valueFactory.createTemperatureValue(type);
            this.id = 0;
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
     * Returns the value from the sensor.
     *
     * @return the value from the sensor
     */
    public Value getValue() {
        return value;
    }

    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }
}