package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.SolarIrradianceValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

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
        IdGenerator.resetSensorId();
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
        sensorOfSolarIrradiance.generateId();
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
     * Test for the generateId method of the SensorOfSolarIrradiance class.
     * Verifies that the method generates a new ID for the sensor correctly.
     */
    @Test
    void testGenerateId() {
        // Arrange
        SensorOfSolarIrradiance sensor = new SensorOfSolarIrradiance();
        int expectedId = 1; // Assuming that the IdGenerator starts from 1

        // Act
        int generatedId = sensor.generateId();

        // Assert
        assertEquals(expectedId, generatedId);
    }

    /**
     * Test for the getValue method of the SensorOfSolarIrradiance class.
     * Verifies that the method returns the value of the sensor correctly.
     */
    @Test
    void testGetValue() {
        //Arrange
        int expectedSize = 1;
        String defaultValue = "1200.0";
        try (MockedConstruction<SolarIrradianceValue> valueMockedConstruction = mockConstruction(SolarIrradianceValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);
        })) {
            SensorOfSolarIrradiance sensorOfSolarIrradiance = new SensorOfSolarIrradiance();
            //Act
            Value result = sensorOfSolarIrradiance.getValue();
            //Assert
            List<SolarIrradianceValue> values = valueMockedConstruction.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.valueToString());
        }
    }

}