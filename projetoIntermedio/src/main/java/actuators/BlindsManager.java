package actuators;

public class BlindsManager implements Actuator{
    private Boolean state;
    public BlindsManager() {this.state = false;}

    public boolean isActive() {return this.state;}
    @Override
    public void activate() {}
    @Override
    public void deactivate() {}
}
