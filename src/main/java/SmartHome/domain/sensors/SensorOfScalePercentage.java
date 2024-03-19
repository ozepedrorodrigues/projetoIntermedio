package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.ScalePercentageValue;
import SmartHome.domain.sensors.values.Value;

/**
 * This class represents a scale percentage sensor.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.SCALE_PERCENTAGE, and the value is a ScalePercentageValue.
 */
public class SensorOfScalePercentage implements Sensor {

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
    private Value DEFAULT = new ScalePercentageValue(50);

    /**
     * Constructor for the SensorOfScalePercentage class.
     *
     * @throws IllegalArgumentException if the valueFactory is invalid.
     */
    public SensorOfScalePercentage() {
        this.type = SensorType.SCALE_PERCENTAGE;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    @Override
    public SensorType getType() {
        return type;
    }

    /**
     * Generates a new ID for this sensor.
     *
     * @return the updated identifier for this sensor.
     */
    @Override
    public int generateId() {
        return id = IdGenerator.generateSensorId();
    }

    /**
     * Returns the ID of the sensor
     *
     * @return the ID of the sensor
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    @Override
    public Value getValue() {
        return this.DEFAULT;
    }


}
