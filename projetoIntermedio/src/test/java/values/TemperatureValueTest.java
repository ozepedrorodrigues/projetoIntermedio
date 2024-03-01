package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the TemperatureValue class.
 */
class TemperatureValueTest {

    /**
     * The TemperatureValue object to be tested.
     */
    TemperatureValue temperatureValue;

    /**
     * Initializes the temperatureValue object for the tests.
     */
    @BeforeEach
    void setUp() {
        temperatureValue = new TemperatureValue(25);
    }


    /**
     * Verifies the TemperatureValue constructor sets the value correctly.
     * Verifies the getValue method returns the expected temperature value.
     */
    @Test
    void defaultConstructor_getTemperatureValue() {
        // Arrange
        String expected = "25";
        // Act
        String result = temperatureValue.valueToString();
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
        String result = temperatureValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}