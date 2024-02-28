package sensors;

import domain.SensorType;
import factories.ValueFactory;
import org.junit.jupiter.api.Test;
import values.SunriseValue;
import values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorOfSunriseTest {

    @Test
    void constructor() {
        //Arrange
        ValueFactory valueFactoryMock = mock(ValueFactory.class);
        //Act
        SensorOfSunrise result = new SensorOfSunrise(valueFactoryMock);
        //Assert
        assertNotNull(result);
    }

    @Test
    void constructor_nullValueFactory() {
        //Arrange
        ValueFactory valueFactory = null;
        String expectedException = "Invalid parameters";
        //Act + assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new SensorOfSunrise(valueFactory));
        assertEquals(expectedException, result.getMessage());
    }

    @Test
    void getId() {
        //Arrange
        ValueFactory valueFactory = mock(ValueFactory.class);
        SensorOfSunrise sensorOfSunrise = new SensorOfSunrise(valueFactory);
        int expected = 0;
        //Act
        int result = sensorOfSunrise.getId();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setId() {
        //Arrange
        ValueFactory valueFactory = mock(ValueFactory.class);
        SensorOfSunrise sensorOfSunrise = new SensorOfSunrise(valueFactory);
        int expected = 10;
        //Act
        int result = sensorOfSunrise.setId(10);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getType() {
        //Arrange
        ValueFactory valueFactory = mock(ValueFactory.class);
        SensorOfSunrise sensorOfSunrise = new SensorOfSunrise(valueFactory);
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

        ValueFactory valueFactory = mock(ValueFactory.class);
        when(valueFactory.createSunriseValue(localDateTime)).thenReturn(sunriseValueMock);

        SensorOfSunrise sensorOfSunrise = new SensorOfSunrise(valueFactory);
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

        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(10,0));

        ValueFactory valueFactory = mock(ValueFactory.class);
        when(valueFactory.createSunriseValue(localDateTime)).thenReturn(sunriseValueMock);

        SensorOfSunrise sensorOfSunrise = new SensorOfSunrise(valueFactory);
        LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(10,0));
        //Act
        Value result = sensorOfSunrise.getValue(localDate);
        //Assert
        assertEquals(expected.toString(), result.valueToString());
    }
}