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

class SensorOfSunriseTest {

    SensorOfSunrise sensorOfSunrise;

    @BeforeEach
    void setUp () {
        sensorOfSunrise = new SensorOfSunrise();
    }

    @Test
    void constructor() {
        //Act
        SensorOfSunrise result = new SensorOfSunrise();
        //Assert
        assertNotNull(result);
    }


    @Test
    void getId() {
        //Arrange
        int expected = 0;
        //Act
        int result = sensorOfSunrise.getId();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setId() {
        //Arrange
        int expected = 10;
        //Act
        int result = sensorOfSunrise.setId(10);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getType() {
        //Arrange
        SensorType expected = SensorType.SUNRISE;
        //Act
        SensorType result = sensorOfSunrise.getType();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getValue_currentDate() {
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

    @Test
    void getValue_givenCalendarDate() {
        //Arrange
        LocalDate localDate = LocalDate.of(2023,1,10);
        SunriseValue sunriseValueMock = mock(SunriseValue.class);
        when(sunriseValueMock.valueToString()).thenReturn("2023-01-10T10:00");

        try(MockedConstruction<Value> valueDouble = mockConstruction(Value.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(String.valueOf(sunriseValueMock));
        })) {
            LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(10,0));

            SensorOfSunrise sensorOfSunrise1 = new SensorOfSunrise();

            //Act
            Value result = sensorOfSunrise1.getValue(localDate);
            //Assert
            List<Value> values = valueDouble.constructed();
            assertEquals(1, values.size());
            assertEquals(expected.toString(), valueDouble.constructed().get(0).valueToString());
            assertEquals(expected.toString(), result.valueToString());
        }
    }
}