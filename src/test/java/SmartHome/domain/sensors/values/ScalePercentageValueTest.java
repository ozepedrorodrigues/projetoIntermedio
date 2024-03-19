package SmartHome.domain.sensors.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ScalePercentageValue class.
 */
class ScalePercentageValueTest {

    /**
     * Verifies the ScalePercentageValue constructor is instantiating the object correctly.
     */
    @Test
    void constructorInstantiation() {
        // Arrange
        double value = 50;
        // Act
        ScalePercentageValue scalePercentageValue = new ScalePercentageValue(value);
        // Assert
        assertNotNull(scalePercentageValue);
    }

    /**
     * Verifies the toValue method generates the correct string representation of a positive number (representing a
     * percentage value).
     */
    @Test
    void toValue_positiveNumber() {
        // Arrange
        double value = 50.5;
        String valueExpected = "50.5";
        // Act
        ScalePercentageValue scalePercentageValue = new ScalePercentageValue(value);
        String valueResult = scalePercentageValue.valueToString();
        // Assert
        assertEquals(valueExpected, valueResult);
    }

    /**
     * Verifies the toValue method generates the correct string representation of a negative number (representing a
     * percentage value).
     */
    @Test
    void toValue_negativeNumber() {
        // Arrange
        double value = -5.4;
        String valueExpected = "-5.4";
        // Act
        ScalePercentageValue scalePercentageValue = new ScalePercentageValue(value);
        String valueResult = scalePercentageValue.valueToString();
        // Assert
        assertEquals(valueExpected, valueResult);
    }

    /**
     * Verifies the toValue method generates the correct string representation of zero (representing a percentage value).
     */
    @Test
    void toValue_zeroNumber() {
        // Arrange
        double value = 0;
        String valueExpected = "0.0";
        // Act
        ScalePercentageValue scalePercentageValue = new ScalePercentageValue(value);
        String valueResult = scalePercentageValue.valueToString();
        // Assert
        assertEquals(valueExpected, valueResult);
    }
}