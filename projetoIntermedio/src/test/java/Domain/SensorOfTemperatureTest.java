package Domain;

import Factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the SensorOfTemperature class.
 */
class SensorOfTemperatureTest {

    ValueFactory valueFactoryDouble;
    SensorOfTemperature sensorOfTemperatureDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfTemperature class.
     */
    @BeforeEach
    void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
        sensorOfTemperatureDouble = mock(SensorOfTemperature.class);
    }


    /**
     * Tests the getId method of the SensorOfTemperature class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getId() {
        // Arrange
        int idExpected = 1;
        when(sensorOfTemperatureDouble.getId()).thenReturn(idExpected);
        // Act
        int result = sensorOfTemperatureDouble.getId();
        // Assert
        assertEquals(id, result);
    }

    /**
     * Tests the setId method of the SensorOfTemperature class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void setId() {
        // Arrange
        when(sensorOfTemperatureDouble.setId(1)).thenReturn(1);
        // Act
        int result = sensorOfTemperatureDouble.setId(1);
        // Assert
        assertEquals(1, result);
    }

    /**
     * Tests the getType method of the SensorOfTemperature class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.TEMPERATURE;
        when(sensorOfTemperatureDouble.getType()).thenReturn(typeExpected);
        // Act
        SensorType result = sensorOfTemperatureDouble.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method of the SensorOfTemperature class.
     * Verifies that the method returns the value from the sensor correctly.
     */
    @Test
    void getValue() {
        // Arrange
        Value valueExpected = valueFactoryDouble.createTemperatureValue(SensorType.TEMPERATURE);
        when(sensorOfTemperatureDouble.getValue()).thenReturn(valueExpected);
        // Act
        Value result = sensorOfTemperatureDouble.getValue();
        // Assert
        assertEquals(valueExpected, result);
    }
}