package actuators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnOffTest {
    /**
     * Test of the constructor, of class OnOff. No parameters are needed.
     * The constructor should create a new instance of the OnOff class, with the state of the OnOff initialized to an inactive (false) state.
     */
    @Test
    void testConstructor() {
        // Arrange
        OnOff actuator = new OnOff();
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertNotNull(actuator);
        assertEquals(result, expected);
    }

    /**
     * Test of the isActive method, of class OnOff. No parameters are needed.
     * The method should return the state of the OnOff.
     * The state of the OnOff should be inactive (false).
     */
    @Test
    void isActive() {
        // Arrange
        OnOff actuator = new OnOff();
        boolean expected = false;
        // Act
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the activate method, of class OnOff. No parameters are needed.
     * The method should activate the OnOff.
     * The state of the OnOff should be active (true).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void activate() {
        // Arrange
        OnOff actuator = new OnOff();
        boolean expected = true;
        // Act
        actuator.activate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the activate method, of class OnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the OnOff is already active.
     * The state of the OnOff should be active (true).
     * The method should throw an IllegalStateException.
     */
    @Test
    void activateAlreadyActive() {
        // Arrange
        OnOff actuator = new OnOff();
        // Act
        actuator.activate();
        // Assert
        assertThrows(IllegalStateException.class, actuator::activate);
    }

    /**
     * Test of the activate method, of class OnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the OnOff is already active.
     * The state of the OnOff should be active (true).
     */
    @Test
    void deactivate() {
        // Arrange
        OnOff actuator = new OnOff();
        boolean expected = false;
        // Act
        actuator.activate();
        actuator.deactivate();
        boolean result = actuator.isActive();
        // Assert
        assertEquals(result, expected);
    }

    /**
     * Test of the deactivate method, of class OnOff. No parameters are needed.
     * The method should throw an IllegalStateException if the OnOff is already deactivated.
     * The state of the OnOff should be inactive (false).
     * The method should throw an IllegalStateException.
     */
    @Test
    void deactivateAlreadyInactive() {
        // Arrange
        OnOff actuator = new OnOff();
        // Act & Assert
        assertThrows(IllegalStateException.class, actuator::deactivate);
    }
}