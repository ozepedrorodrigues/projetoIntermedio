package actuators;

import domain.ActuatorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorOfOnOffTest {
    /**
     * Test of the constructor, of class ActuatorOfOnOff. No parameters are needed.
     * The constructor should create a new instance of the ActuatorOfOnOff class, with the state of the ActuatorOfOnOff initialized to an inactive (false) state.
     */
    @Test
    void testConstructor() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertNotNull(actuator);
        assertEquals(result, expected);
    }

    /**
     * Test of the isActive method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should return the state of the ActuatorOfOnOff.
     * The state of the ActuatorOfOnOff should be inactive (false).
     */
    @Test
    void isActive() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the activate method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should activate the ActuatorOfOnOff.
     * The state of the ActuatorOfOnOff should be active (true).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void activate() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        boolean expected = true;
        // Act
        actuator.activate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the activate method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the ActuatorOfOnOff is already active.
     * The state of the ActuatorOfOnOff should be active (true).
     * The method should throw an IllegalStateException.
     */
    @Test
    void activateAlreadyActive() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        // Act
        actuator.activate();
        // Assert
        assertThrows(IllegalStateException.class, actuator::activate);
    }

    /**
     * Test of the activate method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the ActuatorOfOnOff is already active.
     * The state of the ActuatorOfOnOff should be active (true).
     */
    @Test
    void deactivate() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        boolean expected = false;
        // Act
        actuator.activate();
        actuator.deactivate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the deactivate method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the ActuatorOfOnOff is already deactivated.
     * The state of the ActuatorOfOnOff should be inactive (false).
     * The method should throw an IllegalStateException.
     */
    @Test
    void deactivateAlreadyInactive() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        // Act & Assert
        assertThrows(IllegalStateException.class, actuator::deactivate);
    }

    /**
     * Test of the getType method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should return the ActuatorType of the ActuatorOfOnOff.
     * The method should return ActuatorType.ONOFFSWITCH.
     */
    @Test
    void getType() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        ActuatorType expected = ActuatorType.ONOFFSWITCH;
        // Act
        ActuatorType result = actuator.getType();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the getId method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should return the ID of the ActuatorOfOnOff.
     */
    @Test
    void getId() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        int expected = 0;
        // Act
        int result = actuator.getId();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the setId method, of class ActuatorOfOnOff. No parameters are needed.
     * The method should set the ID of the ActuatorOfOnOff.
     */
    @Test
    void setId() {
        // Arrange
        ActuatorOfOnOff actuator = new ActuatorOfOnOff();
        int expected = 1;
        // Act
        actuator.setId(expected);
        int result = actuator.getId();
        // Assert
        assertEquals(result, expected);
    }
}