package actuators;

import domain.ActuatorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatoOfOnOffTest {
    /**
     * Test of the constructor, of class ActuatoOfOnOff. No parameters are needed.
     * The constructor should create a new instance of the ActuatoOfOnOff class, with the state of the ActuatoOfOnOff initialized to an inactive (false) state.
     */
    @Test
    void testConstructor() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertNotNull(actuator);
        assertEquals(result, expected);
    }

    /**
     * Test of the isActive method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should return the state of the ActuatoOfOnOff.
     * The state of the ActuatoOfOnOff should be inactive (false).
     */
    @Test
    void isActive() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the activate method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should activate the ActuatoOfOnOff.
     * The state of the ActuatoOfOnOff should be active (true).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void activate() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        boolean expected = true;
        // Act
        actuator.activate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the activate method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the ActuatoOfOnOff is already active.
     * The state of the ActuatoOfOnOff should be active (true).
     * The method should throw an IllegalStateException.
     */
    @Test
    void activateAlreadyActive() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        // Act
        actuator.activate();
        // Assert
        assertThrows(IllegalStateException.class, actuator::activate);
    }

    /**
     * Test of the activate method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the ActuatoOfOnOff is already active.
     * The state of the ActuatoOfOnOff should be active (true).
     */
    @Test
    void deactivate() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        boolean expected = false;
        // Act
        actuator.activate();
        actuator.deactivate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the deactivate method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the ActuatoOfOnOff is already deactivated.
     * The state of the ActuatoOfOnOff should be inactive (false).
     * The method should throw an IllegalStateException.
     */
    @Test
    void deactivateAlreadyInactive() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        // Act & Assert
        assertThrows(IllegalStateException.class, actuator::deactivate);
    }

    /**
     * Test of the getType method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should return the ActuatorType of the ActuatoOfOnOff.
     * The method should return ActuatorType.ONOFFSWITCH.
     */
    @Test
    void getType() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        ActuatorType expected = ActuatorType.ONOFFSWITCH;
        // Act
        ActuatorType result = actuator.getType();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the getId method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should return the ID of the ActuatoOfOnOff.
     */
    @Test
    void getId() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        int expected = 0;
        // Act
        int result = actuator.getId();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the setId method, of class ActuatoOfOnOff. No parameters are needed.
     * The method should set the ID of the ActuatoOfOnOff.
     */
    @Test
    void setId() {
        // Arrange
        ActuatoOfOnOff actuator = new ActuatoOfOnOff();
        int expected = 1;
        // Act
        actuator.setId(expected);
        int result = actuator.getId();
        // Assert
        assertEquals(result, expected);
    }
}