package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;

/**
 * Implementation of Actuator Interface for and Actuator that manages the Blinds.
 * This class is used to manage the state of the Blinds.
 */
public class ActuatorOfBlinds implements Actuator{

    /**
     * The unique identifier of the ActuatorOfBlinds.
     */
    private int id;


    private ActuatorType type;
    /**
     * Constructs a new instance of the ActuatorOfBlinds class
     * with the state of the ActuatorOfBlinds initialized to an inactive (false) state.
     */
    public ActuatorOfBlinds() {
        this.type = ActuatorType.BLINDSMANAGER;
    }

    /**
     * Activates the ActuatorOfBlinds.
     * @param value The new state of the ActuatorOfBlinds.
     * @return The new state of the ActuatorOfBlinds.
     */
    public Value operate(Value value) throws NumberFormatException{
        double valueDouble = Double.parseDouble(value.valueToString());
        if (valueDouble >= 0 && valueDouble <= 100) {return value;}
        return null;}
    /**
     * Returns the type of the Actuator.
     * @return The {@link ActuatorType} of the Actuator.
     */
    public ActuatorType getType() {
        return type;}

    /**
     * Returns the ID of the Actuator.
     * @return The ID of the Actuator.
     */
    public int getId() {
        return id;}

    /**
     * Generates automatically the new ID of the Actuator.
     * @return The new ID of the Actuator.
     */
    public int generateId() {
        return this.id = IdGenerator.generateActuatorId();}
}
