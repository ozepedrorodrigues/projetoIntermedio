package actuators;

import domain.ActuatorType;

/**
 * A Limiter is an implementation of the Actuator interface that limits a value within a specified range.
 * The Limiter has a state, a minimum and maximum limit, and a value.
 */
public class Limiter implements Actuator {
    /**
     * The unique identifier of the Limiter.
     */
    private int id;
    /**
     * The type of the Actuator.
     */
    private ActuatorType type;
    /**
     * The state of the Limiter.
     */
    private boolean state;
    /**
     * The minimum limit of the Limiter.
     */
    private int min;
    /**
     * The maximum limit of the Limiter.
     */
    private int max;
    /**
     * The value that is being limited.
     */
    private Integer value;

    /**
     * Constructs a new instance of the Limiter class with specified lower and upper limits.
     * The state of the Limiter is set to false and the value is set to null.
     *
     * @param lower the lower limit of the Limiter
     * @param upper the upper limit of the Limiter
     * @throws IllegalArgumentException if the lower limit is greater than the upper limit
     */
    public Limiter(int lower, int upper) {
        if (!validLimits(lower, upper)) {
            throw new IllegalArgumentException();
        }
        this.min = lower;
        this.max = upper;
        this.state = false;
        this.value = null;
        this.type = ActuatorType.LIMITER;
    }

    /**
     * Retrieves the state of the Limiter.
     *
     * @return true if the Limiter is active, false otherwise
     */
    @Override
    public boolean isActive() {
        return this.state;
    }

    /**
     * Activates the Limiter.
     *
     * @throws IllegalStateException if the Limiter is already active
     */
    @Override
    public void activate() {
        if (state) {
            throw new IllegalStateException();
        }
        state = true;
    }

    /**
     * Deactivates the Limiter.
     *
     * @throws IllegalStateException if the Limiter is already inactive
     */
    @Override
    public void deactivate() {
        if (!state) {
            throw new IllegalStateException();
        }
        state = false;
    }

    /**
     * Retrieves the type of the Actuator.
     *
     * @return the ActuatorType of the Actuator
     */
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Retrieves the ID of the Actuator.
     *
     * @return the ID of the Actuator
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the Actuator.
     *
     * @param newId the new ID to set
     * @return the new ID of the Actuator
     */
    public int setId(int newId) {
        this.id = newId;
        return this.id;
    }

    /**
     * Sets the value of the Limiter.
     *
     * @param value the value to set
     * @return true if the value is set
     * @throws IllegalArgumentException if the Limiter is inactive or the value is not within the limits
     */
    public boolean setValue(int value) {
        if (!this.state || !validValue(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        return true;
    }

    /**
     * Sets the limits of the Limiter.
     *
     * @param lower the lower limit of the Limiter
     * @param upper the upper limit of the Limiter
     * @return true if the limits are set
     * @throws IllegalArgumentException if the lower limit is greater than the upper limit
     */
    public boolean setLimits(int lower, int upper) {
        if (!validLimits(lower, upper)) {
            throw new IllegalArgumentException();
        }
        this.min = lower;
        this.max = upper;
        return true;
    }

    /**
     * Retrieves the value of the Limiter.
     *
     * @return the value of the Limiter
     */
    public Integer getValue() {
        return this.value;
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

    /**
     * Validates the value of the Limiter.
     *
     * @param value the value to validate
     * @return true if the value is within the limits, false otherwise
     */
    private boolean validValue(int value) {
        return value >= this.min && value <= this.max;
    }
}
