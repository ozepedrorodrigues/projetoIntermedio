package sensors;


import domain.SensorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.OnOffValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorOfOnOffTest {

    /**
     * Tests the behavior of the getId method of SensorOfOnOff class.
     * Verifies that the method returns the default ID of the sensor correctly.
     */
    @Test
    void testGetDefaultId() {
        // Arrange
        int expected = 0;
        SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

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
   /* @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        boolean defaultValue = true;
        try (MockedConstruction<OnOffValue> valueDouble = mockConstruction(OnOffValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(defaultValue);
        })) {

            SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

            // Act
            Value result = sensorOfOnOff.getValue();

            // Assert
            List<OnOffValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.getValue());
        }
    }
*/
    /**
     * Tests the behavior of the getValue method of SensorOfOnOff class.
     * Verifies that the method returns the current ON/OFF value of the sensor.
     */
    @Test
    void testGetValueWithValueToString() {
        // Arrange
        int expectedSize = 1;
        String defaultString = "Value: true";
        try (MockedConstruction<OnOffValue> valueDouble = mockConstruction(OnOffValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultString);
        })) {

            SensorOfOnOff sensorOfOnOff = new SensorOfOnOff();

            // Act
            Value result = sensorOfOnOff.getValue();

            // Assert
            List<OnOffValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultString, result.valueToString());
        }
    }
}
