package actuators;

import domain.ActuatorType;

public interface Actuator{
    ActuatorType getType();
    boolean isActive();
    void activate();
    void deactivate();
    int getId();
    int setId(int id);

}
