package actuators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the Limiter class.
 * It tests the creation of the Limiter and its methods, ensuring that the Limiter behaves as expected.
 */
class LimiterTest {

    /**
     * Test the Limiter constructor with valid limits.
     * The Limiter should be inactive and the value should be null.
     */
    @Test
    void limiterConstructorValid() {
        // Arrange
        int lower = 0;
        int upper = 10;
        // Act
        Limiter limiter = new Limiter(lower, upper);
        boolean isActive = limiter.isActive();
        Integer value = limiter.getValue();
        // Assert
        assertFalse(isActive);
        assertNull(value);
    }

    /**
     * Test the Limiter constructor with invalid limits.
     * The Limiter should throw an IllegalArgumentException.
     */
    @Test
    void limiterConstructorInvalidLimits() {
        // Arrange
        int lower = 10;
        int upper = 0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Limiter(lower, upper));
    }

    /**
     * Test the isActive method when the Limiter is active.
     * The method should return true.
     */
    @Test
    void isActiveTrue() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        limiter.activate();
        boolean result = limiter.isActive();
        // Assert
        assertTrue(result);
    }

    /**
     * Test the isActive method when the Limiter is inactive.
     * The method should return false.
     */
    @Test
    void isActiveFalse() throws IllegalStateException {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        boolean result = limiter.isActive();
        // Assert
        assertFalse(result);
    }

    /**
     * Test the activate method with a valid Limiter.
     * The Limiter should become active.
     */
    @Test
    void activateValid() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        limiter.activate();
        boolean result = limiter.isActive();
        // Assert
        assertTrue(result);
    }

    /**
     * Test the activate method when the Limiter is already active.
     * An IllegalStateException should be thrown.
     */
    @Test
    void activateTwice() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        limiter.activate();
        // Assert
        assertThrows(IllegalStateException.class, () -> limiter.activate());
    }

    /**
     * Test the deactivate method with a valid Limiter.
     * The Limiter should become inactive.
     */
    @Test
    void deactivate() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        limiter.activate();
        boolean resultOn = limiter.isActive();
        limiter.deactivate();
        boolean resultOff = limiter.isActive();
        // Assert
        assertTrue(resultOn);
        assertFalse(resultOff);
    }

    /**
     * Test the deactivate method when the Limiter is already inactive.
     * An IllegalStateException should be thrown.
     */
    @Test
    void deactivateTwice() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        limiter.activate();
        limiter.deactivate();
        // Assert
        assertThrows(IllegalStateException.class, () -> limiter.deactivate());
    }

    /**
     * Test the setValue method with a valid Limiter and value.
     * The Limiter should be active and the value should be set.
     */
    @Test
    void setValue() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        int expected = 5;
        // Act
        limiter.activate();
        limiter.setValue(expected);
        int result = limiter.getValue();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test the setValue method with an inactive Limiter.
     * An IllegalArgumentException should be thrown.
     */
    @Test
    void setValueInactive() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        int value = 5;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> limiter.setValue(value));
    }

    /**
     * Test the setLimits method with valid limits.
     * The limits should be set.
     */
    @Test
    void setLimitsValid() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        // Act
        limiter.setLimits(5, 15);
        limiter.activate();
        boolean setValidValue = limiter.setValue(15);
        // Assert
        assertTrue(setValidValue);
    }

    /**
     * Test the setLimits method with invalid limits.
     * An IllegalArgumentException should be thrown.
     */
    @Test
    void setLimitsInvalid() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        int lower = 15;
        int upper = 5;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> limiter.setLimits(lower, upper));
    }

    /**
     * Test the setLimits method with border limits.
     * The limits should be set.
     */
    @Test
    void setLimitsBorderLimits() {
        // Arrange
        Limiter limiter = new Limiter(0, 1);
        // Act
        int limit = 5;
        limiter.setLimits(limit, limit);
        limiter.activate();
        boolean setValidValue = limiter.setValue(limit);
        // Assert
        assertTrue(setValidValue);
    }

    /**
     * Test the getValue method.
     * The method should return the correct value.
     */
    @Test
    void getValue() {
        // Arrange
        Limiter limiter = new Limiter(0, 10);
        int expected = 5;
        // Act
        limiter.activate();
        limiter.setValue(expected);
        int result = limiter.getValue();
        // Assert
        assertEquals(expected, result);
    }
}