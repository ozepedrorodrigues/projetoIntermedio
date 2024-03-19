package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.SunsetValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * The SensorOfSunsetTest class provides unit tests for the SensorOfSunset class.
 */
class SensorOfSunsetTest {
    /**
     * The sensor of sunset instance to be used in the tests.
     */
    SensorOfSunset sensorOfSunset;

    /**
     * Sets up the sensor of sunset instance for the tests.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        sensorOfSunset = new SensorOfSunset();
    }

    /**
     * Verifies that the SensorOfSunset constructor creates a new SensorOfSunset instance.
     */
    @Test
    void constructor() {
        //Act
        SensorOfSunset result = new SensorOfSunset();
        //Assert
        assertNotNull(result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor.
     */
    @Test
    void getId() {
        //Arrange
        int expected = 0;
        //Act
        int result = sensorOfSunset.getId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor after generating a new ID.
     */
    @Test
    void getIdAfterGenerateId() {
        //Arrange
        int expected = 1;
        //Act
        sensorOfSunset.generateId();
        int result = sensorOfSunset.getId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the generateId method sets the correct ID for the sensor.
     */
    @Test
    void generateId() {
        //Arrange
        int expected = 1;
        //Act
        int result = sensorOfSunset.generateId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the getType method returns the correct type for the sensor.
     */
    @Test
    void getType() {
        //Arrange
        SensorType expected = SensorType.SUNSET;
        //Act
        SensorType result = sensorOfSunset.getType();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the getValue method returns the correct value for the current date.
     */
    @Test
    void getValueCurrentDate() {
        //Arrange
        LocalDate localDate = LocalDate.now();
        SunsetValue sunsetValueMock = mock(SunsetValue.class);
        when(sunsetValueMock.valueToString()).thenReturn(localDate + "T18:00");
        SensorOfSunset sensorOfSunset = new SensorOfSunset();
        LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(18, 0));
        //Act
        Value result = sensorOfSunset.getValue();
        //Assert
        assertEquals(expected.toString(), result.valueToString());
    }

    /**
     * Verifies that the getValue method returns the correct value for the specified date.
     */
    @Test
    void getValueGivenCalendarDate() {
        //Arrange
        int expectedSize = 1;
        String defaultValue = "2024-03-05T18:00";
        LocalDate localDate = LocalDate.of(2024, 3, 5);
        try (MockedConstruction<SunsetValue> valueDouble = mockConstruction(SunsetValue.class, (mock, context)
                -> when(mock.valueToString()).thenReturn(defaultValue))) {
            LocalDateTime expected = LocalDateTime.of(localDate, LocalTime.of(18, 0));
            SensorOfSunset sensorOfSunset1 = new SensorOfSunset();
            //Act
            Value result = sensorOfSunset1.getValue(localDate);
            //Assert
            List<SunsetValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expected.toString(), valueDouble.constructed().getFirst().valueToString());
            assertEquals(expected.toString(), result.valueToString());
        }
    }
}