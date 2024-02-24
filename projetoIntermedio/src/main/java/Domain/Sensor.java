package Domain;

/**
 * This interface represents a generic sensor.
 * It has methods to get the type, name, and current reading of the sensor.
 */

public interface Sensor {
    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    String getType();

    /**
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    Value getValue();
}
