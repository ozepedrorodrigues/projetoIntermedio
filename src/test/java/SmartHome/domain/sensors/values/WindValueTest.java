package SmartHome.domain.sensors.values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * The WindValueTest class is responsible for testing the WindValue class.
 */

class WindValueTest {

    /**
     * The WindValue object to be used in the tests.
     */
    WindValue windValue;

    /**
     * Sets up valid data for testing the WindValue class.
     */
    @BeforeEach
    void setUp() {
        windValue = new WindValue(180, 25.0);
    }

    /**
     * Tests the default constructor of the WindValue class.
     */
    @Test
    void defaultConstructor_getWindValue() {
        // Arrange
        String expected = "Wind Direction: 180.0, Wind Speed: 25.0";
        // Act
        String result = windValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the valueToString method of the WindValue class.
     * Verifies that the method returns the wind value correctly.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        WindValue windValue = new WindValue(180, 25.0);
        String expected = "Wind Direction: 180.0, Wind Speed: 25.0";
        // Act
        String result = windValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}