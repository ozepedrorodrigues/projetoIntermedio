package SmartHome.domain.sensors.values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the SunsetValue class.
 */
class SunsetValueTest {
    /**
     * The local date and time used for testing.
     */
    LocalDateTime localDateTime;

    /**
     * Sets up the tests by initializing the local date and time.
     */
    @BeforeEach
    void setUp () {
        localDateTime = LocalDateTime.now();
    }

    /**
     * Tests the behavior of the constructor of SunsetValue class.
     * Verifies that the constructor creates a new SunsetValue instance.
     */
    @Test
    void constructor() {
        // Act
        SunsetValue result = new SunsetValue(localDateTime);
        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the getValue method of the SunsetValue class.
     * Verifies that the method returns the expected string.
     */
    @Test
    void getValue() {
        // Arrange
        SunsetValue sunsetValue = new SunsetValue(localDateTime);
        String expected = localDateTime.toString();
        // Act
        String result = sunsetValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the valueToString method of the SunsetValue class.
     * Verifies that the method returns the expected string.
     */
    @Test
    void valueToString() {
        // Arrange
        SunsetValue sunsetValue = new SunsetValue(localDateTime);
        String expected = localDateTime.toString();
        // Act
        String result = sunsetValue.valueToString();
        // Assert
        assertEquals(expected, result);
    }
}