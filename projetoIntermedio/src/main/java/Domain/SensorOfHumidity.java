package Domain;
/**
 * This class represents a humidity sensor.
 * It implements the Sensor interface and has a name and a type.
 */

public class SensorOfHumidity implements Sensor {
    /**
     * The name of the sensor.
     */
    private String name;
    /**
     * The type of the sensor.
     */
    private String type = "Humidity";

    /**
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    private Value value;

    /**
     * Constructs a new SensorOfHumidity with the given name and type.
     *
     * @param name the name of the sensor
     */
    public SensorOfHumidity(String name) {
        this.value = new HumidityValue();
        this.name = name;
    }

    /**
     * Returns the name of the sensor.
     *
     * @return the name of the sensor
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    public String getType() {
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