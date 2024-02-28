package sensors;

import domain.SensorType;
import factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
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

    ValueFactory valueFactoryMock;
    SensorOfSunrise sensorOfSunrise;

    @BeforeEach
    void setUp () {
        valueFactoryMock = mock(ValueFactory.class);
        sensorOfSunrise = new SensorOfSunrise(valueFactoryMock);
    }

    @Test
    void constructor() {
        //Act
        SensorOfSunrise result = new SensorOfSunrise(valueFactoryMock);
        //Assert
        assertNotNull(result);
    }

    @Test
    void constructor_nullValueFactory() {
        //Arrange
        ValueFactory valueFactory1 = null;
        String expectedException = "Invalid parameters";
        //Act + assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new SensorOfSunrise(valueFactory1));
        assertEquals(expectedException, result.getMessage());
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

        when(valueFactoryMock.createSunriseValue(localDateTime)).thenReturn(sunriseValueMock);

        LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(10,0));
        //Act
        Value result = sensorOfSunrise.getValue(localDate);
        //Assert
        assertEquals(expected.toString(), result.valueToString());
    }
}