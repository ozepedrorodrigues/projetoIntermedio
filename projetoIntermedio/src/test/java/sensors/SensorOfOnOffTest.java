package sensors;


import domain.SensorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.OnOffValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfOnOff class.
 */
class SensorOfOnOffTest {

    /**
     * Tests the behavior of the constructor of SensorOfOnOff class.
     * Verifies that the constructor creates a new SensorOfOnOff instance.
     */
    @Test
    void testConstructor() {
        // Arrange - Act
        SensorOfOnOff result = new SensorOfOnOff();
        //Assert
        assertNotNull(result);
    }

    /**
     * Tests the behavior of the getId and setId methods of SensorOfOnOff class.
     * Verifies that the methods return and set the unique identifier of the sensor.
     */
    @Test
    void testGetIdAndSetId() {
        // Arrange
        int expected = 10;
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

        // Act;
        sensorOfOnOff.setId(expected);
        int result = sensorOfOnOff.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the behavior of the getType method of SensorOfOnOff class.
     * Verifies that the type of the sensor is SensorType.ON_OFF.
     */
    @Test
    void testGetType() {
        // Arrange
        SensorType expected = SensorType.ON_OFF;
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

        // Act
        SensorType result = sensorOfOnOff.getType();

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
        int expectedSize = 1;
        try (MockedConstruction<OnOffValue> valueDouble = mockConstruction(OnOffValue.class, (mock, context) -> {
        })) {

            SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

            // Act
            Value result = sensorOfOnOff.getValue();
            List<OnOffValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(result, values.getFirst());
        }
    }

    /**
     * Tests the behavior of the getValue method of SensorOfOnOff class.
     * Verifies that the method returns the current ON/OFF value of the
     * sensor using the valueToString method.
     */
    @Test
    void testGetValueWithValueToString() {
        // Arrange
        int expectedSize = 1;
        String expectedString = "true";
        try (MockedConstruction<OnOffValue> valueDouble = mockConstruction(OnOffValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedString);
        })) {

            SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

            // Act
            Value result = sensorOfOnOff.getValue();
            List<OnOffValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(expectedString, result.valueToString());
        }
    }
}
