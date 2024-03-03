package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyConsumptionValueTest {

    EnergyConsumptionValue energyConsumptionValue;

    /**
     * This method sets up the test environment, initializing the energy consumption value to 1.0.
     */
    @BeforeEach
    void setUp() {
        energyConsumptionValue = new EnergyConsumptionValue(1);
    }

    /**
     * This test checks if the default constructor sets the energy consumption value to 1.0.
     */
    @Test
    void defaultConstructor_getEnergyConsumptionValue() {
        // Arrange
        String expected = "1.0";
        // Act
        String result = energyConsumptionValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test checks if the value of the energy consumption is correctly converted to a string.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        EnergyConsumptionValue currentReading = new EnergyConsumptionValue(0);
        String expected = "0.0";
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}