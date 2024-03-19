package SmartHome.domain.sensors.values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElectricEnergyConsumptionValueTest {

    ElectricEnergyConsumptionValue electricEnergyConsumptionValue;

    /**
     * This method sets up the test environment, initializing the energy consumption value to 1.0.
     */
    @BeforeEach
    void setUp() {
        electricEnergyConsumptionValue = new ElectricEnergyConsumptionValue(1);
    }

    /**
     * This test checks if the default constructor sets the energy consumption value to 1.0.
     */
    @Test
    void defaultConstructor_getEnergyConsumptionValue() {
        // Arrange
        String expected = "1.0";
        // Act
        String result = electricEnergyConsumptionValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test checks if the value of the energy consumption is correctly converted to a string.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        ElectricEnergyConsumptionValue currentReading = new ElectricEnergyConsumptionValue(0);
        String expected = "0.0";
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}
