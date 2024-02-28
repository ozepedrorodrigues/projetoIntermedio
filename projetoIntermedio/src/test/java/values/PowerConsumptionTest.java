package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerConsumptionTest {
    PowerConsumptionValue powerConsumptionValue;

    @BeforeEach
    void setUp() {
        powerConsumptionValue = new PowerConsumptionValue();
    }

    @Test
    void defaultConstructor_getPowerConsumptionValue() {
        // Arrange
        int expected = 0;
        // Act
        int result = powerConsumptionValue.getPowerConsumptionValue();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void toValue_Valid() {
        // Arrange
        PowerConsumptionValue currentReading = new PowerConsumptionValue();
        String expected = "0";
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}