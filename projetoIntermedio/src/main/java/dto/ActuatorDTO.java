package dto;

import domain.ActuatorType;

public class ActuatorDTO {

    //private int id;
    //private ActuatorType type;
    private final boolean state;

    public ActuatorDTO(boolean state) {
        //this.type = type;
        //this.id = id;
        this.state = state;

    }

    //public int getId() {
    //    return this.id;
    // }

    //public ActuatorType getType() {
    //    return this.type;
    //}

    public boolean getState() {
        return this.state;
    }
}
