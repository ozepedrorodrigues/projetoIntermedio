package sensors;


import domain.SensorType;
import factories.ValueFactory;
import values.TemperatureValue;
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
     * The default value of the sensor.
     */
    private Value DEFAULT = new TemperatureValue(25.0);


    /**
     * Constructor for the SensorOfTemperature class.
     */
    public SensorOfTemperature() {
            this.type = SensorType.TEMPERATURE;
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
     * @return the ID of the sensor
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
        return this.DEFAULT; // for now is a default value.
    }

}