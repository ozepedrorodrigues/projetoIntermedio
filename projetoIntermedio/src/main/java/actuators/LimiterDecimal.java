package actuators;

import domain.ActuatorType;

/**
 * A LimiterDecimal is an actuator that limits the value to a specified range and precision.
 * The LimiterDecimal has a state, a lower limit, an upper limit, and a precision.
 * The value of the LimiterDecimal is rounded to the specified precision.
 */
public class LimiterDecimal implements Actuator {

    /**
     * The unique identifier of the actuator.
     */
    private int id;

    /**
     * The type of the actuator.
     */
    private ActuatorType type;

    /**
     * The state of the actuator.
     */
    private boolean state;

    /**
     * The value of the actuator.
     */
    private Double value;

    /**
     * The lower limit for the value.
     */
    private double lowerLimit;

    /**
     * The upper limit for the value.
     */
    private double upperLimit;

    /**
     * The precision of the value.
     */
    private int precision;

    /**
     * Constructs a new LimiterDecimal with the specified lower limit, upper limit, and precision.
     *
     * @param lowerLimit The lower limit for the value.
     * @param upperLimit The upper limit for the value.
     * @param precision  The precision of the value.
     * @throws IllegalArgumentException if the lower limit is greater than the upper limit or if the precision is negative
     */

    public LimiterDecimal(double lowerLimit, double upperLimit, int precision) {
        if (!validLimits(lowerLimit, upperLimit) || precision < 0)
            throw new IllegalArgumentException();
        this.state = false;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.precision = precision;
        this.type = ActuatorType.LIMITER_DECIMAL;
    }

    /**
     * Returns whether the actuator is active.
     *
     * @return true if the actuator is active, false otherwise.
     */
    @Override
    public boolean isActive() {
        return this.state;
    }

    /**
     * Activates the actuator.
     *
     * @throws IllegalStateException if the actuator is already active.
     */
    public void activate() {
        if (this.state) {
            throw new IllegalStateException();
        }
        this.state = true;
    }

    /**
     * Deactivates the actuator.
     *
     * @throws IllegalStateException if the actuator is not active.
     */
    @Override
    public void deactivate() {
        if (!this.state) {
            throw new IllegalStateException();
        }
        this.state = false;
    }

    /**
     * Returns the type of the actuator.
     *
     * @return the type of the actuator.
     */
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Returns the unique identifier of the actuator.
     *
     * @return the unique identifier of the actuator.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier of the actuator.
     *
     * @param newId the new ID to set
     * @return the new ID of the actuator.
     */
    public int setId(int newId) {
        this.id = newId;
        return this.id;
    }

    /**
     * Sets the limits of the LimiterDecimal.
     *
     * @param lower the lower limit of the LimiterDecimal
     * @param upper the upper limit of the LimiterDecimal
     * @return true if the limits are set
     * @throws IllegalArgumentException if the limits are not valid
     */
    public boolean setLimits(double lower, double upper) {
        if (!validLimits(lower, upper)) {
            throw new IllegalArgumentException();
        }

        this.lowerLimit = lower;
        this.upperLimit = upper;
        return true;
    }

    /**
     * Sets the precision of the LimiterDecimal.
     *
     * @param precision the precision to set
     * @return true if the precision is set
     * @throws IllegalArgumentException if the precision is negative
     */
    public  boolean setPrecision(int precision) {
        if (precision < 0) {
            throw new IllegalArgumentException();
        }
        this.precision = precision;
        return true;
    }

    /**
     * Sets the value of the LimiterDecimal.
     *
     * @param value the value to set
     * @return true if the value is set
     * @throws IllegalArgumentException if the LimiterDecimal is inactive or the value is not within the limits
     */
    public boolean setValue(double value) {
        if (!this.state || !validValue(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        return true;
    }

    /**
     * Retrieves the value of the LimiterDecimal.
     *
     * @return the value of the LimiterDecimal
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * Validates the limits of the LimiterDecimal.
     *
     * @param lower the lower limit of the LimiterDecimal
     * @param upper the upper limit of the LimiterDecimal
     * @return true if the lower limit is less than the upper limit, false otherwise
     */
    private boolean validLimits(double lower, double upper) {
        return lower <= upper;
    }

    /**
     * Validates the value of the LimiterDecimal.
     *
     * @param value the value to validate
     * @return true if the value is within the limits, false otherwise
     */
    private boolean validValue(double value) {
        return value >= this.lowerLimit && value <= this.upperLimit;
    }
}