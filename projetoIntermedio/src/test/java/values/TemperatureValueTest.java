package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureValueTest {


    TemperatureValue temperatureValue;

    @BeforeEach
    void setUp() {
        temperatureValue = new TemperatureValue(25);
    }


    /**
     * Verifies that the getTemperatureValue method returns the expected temperature value.
     *
     */
    @Test
    void defaultConstructor_getTemperatureValue() {
        // Arrange
        double expected = 25;
        // Act
        double result = temperatureValue.getValue();
        // Assert
        assertEquals(expected, result, 0.01);
    }


    /**
     * Verifies that the toValue method generates the correct string representation with valid input.
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