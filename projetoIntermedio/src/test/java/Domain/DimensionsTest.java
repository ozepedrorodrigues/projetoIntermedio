package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Dimensions class.
 * It tests the constructor with valid and invalid parameters.
 */
class DimensionsTest {

    /**
     * Test to assess the Constructor of Location with valid parameters.
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
     * Test to assess the Constructor of Location with invalid width.
     */
    @Test
    void testConstructorInvalidWidth() {
        // Arrange
        double expectedWidth = -10.0;
        double expectedLength = 20.0;
        double expectedHeight = 30.0;
        String expectedMessage = "Invalid dimensions";
        // Act
        Exception exc = assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
        String resultMessage = exc.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of Location with invalid length.
     */
    @Test
    void testConstructorInvalidLength() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = -20.0;
        double expectedHeight = 30.0;
        String expectedMessage = "Invalid dimensions";
        // Act
        Exception exc = assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
        String resultMessage = exc.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of Location with invalid length.
     */
    @Test
    void testConstructorInvalidHeight() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 20.0;
        double expectedHeight = -30.0;
        String expectedMessage = "Invalid dimensions";
        // Act
        Exception exc = assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
        String resultMessage = exc.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    /**
     * Test to assess the Constructor of Location with zero height.
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
     * Test to assess the Constructor of Location with zero width.
     */
    @Test
    void testConstructorZeroWidthInvalid() {
        // Arrange
        double expectedWidth = 0.0;
        double expectedLength = 20.0;
        double expectedHeight = 30.0;
        String expectedMessage = "Invalid dimensions";
        // Act
        Exception exc = assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
        String resultMessage = exc.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of Location with zero width.
     */
    @Test
    void testConstructorZeroLengthInvalid() {
        // Arrange
        double expectedWidth = 10.0;
        double expectedLength = 0.0;
        double expectedHeight = 30.0;
        String expectedMessage = "Invalid dimensions";
        // Act
        Exception exc = assertThrows(IllegalArgumentException.class, () -> new Dimensions(expectedWidth, expectedLength, expectedHeight));
        String resultMessage = exc.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

}