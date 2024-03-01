package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SunsetValueTest {
    LocalDateTime localDateTime;

    @BeforeEach
    void setUp () {
        localDateTime = LocalDateTime.now();
    }

    @Test
    void constructor() {
        //Act
        SunsetValue result = new SunsetValue(localDateTime);
        //Assert
        assertNotNull(result);
    }

    @Test
    void getValue() {
        //Arrange
        SunsetValue sunsetValue = new SunsetValue(localDateTime);
        LocalDateTime expected = localDateTime;
        //Act
        String result = sunsetValue.valueToString();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void valueToString() {
        //Arrange
        SunsetValue sunsetValue = new SunsetValue(localDateTime);
        String expected = localDateTime.toString();
        //Act
        String result = sunsetValue.valueToString();
        //Assert
        assertEquals(expected, result);
    }
}