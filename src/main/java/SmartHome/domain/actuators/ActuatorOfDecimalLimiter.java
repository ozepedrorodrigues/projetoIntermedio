package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;

/**
 * A ActuatorOfDecimalLimiter is an actuator that limits the value to a specified range and precision.
 * The ActuatorOfDecimalLimiter has a lower limit, an upper limit, and a precision.
 * The value of the ActuatorOfDecimalLimiter is rounded to the specified precision.
 */
public class ActuatorOfDecimalLimiter implements Actuator {

    /**
     * The unique identifier of the actuator.
     */
    private int id;

    /**
     * The type of the actuator.
     */
    private ActuatorType type;

    /**
     * The lower limit for the value.
     */
    private Double lowerLimit;

    /**
     * The upper limit for the value.
     */
    private Double upperLimit;

    /**
     * The precision of the value.
     */
    private Integer precision;

    /**
     * Constructs a new ActuatorOfDecimalLimiter with the specified lower limit, upper limit, and precision.
     *
     * @throws IllegalArgumentException if the lower limit is greater than the upper limit or if the precision is negative
     */
    public ActuatorOfDecimalLimiter() {
        this.lowerLimit = null;
        this.upperLimit = null;
        this.precision = null;
        this.type = ActuatorType.LIMITER_DECIMAL;
    }

    /**
     * Sets the limits of the ActuatorOfDecimalLimiter.
     * The lower limit must be less than or equal to the upper limit.
     *
     * @param lowerLimit the lower limit of the ActuatorOfDecimalLimiter
     * @param upperLimit the upper limit of the ActuatorOfDecimalLimiter
     * @return true if the limits are valid and have been set, false otherwise
     */
    public boolean setLimits(double lowerLimit, double upperLimit) {
        if (!validLimits(lowerLimit, upperLimit)) {
            return false;
        }
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        return true;
    }

    /**
     * Sets the precision of the ActuatorOfDecimalLimiter.
     * The precision must be non-negative.
     *
     * @param precision the precision of the ActuatorOfDecimalLimiter
     * @return true if the precision is valid and has been set, false otherwise
     */
    public boolean setPrecision(int precision) {
        if (!validPrecision(precision)) {
            return false;
        }
        this.precision = precision;
        return true;
    }


    /**
     * Returns the type of the actuator.
     *
     * @return The of the actuator.
     */
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Returns the ID of the actuator.
     *
     * @return The ID of the actuator.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Generates a new ID for the actuator.
     *
     * @return The new ID of the actuator.
     */
    public int generateId() {
        return this.id = IdGenerator.generateActuatorId();
    }

    /**
     * Sets the value of the ActuatorOfDecimalLimiter.
     * The value is rounded to the specified precision.
     * The value must be within the limits of the ActuatorOfDecimalLimiter.
     *
     * @param value the value to set
     * @return the updated value
     */
    public Value operate(Value value) {
        double valueDouble = Double.parseDouble(value.valueToString());
        if (!validValue(valueDouble)) {
            throw new IllegalArgumentException();
        }
        double newValue = Math.round(valueDouble * Math.pow(10, this.precision)) / Math.pow(10, this.precision);
        Value updatedValue = new ValueDouble(newValue);
        return updatedValue;
    }

    /**
     * Validates the limits of the ActuatorOfDecimalLimiter.
     *
     * @param lower the lower limit of the ActuatorOfDecimalLimiter.
     * @param upper the upper limit of the ActuatorOfDecimalLimiter.
     * @return true if the limits are valid
     */
    private boolean validLimits(double lower, double upper) {
        return lower <= upper;
    }

    /**
     * Validates the value of the ActuatorOfDecimalLimiter.
     *
     * @param value the value of the ActuatorOfDecimalLimiter.
     * @return true if the value is valid
     */
    private boolean validValue(double value) {
        return value >= this.lowerLimit && value <= this.upperLimit;
    }

    /**
     * Validates the precision of the ActuatorOfDecimalLimiter.
     *
     * @param precision the precision to validate
     * @return true if the precision is non-negative
     */
    private boolean validPrecision(int precision) {
        return precision >= 0;
    }
}
