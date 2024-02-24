package Domain;

/**
 * This class represents a humidity sensor.
 * It implements the Sensor interface and has a type.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.HUMIDITY, and the value is a HumidityValue.
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
    public SensorOfHumidity() {
        this.value = new HumidityValue();
        this.type = SensorType.HUMIDITY;
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
     * Sets the ID of the sensor.
     *
     * @param id the new ID of the sensor
     */
    public int setID(int id) {
        this.id = id;
        return id;
    }
}