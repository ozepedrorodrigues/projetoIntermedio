package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerConsumptionTest {
    PowerConsumptionValue powerConsumptionValue;

    @BeforeEach
    void setUp() {
        powerConsumptionValue = new PowerConsumptionValue(1);
    }

    @Test
    void defaultConstructor_getPowerConsumptionValue() {
        // Arrange
        String expected = "1.0";
        // Act
        String result = powerConsumptionValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

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
