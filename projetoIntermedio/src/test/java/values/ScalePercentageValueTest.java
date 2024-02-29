package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ScalePercentageValue class.
 */
class ScalePercentageValueTest {

    /**
     * The ScalePercentageValue object to be used in the tests.
     */
    ScalePercentageValue scalePercentageValue;

    /**
     * Initializes the scalePercentageValue object for the tests.
     */

    @BeforeEach
    void setUp() {
        scalePercentageValue = new ScalePercentageValue(50);
    }


    /**
     * Verifies that the getScalePercentageValue method returns the expected scale percentage value.
     */
    @Test
    void defaultConstructor_getScalePercentageValue() {
        // Arrange
        int expected = 50;
        // Act
        int result = scalePercentageValue.getValue();
        // Assert
        assertEquals(expected, result, 0.01);
    }


    /**
     * Verifies that the toValue method generates the correct string representation with valid input.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        ScalePercentageValue currentReading = new ScalePercentageValue(50);
        String expected = "50";
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}