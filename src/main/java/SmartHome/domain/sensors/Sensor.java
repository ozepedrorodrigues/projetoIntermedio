package SmartHome.domain.sensors;

import SmartHome.domain.sensors.values.Value;

/**
 * This interface represents a generic sensor that can measure various physical quantities.
 * Implementing classes must provide functionality to retrieve the sensor type, current value,
 * and manage the sensor's unique identifier.
 */
public interface Sensor {

    /**
     * Returns the type of the sensor.
     *
     * @return The type of the sensor as defined by the SensorType enum.
     */
    SensorType getType();

    /**
     * Returns the current reading measured by the sensor.
     *
     * @return The current reading from the sensor represented by an implementation of the Value interface.
     */
    Value getValue();

    /**
     * Sets a new unique identifier for the sensor.
     *
     * @return The updated unique identifier for the sensor.
     */
    int generateId();

    /**
     * Retrieves the unique identifier of the sensor.
     *
     * @return The unique identifier of the sensor.
     */
    int getId();
}
