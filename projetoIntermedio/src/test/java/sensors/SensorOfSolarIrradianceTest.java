package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.SolarIrradianceValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfSolarIrradiance class.
 */
class SensorOfSolarIrradianceTest {

    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfSolarIrradiance class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
    }

    /**
     * Test for the SensorOfSolarIrradiance constructor.
     */
    @Test
    void testConstructor() {
        //Arrange + Act
        SensorOfSolarIrradiance sensorOfSolarIrradiance = new SensorOfSolarIrradiance();
        //Assert
        assertNotNull(sensorOfSolarIrradiance);
    }

    /**
     * Test for the getId method of the SensorOfSolarIrradiance class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void testGetIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfSolarIrradiance sensorOfSolarIrradiance = new SensorOfSolarIrradiance();
        // Act
        int result = sensorOfSolarIrradiance.getId();
        // Assert
        assertEquals(idExpected, result);


    }

    /**
     * Test for the setId method of the SensorOfSolarIrradiance class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void testSetId() {
        // Arrange
        int idExpected = 1;
        SensorOfSolarIrradiance sensorOfSolarIrradiance = new SensorOfSolarIrradiance();
        // Act
        sensorOfSolarIrradiance.setId(idExpected);
        int result = sensorOfSolarIrradiance.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Test for the getType method of the SensorOfSolarIrradiance class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void testGetType() {
        // Arrange
        SensorOfSolarIrradiance sensorOfSolarIrradiance = new SensorOfSolarIrradiance();
        // Act
        SensorType result = sensorOfSolarIrradiance.getType();
        // Assert
        assertEquals(SensorType.SOLAR_IRRADIANCE, result);
    }

    /**
     * Test for the getValue method of the SensorOfSolarIrradiance class.
     * Verifies that the method returns the value of the sensor correctly.
     */
    @Test
    void testGetValue() {
        //Arrange
        int expectedSize = 1;
        double defaultValue = 1200.0;
        try (MockedConstruction<SolarIrradianceValue> valueMockedConstruction = mockConstruction(SolarIrradianceValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(defaultValue);
        })) {
            SensorOfSolarIrradiance sensorOfSolarIrradiance = new SensorOfSolarIrradiance();
            //Act
            Value result = sensorOfSolarIrradiance.getValue();
            //Assert
            List<SolarIrradianceValue> values = valueMockedConstruction.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.getValue());
        }

    }
}

