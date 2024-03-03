package actuators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LimiterDecimalTest {

    /**
     * Test of constructor of class LimiterDecimal
     */
    @Test
    void testConstructor() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertNotNull(actuator);
        assertEquals(result, expected);
    }

    /**
     * Test of isActive method
     * The method should return the state of the LimiterDecimal.
     * The state of the LimiterDecimal should be inactive (false).
     */
    @Test
    void isActive() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        // Act
        boolean expected = actuator.isActive();
        // Assert
        assertFalse(expected);
    }

    /**
     * Test of activate method
     * The method should activate the LimiterDecimal.
     * The state of the LimiterDecimal should be active (true).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void activate() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        boolean expected = true;
        // Act
        actuator.activate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(expected, result);
    }
/**
     * Test of activate method
     * The method should throw an IllegalStateException if the LimiterDecimal is already active.
     */
    @Test
    void activateAlreadyActive() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        // Act
        actuator.activate();
        // Assert
        assertThrows(IllegalStateException.class, () -> actuator.activate());
    }
/**
     * Test of deactivate method
     * The method should deactivate the LimiterDecimal.
     * The state of the LimiterDecimal should be inactive (false).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void deactivate() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        boolean expected = false;
        // Act
        actuator.activate();
        actuator.deactivate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(expected, result);
    }
/**
     * Test of deactivate method
     * The method should throw an IllegalStateException if the LimiterDecimal is already inactive.
     */
    @Test
    void deactivateAlreadyInactive() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> actuator.deactivate());
    }
}