package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
    * This class contains unit tests for the PositionScaleValue class.
 */
class PositionScaleValueTest {


    PositionScaleValue positionScaleValue;

    /**
     * Initializes the positionScaleValue object for the tests.
     */
    @BeforeEach
    void setUp() {
        positionScaleValue = new PositionScaleValue(50);}

    @Test
    void testConstructorGetPositionScaleValue() {
        // Arrange
        String expected = "50";
        // Act
        String result = positionScaleValue.valueToString();
        // Assert
        assertEquals(expected, result);}

    @Test
    void valueToString() {
        // Arrange
        String expected = "50";
        // Act
        String result = positionScaleValue.valueToString();
        // Assert
        assertEquals(expected, result);}
}