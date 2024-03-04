package values;

/**
 * This class represents a binary value that can be either ON or OFF.
 */
public class OnOffValue implements Value {

    /**
     * The ON/OFF value.
     */
    private boolean onOffValue;

    /**
     * Constructs a new instance of the OnOffValue class with the ON/OFF value initialized to an undefined state.
     *
     * @param onOffValue The ON/OFF value.
     */
    public OnOffValue(boolean onOffValue) {
        this.onOffValue = onOffValue;
    }

    /**
     * Converts the ON/OFF value to a string representation.
     *
     * @return The string representation of the ON/OFF value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(onOffValue);
    }
}
