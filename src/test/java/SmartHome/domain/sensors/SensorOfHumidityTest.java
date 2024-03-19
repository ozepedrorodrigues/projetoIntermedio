package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.HumidityValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfHumidity class.
 */
class SensorOfHumidityTest {

    /**
     * Sets up mock objects and valid data for testing the SensorOfHumidity class.
     */
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfHumidity class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
        IdGenerator.resetSensorId();
        IdGenerator.resetActuatorId();}


    /**
     * Tests the valid construction of the SensorOfHumidity class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
        //Assert
        assertNotNull(sensorOfHumidity);
    }

    /**
     * Tests the getId method of the SensorOfHumidity class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
        // Act
        int result = sensorOfHumidity.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getId method of the SensorOfHumidity class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdAltered() {
        // Arrange
        int idExpected = 1;
        SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
        sensorOfHumidity.generateId();
        // Act
        int result = sensorOfHumidity.getId();
        // Assert
        assertEquals(idExpected, result);}

    /**
     * Tests the setId method of the SensorOfHumidity class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void testGenerateId() {
        // Arrange
        SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
        int resultInitial = sensorOfHumidity.getId();
        int expectedInitial = 0;
        // Act
        int resultFinal = sensorOfHumidity.generateId();
        int expectedFinal = 1;
        // Assert
        assertEquals(expectedInitial, resultInitial);
        assertEquals(expectedFinal, resultFinal);
    }

    /**
     * Tests the getType method of the SensorOfHumidity class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.HUMIDITY;
        SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
        // Act
        SensorType result = sensorOfHumidity.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the behavior of the getValue method of SensorOfHumidity class.
     * Verifies that the method returns the value of the sensor correctly.
     */
    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        String defaultString = "25.0";
        try (MockedConstruction<HumidityValue> valueDouble = mockConstruction(HumidityValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultString);
        })) {
            SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
            // Act
            Value result = sensorOfHumidity.getValue();

            // Assert
            List<HumidityValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultString, result.valueToString());
        }
    }
}

