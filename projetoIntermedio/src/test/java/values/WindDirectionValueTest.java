package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the WindDirectionValue class.
 */

class WindDirectionValueTest {

    /**
     * The WindDirectionValue object to be tested.
     */

    WindDirectionValue windDirectionValue;

    /**
     * Initializes the windDirectionValue object for the tests.
     */
    @BeforeEach
    void setUp() {
        windDirectionValue = new WindDirectionValue(315);
    }

    /**
     * Verifies the WindDirectionValue constructor sets the value correctly.
     * Verifies the getValue method returns the expected wind direction value.
     */
    @Test
    void defaultConstructor_getWindDirectionValue() {
        // Arrange
        int expected = 315;
        // Act
        int result = windDirectionValue.getValue();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the toValue method generates the correct string representation.
     */

    @Test
    void toValue_Valid() {
        // Arrange
        String expected = "315";
        // Act
        String result = windDirectionValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}