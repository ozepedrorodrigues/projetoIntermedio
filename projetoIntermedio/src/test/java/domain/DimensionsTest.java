package domain;

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
    void testConstructorValid() {
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
     */
    @Test
    void testConstructorZeroHeightValid() {
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
     */
    @Test
    void testConstructorZeroWidthInvalid() {
        // Arrange
        double expectedWidth = 0.0;
        double expectedLength = 20.0;
        double expectedHeight = 30.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
    }

    /**
     * Test to assess the Constructor of Dimensions with zero length.
     */
    @Test
    void testConstructorZeroLengthInvalid() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 0.0;
        double expectedHeight = 30.0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));

    }

}