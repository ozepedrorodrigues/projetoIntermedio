package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.DewPointValue;
import SmartHome.domain.sensors.values.Value;

/**
 * This class represents a specific type of sensor that measures dew point.
 * It implements the Sensor interface and provides functionality to retrieve from the sensor type,
 * current dew point reading and the sensor's unique identifier (ID).
 * The type is always SensorType.DEW_POINT, and the reading is a DewPointValue which,
 * for the time being, is set to a default value.
 * The ID can be set using the setID method.
 */
public class SensorOfDewPoint implements Sensor {
    /**
     * The unique identifier for the sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     */
    private SensorType type;

    /**
     * The default value of the sensor's reading.
     */
    private Value DEFAULT = new DewPointValue(17.0);

    /**
     * Default constructor for a dew point sensor.
     * Initializes the sensor type to DEW_POINT.
     */
    public SensorOfDewPoint() {
            this.type = SensorType.DEW_POINT;
    }

    /**
     * Retrieves the unique identifier of the dew point sensor.
     *
     * @return The unique identifier of the dew point sensor.
     */
    public int getId() {
        return id;
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
     * Gets the type of the dew point sensor.
     *
     * @return The type of the dew point sensor as defined by the SensorType enum.
     */
    public SensorType getType() {
        return type;
    }

    /**
     * Retrieves the current dew point value measured by the sensor.
     *
     * @return The current dew point value of the sensor represented by an implementation of the Value interface.
     */
    public Value getValue() {
        return this.DEFAULT; // For the time being, this value is set to a default value.
    }
}