package SmartHome.domain.sensors.values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AveragePowerConsumptionValueTest {

    AveragePowerConsumptionValue averagePowerConsumptionValue;

    /**
     * This method sets up the test environment, initializing the average power consumption value to 1.0.
     */
    @BeforeEach
    void setUp() {
        averagePowerConsumptionValue = new AveragePowerConsumptionValue(1);
    }

    /**
     * This test checks if the default constructor sets the average power consumption value to 1.0.
     */
    @Test
    void defaultConstructor_getAveragePowerConsumptionValue() {
        // Arrange
        String expected = "1.0";
        // Act
        String result = averagePowerConsumptionValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test checks if the value of the average power consumption is correctly converted to a string.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        AveragePowerConsumptionValue currentReading = new AveragePowerConsumptionValue(0);
        String expected = "0.0";
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}
