package SmartHome.domain.sensors.values;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumidityValueTest {

    HumidityValue humidityValue;

    @BeforeEach
    void setUp() {
        humidityValue = new HumidityValue(50);
    }

    /**
     * Verifies the HumidityValue constructor sets the value correctly.
     * Verifies the getValue method returns the expected humidity value.
     */
    @Test
    void defaultConstructor_getHumidityValue() {
        // Arrange
        String expected = "50.0";
        // Act
        String result = humidityValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the toValue method generates the correct string representation with valid input.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        String expected = "50.0";
        // Act
        String result = humidityValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}
