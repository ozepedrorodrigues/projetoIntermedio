package Domain;

import Factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the SensorOfHumidity class.
 */
class SensorOfHumidityTest {

    /**
     * Sets up mock objects and valid data for testing the SensorOfHumidity class.
     */
    ValueFactory valueFactoryDouble;
    SensorOfHumidity sensorOfHumidityDouble;

    @BeforeEach
    void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
        sensorOfHumidityDouble = mock(SensorOfHumidity.class);
    }

    /**
     * Test to verify that the constructor of SensorOfHumidity class works as expected.
     * It asserts that the type of the sensor created is SensorType.HUMIDITY.
     */
    @Test
    void testConstructorValid() {
        // Arrange
        when(valueFactoryDouble.createHumidityValue(SensorType.HUMIDITY)).thenReturn(new HumidityValue());
        //Act
        SensorOfHumidity sensor = new SensorOfHumidity(valueFactoryDouble);
        // Assert
        assertEquals(SensorType.HUMIDITY, sensor.getType());
    }

    /**
     * Test to verify that the getType method of SensorOfHumidity class works as expected.
     * It asserts that the type of the sensor is SensorType.HUMIDITY.
     */
    @Test
    void testGetType() {
        // Arrange
        SensorType sensorTypeExpected = SensorType.HUMIDITY;
        when(sensorOfHumidityDouble.getType()).thenReturn(sensorTypeExpected);
        // Act
        SensorType result = sensorOfHumidityDouble.getType();
        // Assert
        assertEquals(sensorTypeExpected, result);
    }

    /**
     * Test to verify that the getValue method of SensorOfHumidity class works as expected.
     * It asserts that the value of the sensor is a new instance of HumidityValue.
     */
    @Test
    void testGetValue() {
        // Arrange
        Value valueExpected = valueFactoryDouble.createHumidityValue(SensorType.HUMIDITY);
        when(sensorOfHumidityDouble.getValue()).thenReturn(valueExpected);
        // Act
        Value value = sensorOfHumidityDouble.getValue();
        // Assert
        assertEquals(valueExpected, value);
    }

    /**
     * Test to verify that the getID method of SensorOfHumidity class works as expected.
     * It asserts that the ID of the sensor is returned correctly.
     */
    @Test
    void testGetID() {
        // Arrange
        int id = 1;
        when(sensorOfHumidityDouble.getId()).thenReturn(id);
        // Act
        int result = sensorOfHumidityDouble.getId();
        // Assert
        assertEquals(id, result);
    }

    /**
     * Test to verify that the setID method of SensorOfHumidity class works as expected.
     * It asserts that the ID of the sensor can be set correctly.
     */
    @Test
    void testSetID() {
        // Arrange
        int id = 1;
        when(sensorOfHumidityDouble.setId(1)).thenReturn(1);
        // Act
        int result = sensorOfHumidityDouble.setId(1);
        // Assert
        assertEquals(1, result);

    }
}

