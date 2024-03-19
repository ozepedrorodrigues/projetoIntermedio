package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.OnOffValue;
import SmartHome.domain.sensors.values.Value;

/**
 * Represents an actuator of type ONOFFSWITCH.
 * This actuator is a specific implementation of the Actuator interface specifically designed
 * for controlling an ON/OFF switch.
 * This class allows the operation of the switch, updating its internal state based on
 * an external binary value.
 * The switch can be in either an ON or OFF state, and the class provides methods to operate
 * and query its state.
 */
public class ActuatorOfOnOffSwitch implements Actuator {
    /**
     * The unique identifier for this actuator.
     */
    private int id;
    /**
     * The type of the actuator, indicating that it is designed for an ON/OFF switch.
     */
    private final ActuatorType type;
    /**
     * The current state of the load controlled by the switch.
     * True if the load is turned ON, false if it is turned OFF.
     */
    private boolean isLoadTurnedOn = false;

    /**
     * Constructs a new instance of the ActuatorOfOnOffSwitch class.
     * Initializes the actuator type to ActuatorType ONOFFSWITCH.
     */
    public ActuatorOfOnOffSwitch() {
        this.type = ActuatorType.ONOFFSWITCH;
    }

    /**
     * Gets the type of the actuator.
     *
     * @return The type of the actuator (ON/OFF switch).
     */
    @Override
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Operates the ON/OFF switch based on the provided binary value.
     * If the binary value is true (ON), the switch is turned ON; otherwise, it is turned OFF.
     * Updates the internal state of the switch and returns a new OnOffValue representing the updated state.
     *
     * This method performs a consistency check to ensure that the provided binary value adheres
     * to the expected format for binary states (either "true" or "false" in a case-insensitive manner).
     * If the binary value is not valid, an IllegalArgumentException is thrown.
     *
     * @param binaryValue The binary value representing the desired state of the ON/OFF switch.
     * @return A new OnOffValue representing the updated state of the switch after the operation.
     * @throws IllegalArgumentException If the provided binary value is not a valid binary state.
     */
    public Value operate(Value binaryValue) {
        if (!isValidBinaryValue(binaryValue)) {
            throw new IllegalArgumentException();
        }
        boolean isBinaryValueOn = Boolean.parseBoolean(binaryValue.valueToString());

        if (isBinaryValueOn) {
            this.isLoadTurnedOn = true;
            return new OnOffValue(true);
        } else {
            this.isLoadTurnedOn = false;
            return new OnOffValue(false);
        }
    }

    /**
     * Checks if the provided value is a valid binary value.
     * Assumes that a valid binary value can only be "true" or "false" (case-insensitive).
     *
     * @param value The value to be checked.
     * @return True if the value is a valid binary value, false otherwise.
     */
    private boolean isValidBinaryValue(Value value) {
        String valueString = value.valueToString().toLowerCase();
        return "true".equals(valueString) || "false".equals(valueString);
    }

    /**
     * Gets the unique identifier for this actuator.
     *
     * @return int The unique ID of the actuator.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Generates a new unique identifier for this actuator.
     *
     * @return int The new unique ID of the actuator.
     */
    @Override
    public int generateId() {
        return this.id = IdGenerator.generateActuatorId();
    }
}
