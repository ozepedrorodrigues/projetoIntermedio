package values;

/**
 * Represents an ON/OFF value, providing methods to retrieve the boolean ON/OFF state
 * and convert the value to a string representation.
 */
public class OnOffValue implements Value {

    private boolean onOffValue;

    /**
     * Retrieves the boolean ON/OFF state of the value.
     *
     * @return true if the value represents ON, false if it represents OFF.
     */
    public boolean getOnOffValue() {
        return onOffValue;
    }

    /**
     * Converts the ON/OFF value to its string representation.
     *
     * @return A string representation of the ON/OFF value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(onOffValue);
    }
}
