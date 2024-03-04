package values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the OnOffValue class.
 */
class OnOffValueTest {

    /**
     * Tests the behavior of the constructor of OnOffValue class.
     * Verifies that the constructor creates a new OnOffValue instance.
     */
    @Test
    void testConstructor() {
        // Arrange
        boolean value = true;
        // Act
        OnOffValue onOffValue = new OnOffValue(value);
        // Assert
        assertNotNull(onOffValue);
    }

    /**
     * Tests the valueToString method of the OnOffValue class.
     * Verifies that the method returns the expected string.
     */
    @Test
    void testValueToStringTrue() {
        // Arrange
        String expected = "true";
        boolean value = true;
        OnOffValue onOffValue = new OnOffValue(value);

        // Act
        String result = onOffValue.valueToString();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the valueToString method of the OnOffValue class.
     * Verifies that the method returns the expected string.
     */
    @Test
    void testValueToStringFalse() {
        // Arrange
        String expected = "false";
        boolean value = false;
        OnOffValue onOffValue = new OnOffValue(value);

        // Act
        String result = onOffValue.valueToString();

        // Assert
        assertEquals(expected, result);
    }
}
