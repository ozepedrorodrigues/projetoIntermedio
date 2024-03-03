package mappers;

import actuators.Actuator;
import dto.ActuatorDTO;

public class ActuatorMapper {

    public ActuatorMapper() {
    }

    public ActuatorDTO actuatorToDTO(Actuator actuator) {
        return new ActuatorDTO(actuator.isActive());
    }
}
