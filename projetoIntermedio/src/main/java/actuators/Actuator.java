package actuators;

import domain.ActuatorType;

public interface Actuator{

    boolean isActive();
    void activate();
    void deactivate();

    //é preciso implementar

    //ActuatorType getType();
    //int getId();
    //int setId(int id);
}
