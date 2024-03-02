package values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the WindSpeedValue class.
 */

class WindSpeedValueTest {

    /**
     * The WindSpeedValue object to be tested.
     */
    WindSpeedValue windSpeedValue;

    /**
     * Initializes the windSpeedValue object for the tests.
     */
    @BeforeEach
    void setUp() {
        windSpeedValue = new WindSpeedValue(25);
    }

    /**
     * Verifies the WindSpeedValue constructor sets the value correctly.
     * Verifies the getValue method returns the expected wind speed value.
     */
    @Test
    void defaultConstructor_getWindSpeedValue() {
        // Arrange
        String expected = "25.0";
        // Act
        String result = windSpeedValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the toValue method generates the correct string representation.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        String expected = "25.0";
        // Act
        String result = windSpeedValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}