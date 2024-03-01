package factories;

import sensors.Sensor;

/**
 * A factory interface for creating Sensor objects.
 * This interface provides a method to create a Sensor object with specified model presented in the catalogue.
 */
public interface SensorFactory {

    /**
     * Creates a sensor from a name in the catalogue.
     * @param sensorClassName model from the Catalogue.
     * @return a Sensor object.
     */
    Sensor createSensor(String sensorClassName);
}
