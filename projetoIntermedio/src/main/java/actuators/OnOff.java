package actuators;

import domain.ActuatorType;

public class OnOff implements Actuator {
    /**
     * The state of the actuator
     * true if the actuator is active, false otherwise
     */
    private boolean state;

    /**
     * The type of the actuator
     */
    private ActuatorType type;

    /**
     * The unique identifier of the actuator
     */
    private int id;

    /**
     * Constructs a new OnOff actuator
     * The actuator is initially deactivated
     */
    public OnOff() {
        this.state = false;
        this.type = ActuatorType.ONOFFSWITCH;
    }

    /**
     * Returns the state of the actuator
     *
     * @return true if the actuator is active, false otherwise
     */
    @Override
    public boolean isActive() {
        return state;
    }

    /**
     * Activates the actuator
     *
     * @throws IllegalStateException if the actuator is already active
     */
    @Override
    public void activate() {
        if (state) {
            throw new IllegalStateException();
        }
        state = true;
    }

    /**
     * Deactivates the actuator
     *
     * @throws IllegalStateException if the actuator is already deactivated
     */
    @Override
    public void deactivate() {
        if (!state) {
            throw new IllegalStateException();
        }
        state = false;
    }

    /**
     * Retrieves the type of the actuator
     *
     * @return the ActuatorType of the actuator
     */
    @Override
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Retrieves the ID of the actuator
     *
     * @return the ID of the actuator
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the actuator
     *
     * @param id the new ID of the actuator
     */
    @Override
    public int setId(int id) {
        this.id = id;
        return this.id;}
}