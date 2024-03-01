package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the SolarIrradianceValue class.
 */
class SolarIrradianceValueTest {

    SolarIrradianceValue solarIrradianceValue;

    /**
     * Initializes the solarIrradianceValue object for the tests.
     */
    @BeforeEach
    void setUp() {
        solarIrradianceValue = new SolarIrradianceValue(1200.0);
    }

    /**
     * Verifies the SolarIrradianceValue constructor sets the value correctly.
     * Verifies the getValue method returns the expected solar irradiance value.
     */
    @Test
    void testDefaultConstructorGetSolarIrradianceValue() {
        // Arrange
        String expected = "1200.0";
        // Act
        String result = solarIrradianceValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the toValue method generates the correct string representation.
     */
    @Test
    void testToValueValid() {
        // Arrange
        String expected = "1200.0";
        // Act
        String result = solarIrradianceValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

}