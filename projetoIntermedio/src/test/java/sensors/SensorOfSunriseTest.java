package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.SunriseValue;
import values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfSunrise class.
 */
class SensorOfSunriseTest {

    SensorOfSunrise sensorOfSunrise;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp () {
        sensorOfSunrise = new SensorOfSunrise();
    }

    /**
     * Test to check if the constructor of the SensorOfSunrise class is not null.
     */
    @Test
    void constructor() {
        //Act
        SensorOfSunrise result = new SensorOfSunrise();
        //Assert
        assertNotNull(result);
    }

    /**
     * Test to verify that the getId method returns the correct id of the sensor of sunrise.
     */
    @Test
    void getId() {
        //Arrange
        int expected = 0;
        //Act
        int result = sensorOfSunrise.getId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the method sets the ID of the sensor correctly.
     */
    @Test
    void setId() {
        //Arrange
        int expected = 10;
        //Act
        int result = sensorOfSunrise.setId(10);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the getType method returns the correct type of the sensor of sunrise.
     */
    @Test
    void getType() {
        //Arrange
        SensorType expected = SensorType.SUNRISE;
        //Act
        SensorType result = sensorOfSunrise.getType();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the getValue method returns the correct value for today.
     */
    @Test
    void getValueCurrentDate() {
        //Arrange
        LocalDate localDate = LocalDate.now();
        SunriseValue sunriseValueMock = mock(SunriseValue.class);
        when(sunriseValueMock.valueToString()).thenReturn(localDate + "T10:00" );

        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(10,0));

        SensorOfSunrise sensorOfSunrise = new SensorOfSunrise();
        LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(10,0));
        //Act
        Value result = sensorOfSunrise.getValue();
        //Assert
        assertEquals(expected.toString(), result.valueToString());
    }

    /**
     * Test to verify that the getValue method returns the correct value for a specified date.
     */
    @Test
    void getValueGivenCalendarDate() {
        //Arrange
        int expectedSize = 1;
        String defaultValue = "2023-01-10T10:00";
        LocalDate localDate = LocalDate.of(2023,1,10);

        try(MockedConstruction<SunriseValue> valueDouble = mockConstruction(SunriseValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);
        })) {
            LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(10,0));

            SensorOfSunrise sensorOfSunrise1 = new SensorOfSunrise();

            //Act
            Value result = sensorOfSunrise1.getValue(localDate);
            //Assert
            List<SunriseValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expected.toString(), valueDouble.constructed().get(0).valueToString());
            assertEquals(expected.toString(), result.valueToString());

        }
    }
}