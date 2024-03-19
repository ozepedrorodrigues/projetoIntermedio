package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;

/**
 * This class represents an Actuator of type Limiter.
 * It is used to limit the value of a sensor to a certain range.
 */
public class ActuatorOfLimiter implements Actuator{

    /**
     * The id of the Actuator.
     */
    private int id;
    /**
     * The lower limit of the Limiter.
     */
    private Integer lower;
    /**
     * The upper limit of the Limiter.
     */
    private Integer upper;
    /**
     * The type of the Actuator.
     */
    private final ActuatorType type;

    /**
     * Constructs a new ActuatorOfLimiter with the given lower and upper limits.
     */
    public ActuatorOfLimiter() {
        this.lower = null;
        this.upper = null;
        this.type = ActuatorType.LIMITER;
    }

    /**
     * Retrieves the type of the Actuator.
     *
     * @return the type of the Actuator
     */
    @Override
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Sets the limits of the Limiter.
     *
     * @return true if the limits are valid and have been set, false otherwise
     */
    public boolean setLimits(int lower, int upper) {
        if (!validLimits(lower, upper)) {
            return false;
        }
        this.lower = lower;
        this.upper = upper;
        return true;
    }

    /**
     * Operates the Actuator on the given value.
     *
     * @param value the value to operate the Actuator on
     * @return the value if it is within the limits of the Limiter, null otherwise
     * @throws NumberFormatException if the value is not a valid integer or the limits have not been set
     */
    @Override
    public Value operate(Value value) throws NumberFormatException {
        int valueInteger = Integer.parseInt(value.valueToString());
        if (this.lower == null || this.upper == null) {
            throw new NumberFormatException();
        }
        if (valueInteger >= this.lower && valueInteger <= this.upper) {
            return value;
        }
        return null;
    }

    /**
     * Retrieves the id of the Actuator.
     *
     * @return the id of the Actuator
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Generates the id of the Actuator.
     * @return the generated id of the Actuator
     */
    @Override
    public int generateId() {
        return this.id= IdGenerator.generateActuatorId();
    }

    /**
     * Validates the limits of the Limiter.
     *
     * @param lower the lower limit of the Limiter
     * @param upper the upper limit of the Limiter
     * @return true if the lower limit is less than or equal to the upper limit, false otherwise
     */
    private boolean validLimits(int lower, int upper) {
        return lower <= upper;
    }
}
