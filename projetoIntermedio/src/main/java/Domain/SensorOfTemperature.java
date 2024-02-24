package Domain;


import Factories.ValueFactory;

/**
 * This class represents a temperature sensor.
 * It implements the Sensor interface and has a name and a type.
 */
public class SensorOfTemperature implements Sensor {
    /**
     * The name of the sensor.
     */
    private Integer id;

    /**
     * The type of the sensor, which is set to "Temperature" by default.
     */
    private SensorType type;

    /**
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    private Value value;

    /**
     * Constructs a new SensorOfTemperature with the given name.
     */
    public SensorOfTemperature(ValueFactory valueFactory) {
        this.type = SensorType.TEMPERATURE;
        this.value = valueFactory.createTemperatureValue(type);
        id = null;
    }

    /**
     * Returns the name of the sensor.
     *
     * @return the name of the sensor
     */
    public int getId() {
        return id;
    }
    public void setId (Integer newId) {
        id = newId;
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
}