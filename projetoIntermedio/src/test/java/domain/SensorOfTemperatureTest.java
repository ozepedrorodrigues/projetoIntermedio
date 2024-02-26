package domain;

import factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sensors.SensorOfTemperature;
import values.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the SensorOfTemperature class.
 */
class SensorOfTemperatureTest {

    ValueFactory valueFactoryDouble;
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfTemperature class.
     */
    @BeforeEach
    void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
        valueDouble = mock(Value.class);
    }


    /**
     * Tests the valid construction of the SensorOfTemperature class by giving it a invalid value factory.
     * Verifies that the constructor throws an IllegalArgumentException when given an invalid value factory.
     * The value factory is invalid if it is null.
     */
    @Test
    void testConstructorValid() {
        // Arrange
        ValueFactory invalidValueFactory = null;
        String expectedMessage = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorOfTemperature(invalidValueFactory));
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the getId method of the SensorOfTemperature class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getId() {
        // Arrange
        int idExpected = 1;
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature(valueFactoryDouble);
        // Act
        sensorOfTemperature.setId(idExpected);
        int result = sensorOfTemperature.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method of the SensorOfTemperature class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 1;
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature(valueFactoryDouble);
        // Act
        int result = sensorOfTemperature.setId(idExpected);
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfTemperature class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.TEMPERATURE;
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature(valueFactoryDouble);
        // Act
        SensorType result = sensorOfTemperature.getType();
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
        when(valueFactoryDouble.createTemperatureValue()).thenReturn(valueDouble);
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature(valueFactoryDouble);
        // Act
        Value result = sensorOfTemperature.getValue();
        // Assert
        assertEquals(valueDouble, result);
    }
}