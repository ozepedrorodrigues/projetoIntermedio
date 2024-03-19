package SmartHome.dto;

import SmartHome.domain.actuators.ActuatorType;

/**
 * This class represents the ActuatorDTO, used to transfer actuator data to the user interface.
 * It contains the actuator's id and type.
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
     * Constructs a new ActuatorDTO with the specified id and type.
     *
     * @param id    The id of the actuator.
     * @param type  The type of the actuator.
     */

    public ActuatorDTO(int id, ActuatorType type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Gets the id of the actuator.
     *
     * @return The id of the actuator.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the type of the actuator.
     *
     * @return The type of the actuator.
     */
    public ActuatorType getType() {
        return type;
    }
}
