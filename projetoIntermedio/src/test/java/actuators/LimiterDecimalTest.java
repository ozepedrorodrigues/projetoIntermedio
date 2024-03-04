package actuators;

import static org.junit.jupiter.api.Assertions.*;

import domain.ActuatorType;
import org.junit.jupiter.api.Test;

class LimiterDecimalTest {

    /**
        * Test of the LimiterDecimal constructor with valid limits.
        * The LimiterDecimal should be inactive and the value should be null.
        */
    @Test
    void testConstructor() {
        // Arrange
        double lowerLimit = 25.0;
        double upperLimit = 75.0;
        int precision = 2;
        // Act
        LimiterDecimal actuator = new LimiterDecimal(lowerLimit, upperLimit, precision);
        boolean isActive = actuator.isActive();
        Double value = actuator.getValue();
        // Assert
        assertFalse(isActive);
        assertNull(value);
    }

    /**
     * Test of the LimiterDecimal constructor with invalid limits.
     * The LimiterDecimal should throw an IllegalArgumentException.
     */
    @Test
    void testConstructorInvalidLimits() {
        // Arrange
        double lowerLimit = 75.0;
        double upperLimit = 25.0;
        int precision = 2;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new LimiterDecimal(lowerLimit, upperLimit, precision));
    }

    /**
     * Test of isActive method
     * The method should return the state of the LimiterDecimal.
     * The state of the LimiterDecimal should be inactive (false).
     */
    @Test
    void isActiveFalse() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        // Act
        boolean expected = actuator.isActive();
        // Assert
        assertFalse(expected);
    }

    /**
     * Test of isActive method
     * The method should return the state of the LimiterDecimal.
     * The state of the LimiterDecimal should be active (true).
     */
    @Test
    void isActiveTrue() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        // Act
        actuator.activate();
        boolean expected = actuator.isActive();
        // Assert
        assertTrue(expected);
    }

    /**
     * Test of activate method
     * The method should activate the LimiterDecimal.
     * The state of the LimiterDecimal should be active (true).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void activateValid() {
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
        assertThrows(IllegalStateException.class, () -> actuator.deactivate());}

    /**
     * Test of getId method
     * The method should return the unique identifier of the LimiterDecimal.
     */

    @Test
    void getId() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        int expected = 0;
        // Act
        int result = actuator.getId();
        // Assert
        assertEquals(expected, result);}

    /**
     * Test if Setid method.
     * The method should set the unique identifier of the LimiterDecimal.
     */
    @Test
    void setId() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        int expected = 1;
        // Act
        actuator.setId(expected);
        int result = actuator.getId();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of getType method.
     * The method should return the correct ActuatorType(LIMITER).
     */
    @Test
    void getType() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        ActuatorType expected = ActuatorType.LIMITER_DECIMAL;
        // Act
        ActuatorType result = actuator.getType();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of getValue method.
     * The method should return the value of the LimiterDecimal.
     * The value of the LimiterDecimal should be null.
     */
    @Test
    void setValue() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        double expected = 50.0;
        // Act
        actuator.activate();
        actuator.setValue(expected);
        double result = actuator.getValue();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of setValue method with an invalid value.
     * The method should throw an IllegalArgumentException.
     */
    @Test
    void setValueInactive() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        double expected = 50.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> actuator.setValue(expected));
    }

    /**
     * Test of setValue method with an invalid value.
     * The method should throw an IllegalArgumentException.
     */
    @Test
    void setValueInvalid() {
        // Arrange
        LimiterDecimal actuator = new LimiterDecimal(25.0, 75.0, 2);
        double expected = 100.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> actuator.setValue(expected));
    }
}