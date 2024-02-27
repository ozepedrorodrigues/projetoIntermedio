package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import values.TemperatureValue;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureValueTest {


    TemperatureValue temperatureValue;

    @BeforeEach
    void setUp() {
        temperatureValue = new TemperatureValue();
    }


    /**
     * Verifies that the getTemperatureValue method returns the expected temperature value.
     *
     */
    @Test
    void defaultConstructor_getTemperatureValue() {
        // Arrange
        double expected = 0.0;    // No external setting of value, assuming a valid default value
        // Act
        double result = temperatureValue.getTemperatureValue();
        // Assert
        assertEquals(expected, result, 0.01);
    }


    /**
     * Verifies that the toValue method generates the correct string representation with valid input.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        TemperatureValue currentReading = new TemperatureValue();
        String expected = "TemperatureValue{value=0.0}";    // No external setting of value, assuming a valid default value
        // Act
        String result = currentReading.toValue();
        // Assert
        assertEquals(expected, result);
    }

}