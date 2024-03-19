package SmartHome.domain.sensors.values;


/**
 * <p>Represents a generic value in the context of a sensor reading. Classes implementing
 * this interface should provide a method to convert the value to a string representation.</p>
 *
 */

public interface Value {


    /**
     * Converts the value to a string representation.
     *
     * @return A string representation of the value.
     */
    String valueToString();

}
