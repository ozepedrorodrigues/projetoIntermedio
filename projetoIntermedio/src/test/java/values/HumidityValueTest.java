package values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumidityValueTest {

    HumidityValue humidityValue;

    @BeforeEach
    void setUp() {
        humidityValue = new HumidityValue();
    }

    /**
     * Verifies that the getHumidityValue method returns the expected humidity value.
     */
    @Test
    void defaultConstructor_getHumidityValue() {
        // Arrange
        double expected = 0.0;    // No external setting of value, assuming a valid default value
        // Act
        double result = humidityValue.getHumidityValue();
        // Assert
        assertEquals(expected, result, 0.01);
    }

    /**
     * Verifies that the toValue method generates the correct string representation with valid input.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        HumidityValue currentReading = new HumidityValue();
        String expected = "HumidityValue{value=0.0}";    // No external setting of value, assuming a valid default value
        // Act
        String result = currentReading.toValue();
        // Assert
        assertEquals(expected, result);
    }

}