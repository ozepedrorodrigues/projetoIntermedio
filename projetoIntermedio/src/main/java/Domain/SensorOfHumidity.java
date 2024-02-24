package Domain;

import Factories.ValueFactory;

/**
 * This class represents a humidity sensor.
 * It implements the Sensor interface and has a type.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType. The value is a HumidityValue.
 * The ID can be set using the setID method.
 */
public class SensorOfHumidity implements Sensor {
    private int id;
    /**
     * The type of the sensor.
     */
    private SensorType type;

    /**
     * The current reading from the sensor.
     */
    private Value value;

    /**
     * Constructs a new SensorOfHumidity with a HumidityValue and sets the type to SensorType.HUMIDITY.
     */
    public SensorOfHumidity(ValueFactory valueFactory) {
        this.type = SensorType.HUMIDITY;
        this.value = valueFactory.createHumidityValue(type);
        this.id = 0;

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
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    public Value getValue() {
        return value;
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
     * @param id the new ID of the sensor
     */
    public int setId(int id) {
        this.id = id;
        return id;}


}