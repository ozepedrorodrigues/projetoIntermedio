package actuators;

/**
 * Implementation of Actuator Interface for and Actuator that manages the Blinds.
 * This class is used to manage the state of the Blinds.
 */
public class BlindsManager implements Actuator{
    /**
     * The state of the BlindsManager (active, inactive)
     */
    private Boolean state;
    /**
     * Constructs a new instance of the BlindsManager class
     * with the state of the BlindsManager initialized to an inactive (false) state.
     */
    public BlindsManager() {this.state = false;}

    /**
     * Returns the state of the BlindsManager.
     * @return the state of the BlindsManager.
     */

    public boolean isActive() {return this.state;}
    /**
     * Activates the BlindsManager.
     */
    @Override
    public void activate() {
        if(this.state){throw new IllegalStateException();}
        this.state= true;
    }
    /**
     * Deactivates the BlindsManager.
     */
    @Override
    public void deactivate() {
        if(!this.state){throw new IllegalStateException();}
        this.state= false;}
}
