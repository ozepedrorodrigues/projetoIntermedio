package dto;

import domain.ActuatorType;

/**
 * This class represents the ActuatorDTO, used to transfer actuator data to the user interface.
 * It contains the actuator's id, type, and state.
 */
public class ActuatorDTO {

    /**
     * The actuator's id.
     */
    private int id;

    /**
     * The actuator's type.
     */
    private ActuatorType type;

    /**
     * The actuator's state.
     */
    private final boolean state;

    /**
     * Constructs a new ActuatorDTO with the specified id, type, and state.
     *
     * @param id    The id of the actuator.
     * @param type  The type of the actuator.
     * @param state The state of the actuator.
     */
    public ActuatorDTO(int id, ActuatorType type, boolean state) {
        this.type = type;
        this.id = id;
        this.state = state;

    }

    /**
     * Gets the id of the actuator.
     *
     * @return The id of the actuator.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the type of the actuator.
     *
     * @return The type of the actuator.
     */
    public ActuatorType getType() {
        return this.type;
    }

    /**
     * Gets the state of the actuator.
     *
     * @return The state of the actuator.
     */
    public boolean getState() {
        return this.state;
    }
}
