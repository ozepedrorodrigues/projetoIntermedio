package values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the OnOffValue class.
 * It tests the value getter and the implemented interface method valueToString.
 */
class OnOffValueTest {

    /**
     * Tests the getOnOffValue method of the OnOffValue class.
     * Verifies that the method returns the expected value.
     */
    @Test
    void testGetOnOffValueMethod() {
        // Arrange
        boolean expected = true;
        OnOffValue onOffValue = new OnOffValue(expected);

        // Act
        boolean result = onOffValue.getOnOffValue();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the valueToString method of the OnOffValue class.
     * Verifies that the method returns the expected string.
     */
    @Test
    void testValueToStringMethod() {
        // Arrange
        String expected = "true";
        boolean onOff = true;
        OnOffValue onOffValue = new OnOffValue(onOff);

        // Act
        String result = onOffValue.valueToString();

        // Assert
        assertEquals(expected, result);
    }
}