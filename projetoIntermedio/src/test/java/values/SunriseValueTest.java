package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the TSunriseValue class.
 */
class SunriseValueTest {

    /**
     * The localDateTime attribute to be used in tests.
     */
    LocalDateTime localDateTime;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp () {
        localDateTime = LocalDateTime.now();

    }

    /**
     * Test to check if the constructor of the SunriseValue class is not null.
     */
    @Test
    void constructor() {
        //Act
        SunriseValue result = new SunriseValue(localDateTime);
        //Assert
        assertNotNull(result);
    }

    /**
     * Test to check if the valueToString method generates the correct string representation.
     */
    @Test
    void valueToString() {
        //Arrange
        SunriseValue sunriseValue = new SunriseValue(localDateTime);
        String expected = localDateTime.toString();
        //Act
        String result = sunriseValue.valueToString();
        //Assert
        assertEquals(expected, result);
    }
}