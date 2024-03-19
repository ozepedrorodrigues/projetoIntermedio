package SmartHome.domain.actuators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The ValueDoubleTest class is the companion testing class for the ValueDouble class.
 */
class ValueDoubleTest {
    /**
     * The ValueDouble object to be used in the tests.
     */
    ValueDouble value;

    /**
     * Initializes the ValueDouble object to be used in the tests.
     */
    @BeforeEach
    void setUp() {
        value = new ValueDouble(50.3);
    }

    /**
     * Tests the constructor of the ValueDouble class.
     */
    @Test
    void constructor() {
        //Act
        ValueDouble result = new ValueDouble(30);
        //Assert
        assertNotNull(result);
    }

    /**
     * Tests the valueToString method of the ValueDouble class.
     */
    @Test
    void valueToString() {
        //Arrange
        String expected = "50.3";
        //Act
        String result = value.valueToString();
        //Assert
        assertEquals(expected, result);
    }
}