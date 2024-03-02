package actuators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BlindsManager class.
 */
class BlindsManagerTest {

    /**
     * Test of the constructor, of class BlindsManager. No parameters are needed.
     * The constructor should create a new instance of the BlindsManager class, with the state of the BlindsManager initialized to an inactive (false) state.
     */
    @Test
    void testConstructor() {
        //Act
        BlindsManager result = new BlindsManager();
        //Assert
        assertNotNull(result);
        assertFalse(result.isActive());}

    /**
     * Test of the isActive method, of class BlindsManager. No parameters are needed.
     * The method should return the state of the BlindsManager.
     * The state of the BlindsManager should be inactive (false).
     */
    @Test
    void isActive() {
        //Arrange
        BlindsManager blindsManager = new BlindsManager();
        //Act
        boolean result = blindsManager.isActive();
        //Assert
        assertFalse(result);}

    /**
     * Test of the activate method, of class BlindsManager. No parameters are needed.
     * The method should activate the BlindsManager.
     * The state of the BlindsManager should be active (true).
     * The method should not throw an IllegalStateException.
     */
    @Test
    void activate() {
        //Arrange
        BlindsManager blindsManager = new BlindsManager();
        //Act
        blindsManager.activate();
        //Assert
        assertTrue(blindsManager.isActive());}

    /**
     * Test of the activate method, of class BlindsManager. No parameters are needed.
     * The method should throw an IllegalStateException if the BlindsManager is already active.
     * The state of the BlindsManager should be active (true).
     * The method should throw an IllegalStateException.
     */
    @Test
    void activateAlreadyActiveShouldThrowException() {
        //Arrange
        BlindsManager blindsManager = new BlindsManager();
        blindsManager.activate();
        //Act & Assert
        assertThrows(IllegalStateException.class, blindsManager::activate);}

    /**
     * Test of the deactivate method, of class BlindsManager. No parameters are needed.
     * The method should deactivate the BlindsManager.
     * The state of the BlindsManager should be inactive (false).
     * The method should not throw an IllegalStateException.
     */

    @Test
    void deactivate() {
        //Arrange
        BlindsManager blindsManager = new BlindsManager();
        //Act
        blindsManager.activate();
        blindsManager.deactivate();
        //Assert
        assertFalse(blindsManager.isActive());}

    /**
     * Test of the deactivate method, of class BlindsManager. No parameters are needed.
     * The method should throw an IllegalStateException, as the BlindsManager is already inactive.
     * The state of the BlindsManager should be inactive (false).
     */
    @Test
    void deactivateAlreadyInactiveShouldThrowException() {
        //Arrange
        BlindsManager blindsManager = new BlindsManager();
        //Act & Assert
        assertThrows(IllegalStateException.class, blindsManager::deactivate);}
}