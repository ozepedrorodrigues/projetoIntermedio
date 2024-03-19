package SmartHome.domain.actuators;

import SmartHome.domain.sensors.values.Value;

/**
 * Represents a generic actuator in a smart home system.
 * An actuator is a device responsible for moving or controlling a mechanism or system,
 * e.g., turning on a light or adjusting the thermostat.
 */
public interface Actuator {

    /**
     * Gets the type of this actuator.
     *
     * @return ActuatorType The type of actuator.
     */
    ActuatorType getType();

    /**
     * Operates the actuator with the given value.
     * @param value The value to operate the actuator with.
     * @return Value The new state of the actuator after the operation or null if the value is not valid.
     */
    Value operate(Value value);

    /**
     * Gets the unique identifier for this actuator.
     *
     * @return int The unique ID of the actuator.
     */
    int getId();

    /**
     * Generates a new unique identifier for this actuator.
     *
     * @return int The new unique ID of the actuator.
     */
    int generateId();


}
