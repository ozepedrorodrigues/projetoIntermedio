package factories;

import actuators.Actuator;

/**
 * A factory interface for creating Actuator objects.
 * This interface provides a method to create an Actuator object with specified model
 * presented in the catalogue.
 */
public interface ActuatorFactory {

    /**
     * Creates an actuator from a name in the catalogue.
     *
     * @param actuatorClassName model from the Catalogue.
     * @return an Actuator object.
     */
    Actuator createActuator(String actuatorClassName);
}
