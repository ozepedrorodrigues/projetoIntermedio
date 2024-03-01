package sensors;

import domain.SensorType;
import factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import sensors.SensorOfHumidity;
import values.HumidityValue;
import values.TemperatureValue;
import values.Value;

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
    }


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
     * Tests the setId method of the SensorOfHumidity class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 1;
        SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
        // Act
        int result = sensorOfHumidity.setId(idExpected);
        // Assert
        assertEquals(idExpected, result);
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
        double defaultValue = 25.0;
        try (MockedConstruction<HumidityValue> valueDouble = mockConstruction(HumidityValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(defaultValue);
        })) {
            SensorOfHumidity sensorOfHumidity = new SensorOfHumidity();
            // Act
            Value result = sensorOfHumidity.getValue();

            // Assert
            List<HumidityValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.getValue());
        }
    }
}

