package actuators;

public class OnOff implements Actuator {
    /**
     * The state of the actuator
     * true if the actuator is active, false otherwise
     */
    private boolean state;

    /**
     * Constructs a new OnOff actuator
     * The actuator is initially deactivated
     */
    public OnOff() {
        this.state = false;
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
}