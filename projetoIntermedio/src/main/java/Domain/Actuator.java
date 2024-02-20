package Domain;

/**
 * The Actuator class represents an actuator with a name and a type.
 */
public class Actuator {
    /**
     * The name of the actuator.
     */
    private String name;
    /**
     * The type of the actuator.
     */
    private String type;

    /**
     * Constructs a new Actuator with the specified name and type.
     *
     * @param name the name of the actuator
     * @param type the type of the actuator
     * @throws IllegalArgumentException if the name or type is empty
     */
    public Actuator(String name, String type) {
        if (name.trim().isEmpty() || type.trim().isEmpty())
            throw new IllegalArgumentException("Name or type must not be empty.");
        this.name = name;
        this.type = type;
    }

    /**
     * Returns the name of this actuator.
     *
     * @return the name of this actuator
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of this actuator.
     *
     * @return the type of this actuator
     */
    public String getType() {
        return type;
    }
}