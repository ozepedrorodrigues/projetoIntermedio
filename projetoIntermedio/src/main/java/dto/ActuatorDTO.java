package dto;

public class ActuatorDTO {

    private final boolean state;

    public ActuatorDTO(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }
}
