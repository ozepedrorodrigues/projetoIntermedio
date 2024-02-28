package sensors;

import domain.GPS;
import domain.Location;
import domain.SensorType;
import factories.GPSFactory;
import factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import values.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorOfOnOffTest {

    ValueFactory valueFactoryDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfOnOff class.
     */
    @BeforeEach
    void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
    }

    /**
     * Tests the behavior of the SensorOfOnOff constructor when provided with an invalid ValueFactory.
     * Verifies that an IllegalArgumentException is thrown with the expected error message.
     */
    @Test
    void testConstructorInvalidValueFactory() {
        // Arrange
        ValueFactory invalidValueFactory = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> new SensorOfOnOff(invalidValueFactory));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the behavior of the getType method of SensorOfOnOff class.
     * Verifies that the type of the sensor is SensorType.ON_OFF.
     */
    @Test
    void testGetType() {
        // Arrange
        SensorType expected = SensorType.ON_OFF;
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff(valueFactoryDouble);

        // Act
        SensorType result = sensorOfOnOff.getType();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the behavior of the getId method of SensorOfOnOff class.
     * Verifies that the method returns the default ID of the sensor correctly.
     */
    @Test
    void testGetDefaultId() {
        // Arrange
        int expected = 0;
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff(valueFactoryDouble);

        // Act;
        int result = sensorOfOnOff.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the behavior of the getId method of SensorOfOnOff class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void testGetIdAndSetId() {
        // Arrange
        int expected = 10;
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff(valueFactoryDouble);

        // Act;
        sensorOfOnOff.setId(expected);
        int result = sensorOfOnOff.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the behavior of the getValue method of SensorOfOnOff class.
     * Verifies that the method returns the current ON/OFF value of the sensor.
     */
    @Test
    void testGetValue() {
        // Arrange
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff(valueFactoryDouble);
        Value valueDouble = mock(Value.class);
        boolean defaultValue = true;

        when(valueFactoryDouble.createOnOffValue(defaultValue)).thenReturn(valueDouble);

        // Act
        Value result = sensorOfOnOff.getValue();

        // Assert
        assertNotNull(result);
        assertEquals(valueDouble, result);
    }

    /**
     * Tests the behavior of the getValue method of SensorOfOnOff class.
     * Verifies that the method returns the current ON/OFF value of the sensor.
     */
    @Test
    void testGetValueWithValueToString() {
        // Arrange
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff(valueFactoryDouble);
        Value valueDouble = mock(Value.class);
        boolean defaultValue = true;
        String expected = "mock string";

        when(valueFactoryDouble.createOnOffValue(defaultValue)).thenReturn(valueDouble);
        when(valueDouble.valueToString()).thenReturn(expected);

        // Act
        String result = sensorOfOnOff.getValue().valueToString();

        // Assert
        assertEquals(expected, result);
    }
}