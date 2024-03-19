package SmartHome.domain;

import SmartHome.domain.room.Dimensions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Dimensions class.
 * It tests the constructor with valid and invalid parameters.
 */
class DimensionsTest {

    /**
     * Test to assess the Constructor of Dimensions with valid parameters.
     */
    @Test
    void testConstructorValidShouldNotThrowException() throws IllegalArgumentException {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 20.0;
        double expectedHeight = 30.0;
        // Act
        Dimensions dimensions = new Dimensions(expectedWidth, expectedLength, expectedHeight);
        double resultWidth = dimensions.getWidth();
        double resultLength = dimensions.getLength();
        double resultHeight = dimensions.getHeight();
        // Assert
        assertEquals(expectedWidth, resultWidth);
        assertEquals(expectedLength, resultLength);
        assertEquals(expectedHeight, resultHeight);
    }

    /**
     * Test to assess the Constructor of Dimensions with invalid width.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void testConstructorInvalidWidth() {
        // Arrange
        double expectedWidth = -10.0;
        double expectedLength = 20.0;
        double expectedHeight = 30.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));

    }

    /**
     * Test to assess the Constructor of Dimensions with invalid length.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void testConstructorInvalidLength() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = -20.0;
        double expectedHeight = 30.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));

    }

    /**
     * Test to assess the Constructor of Dimensions with invalid height.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void testConstructorInvalidHeight() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 20.0;
        double expectedHeight = -30.0;
        // Act & Assert
       assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));


    }

    /**
     * Test to assess the Constructor of Dimensions with zero width.
     * Should not Throw an Exception.
     */
    @Test
    void testConstructorZeroHeightValid() throws IllegalArgumentException{
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 20.0;
        double expectedHeight = 0.0;
        // Act
        Dimensions dimensions = new Dimensions(expectedWidth, expectedLength, expectedHeight);
        double resultWidth = dimensions.getWidth();
        double resultLength = dimensions.getLength();
        double resultHeight = dimensions.getHeight();
        // Assert
        assertEquals(expectedWidth, resultWidth);
        assertEquals(expectedLength, resultLength);
        assertEquals(expectedHeight, resultHeight);
    }

    /**
     * Test to assess the Constructor of Dimensions with zero width.
     * Should not Throw an Exception.
     */
    @Test
    void testConstructorZeroWidthInvalid() throws IllegalArgumentException {
        // Arrange
        double expectedWidth = 0.0;
        double expectedLength = 20.0;
        double expectedHeight = 30.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
    }

    /**
     * Test to assess the Constructor of Dimensions with zero length.
     * Should throw an IllegalArgumentException.
     */
    @Test
    void testConstructorZeroLengthInvalidShouldThrowException() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 0.0;
        double expectedHeight = 30.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));

    }

}