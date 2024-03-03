package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerConsumptionValueTest {
    PowerConsumptionValue powerConsumptionValue;

    /**
     * This method sets up the test environment, initializing the power consumption value to 1.0.
     */
    @BeforeEach
    void setUp() {
        powerConsumptionValue = new PowerConsumptionValue(1);
    }

    /**
     * This test checks if the default constructor sets the power consumption value to 1.0.
     */
    @Test
    void defaultConstructor_getPowerConsumptionValue() {
        // Arrange
        String expected = "1.0";
        // Act
        String result = powerConsumptionValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test checks if the value of the power consumption is correctly converted to a string.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        PowerConsumptionValue currentReading = new PowerConsumptionValue(0);
        String expected = "0.0";
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}
