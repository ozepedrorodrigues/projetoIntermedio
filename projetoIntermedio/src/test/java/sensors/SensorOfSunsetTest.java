package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SensorOfSunsetTest {
    SensorOfSunset sensorOfSunset;

    @BeforeEach
    void setUp () {
        sensorOfSunset = new SensorOfSunset();
    }

    @Test
    void constructor() {
        //Act
        SensorOfSunset result = new SensorOfSunset();
        //Assert
        assertNotNull(result);
    }

    @Test
    void getId() {
        //Arrange
        int expected = 0;
        //Act
        int result = sensorOfSunset.getId();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setId() {
        //Arrange
        int expected = 5;
        //Act
        int result = sensorOfSunset.setId(expected);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getType() {
        //Arrange
        SensorType expected = SensorType.SUNSET;
        //Act
        SensorType result = sensorOfSunset.getType();
        //Assert
        assertEquals(expected, result);
    }

}