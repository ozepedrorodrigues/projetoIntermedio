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
     * Verifies the ScalePercentageValue constructor sets the value correctly.
     * Verifies the getValue method returns the expected scale percentage value.
     */
    @Test
    void defaultConstructor_getScalePercentageValue() {
        // Arrange
        String expected = "50";
        // Act
        String result = scalePercentageValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the toValue method generates the correct string representation.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        String expected = "50";
        // Act
        String result = scalePercentageValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}