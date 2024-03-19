package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * The ActuatorOfLimiterTest class is responsible for testing the methods of the ActuatorOfLimiter class.
 */
class ActuatorOfLimiterTest {

    /**
     * The actuator of limiter instance to be used in the tests.
     */
    ActuatorOfLimiter actuator;
    /**
     * Sets up the actuator of limiter instance for the tests.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetActuatorId();
        actuator = new ActuatorOfLimiter();
    }

    /**
     * Test of constructor of ActuatorOfLimiter class.
     */
    @Test
    void constructorOfActuatorValid() {
        // Arrange
        ActuatorType expected = ActuatorType.LIMITER;
        // Act
        actuator = new ActuatorOfLimiter();
        ActuatorType type = actuator.getType();
        // Assert
        assertNotNull(actuator);
        assertEquals(expected, type);
    }

    /**
     * Test of getType method, of class ActuatorOfLimiter.
     * This test checks if the method returns the correct type of the actuator.
     */
    @Test
    void getType() {
        // Arrange
        ActuatorType expected = ActuatorType.LIMITER;
        // Act
        ActuatorType result = actuator.getType();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of setLimits method, of class ActuatorOfLimiter.
     * This test checks if the method returns true using valid limits.
     */
    @Test
    void setLimitsValid() {
        // Arrange
        int lower = -100;
        int upper = 100;
        // Act
        boolean result = actuator.setLimits(lower, upper);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of setLimits method, of class ActuatorOfLimiter.
     * This test checks if the method returns false using invalid limits.
     */
    @Test
    void setLimitsInvalid() {
        // Arrange
        int lower = 100;
        int upper = -100;
        // Act
        boolean result = actuator.setLimits(lower, upper);
        // Assert
        assertFalse(result);
    }

    /**
     * Test of setLimits method, of class ActuatorOfLimiter.
     * This test checks if the method returns true using the same value as the lower and upper limits.
     */
    @Test
    void setLimitsBorder() {
        // Arrange
        int lower = 0;
        int upper = 0;
        // Act
        boolean result = actuator.setLimits(lower, upper);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of operate method, of class ActuatorOfLimiter.
     * This test checks if the method throws an exception when the limits are not previously set.
     */
    @Test
    void operateWithoutSettingLimitsInvalid() {
        // Arrange
        Value mockValue = Mockito.mock(Value.class);
        when(mockValue.valueToString()).thenReturn("50");
        // Act & Assert
        assertThrows(NumberFormatException.class, () -> actuator.operate(mockValue));
    }

    /**
     * Test of operate method, of class ActuatorOfLimiter.
     * This test checks if the method returns the correct value valid using the lower limit.
     */
    @Test
    void operateValidValueLowerLimit() {
        // Arrange
        Value mockValue = Mockito.mock(Value.class);
        when(mockValue.valueToString()).thenReturn("-50");
        // Act
        actuator.setLimits(-50, 50);
        Value result = actuator.operate(mockValue);
        // Assert
        assertNotNull(result);
    }

    /**
     * Test of operate method, of class ActuatorOfLimiter.
     * This test checks if the method returns the correct value valid using the upper limit.
     */
    @Test
    void operateValidValueUpperLimit() {
        // Arrange
        Value mockValue = Mockito.mock(Value.class);
        when(mockValue.valueToString()).thenReturn("50");
        // Act
        actuator.setLimits(-50, 50);
        Value result = actuator.operate(mockValue);
        // Assert
        assertNotNull(result);
    }

    /**
     * Test of operate method, of class ActuatorOfLimiter.
     * This test checks if the method returns null using an invalid value (greater than the upper limit).
     */
    @Test
    void operateInvalidValueUpperLimit() {
        // Arrange
        Value mockValue = Mockito.mock(Value.class);
        when(mockValue.valueToString()).thenReturn("51");
        // Act
        actuator.setLimits(-50, 50);
        Value result = actuator.operate(mockValue);
        // Assert
        assertNull(result);
    }

    /**
     * Test of operate method, of class ActuatorOfLimiter.
     * This test checks if the method returns null using an invalid value (less than the lower limit).
     */
    @Test
    void operateInvalidValueLowerLimit() {
        // Arrange
        Value mockValue = Mockito.mock(Value.class);
        when(mockValue.valueToString()).thenReturn("-51");
        // Act
        actuator.setLimits(-50, 50);
        Value result = actuator.operate(mockValue);
        // Assert
        assertNull(result);
    }

    /**
     * Test of getId method, of class ActuatorOfLimiter.
     * This test checks if the method returns the correct id of the actuator.
     */
    @Test
    void getId() {
        // Arrange
        int expected = 0;
        // Act
        int result = actuator.getId();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of getId method, of class ActuatorOfLimiter.
     * This test checks if the method returns the correct id of the actuator after generating a new id.
     */
    @Test
    void getIdAfterGenerateId() {
        // Arrange
        int expected = 1;
        // Act
        actuator.generateId();
        int result = actuator.getId();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of generateId method, of class ActuatorOfLimiter.
     * This test checks if the method generates the correct id of the actuator.
     */
    @Test
    void generateId() {
        // Arrange
        int expected = 1;
        // Act
        int result = actuator.generateId();
        // Assert
        assertEquals(expected, result);
    }
}