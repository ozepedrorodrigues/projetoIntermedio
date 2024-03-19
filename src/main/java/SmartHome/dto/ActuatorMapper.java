package SmartHome.dto;

import SmartHome.domain.actuators.Actuator;

/**
 * This class represents the mapper for the Actuator class.
 * It is used to convert an Actuator instance to an ActuatorDTO instance.
 */
public class ActuatorMapper {

    /**
     * Constructs a new ActuatorMapper.
     */
    public ActuatorMapper() {
    }

    /**
     * Converts an Actuator instance to an ActuatorDTO instance.
     *
     * @param actuator The actuator to be converted to an ActuatorDTO.
     * @return The ActuatorDTO instance.
     */
    public ActuatorDTO actuatorToDTO(Actuator actuator) {
        return new ActuatorDTO(actuator.getId(), actuator.getType());
    }
}
