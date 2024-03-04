package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains Unit tests for the DewPointValue, focusing on its methods related to
 *  * value conversion and representation and ensuring correct functionality
 * of the dew point value representation.
 */
class DewPointValueTest {

    /**
     * The DewPointValue object to be tested, initialized with a default value of 29.
     */
    DewPointValue dewPointValue;

    /**
     * Sets up the test fixture by initializing a DewPointValue instance
     * with a specific dew point value, expressed in ºC.
     */
    @BeforeEach
    void setUp() {
        this.dewPointValue = new DewPointValue(-7.48);
    }

    /**
     * Tests the default constructor of the DewPointValue class,
     * specifically the DewPointValue's valueToString() method. It ensures
     * that the default constructor correctly initializes the object and that
     * the value is converted to a string as expected.
     */
    @Test
    void defaultConstructor_getTemperatureValue() {
        // Arrange
        String expected = "-7.48";
        // Act
        String result = dewPointValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the DewPointValue`s valueToString() method with a valid dew point value.
     * It verifies that the DewPointValue's valueToString method generates the correct string representation.
     * This test ensures that the string representation of the dew point value matches the expected format.
     */
    @Test
    void toValue_Valid() {
        // Arrange
        String expected = "-7.48";
        // Act
        String result = dewPointValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}