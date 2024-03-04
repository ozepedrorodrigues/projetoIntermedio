package domain;

/**
 * The ActuatorType enum represents the different types of actuators that can be used in the system.
 * <p>
 */
public enum ActuatorType {
    /**
     * The BlindsManager actuator type (for controlling blinds).*/
    BLINDSMANAGER("Blinders"),
    /** The Limiter actuator type (for setting upper and lower limits).*/
    LIMITER("Limiter"),
    /** The LimiterDecimal actuator type (for setting upper and lower limits with decimal values).*/
    LIMITER_DECIMAL("LimiterDecimal"),
    /** The OnOffSwitch actuator type (for turning devices on and off).*/
    ONOFFSWITCH("OnOffSwitch");
    private final String name;

    /**
     * Constructor of the ActuatorType enum.
     * @param name The name of the actuator type.
     */
    ActuatorType(String name) {
        this.name = name;}
}
