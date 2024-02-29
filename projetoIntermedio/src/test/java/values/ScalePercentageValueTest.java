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
        scalePercentageValue = new ScalePercentageValue();
    }


    /**
     * Verifies that the getScalePercentageValue method returns the expected scale percentage value.
     */
    @Test
    void defaultConstructor_getScalePercentageValue() {
        // Arrange
        int expected = 0;    // 0% by default, for now.
        // Act
        int result = scalePercentageValue.getPercentageValue();
        // Assert
        assertEquals(expected, result, 0.01);
    }


    /**
     * Verifies that the toValue method generates the correct string representation with valid input.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        ScalePercentageValue currentReading = new ScalePercentageValue();
        String expected = "0";    // 0% by default, for now.
        // Act
        String result = currentReading.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}