package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.OnOffValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The ActuatorOfOnOffSwitchTest class is responsible for testing the methods of the ActuatorOfOnOffSwitch class.
 */
public class ActuatorOfOnOffSwitchTest {

    /**
     * The unique identifier of the actuator.
     */
    private int id;

    /**
     * The type of the actuator.
     */
    private ActuatorType type;

    /**
     * The actuator of limiter instance to be used in the tests.
     */
    ActuatorOfOnOffSwitch onOffSwitch;

    /**
     * The mock value to be used in the tests.
     */
    Value mockValue;
    /**
     * Sets up the actuator of limiter instance for the tests.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetActuatorId();
        onOffSwitch = new ActuatorOfOnOffSwitch();
        mockValue = mock(Value.class);
    }

    /**
     * Test of the constructor, of class ActuatorOfOnOffSwitch.
     */
    @Test
    void constructorOfActuatorValid() {
        // Act & Assert
        assertDoesNotThrow(ActuatorOfOnOffSwitch::new);
    }

    @Test
    public void getTypeReturnsCorrectType() {
        // Arrange
        ActuatorType expected = ActuatorType.ONOFFSWITCH;
        // Act
        ActuatorType result = onOffSwitch.getType();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void operateWithValidBinaryValueTurnsLoadOn() {
        // Arrange
        when(mockValue.valueToString()).thenReturn("true");
        // Act
        Value result = onOffSwitch.operate(mockValue);
        // Assert
        assertTrue(result instanceof OnOffValue);
    }

    @Test
    public void operateWithInvalidBinaryValueThrowsIllegalArgumentException() {
        // Arrange
        Value invalidBinaryValue = mock(Value.class);
        when(invalidBinaryValue.valueToString()).thenReturn("invalid");
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> onOffSwitch.operate(invalidBinaryValue));
    }

    @Test
    public void operateWithFalseBinaryValueTurnsLoadOff() {
        // Arrange
        when(mockValue.valueToString()).thenReturn("false");
        // Act
        Value result = onOffSwitch.operate(mockValue);
        // Assert
        assertTrue(result instanceof OnOffValue);
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
        int result = onOffSwitch.getId();
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
        onOffSwitch.generateId();
        int result = onOffSwitch.getId();
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
        int result = onOffSwitch.generateId();
        // Assert
        assertEquals(expected, result);
    }
}
