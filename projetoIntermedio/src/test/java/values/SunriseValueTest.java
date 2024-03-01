package values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SunriseValueTest {

    LocalDateTime localDateTime;


    @BeforeEach
    void setUp () {
        localDateTime = LocalDateTime.now();

    }

    @Test
    void constructor() {
        //Arrange
        //Act
        SunriseValue result = new SunriseValue(localDateTime);
        //Assert
        assertNotNull(result);
    }

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