package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.WindSpeedValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfWindSpeed class.
 */
class SensorOfWindSpeedTest {

    /**
     * The Value to be treated as a double.
     */
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfWindSpeed class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
    }

    /**
     * Tests the valid construction of the SensorOfWindSpeed class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfWindSpeed sensorOfWindSpeed = new SensorOfWindSpeed();
        //Assert
        assertNotNull(sensorOfWindSpeed);
    }

    /**
     * Tests the getId method of the SensorOfWindSpeed class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfWindSpeed sensorOfWindSpeed = new SensorOfWindSpeed();
        // Act
        int result = sensorOfWindSpeed.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method of the SensorOfWindSpeed class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 1;
        SensorOfWindSpeed sensorOfWindSpeed = new SensorOfWindSpeed();
        // Act
        sensorOfWindSpeed.setId(1);
        int result = sensorOfWindSpeed.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfWindSpeed class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.WIND_SPEED;
        SensorOfWindSpeed sensorOfWindSpeed = new SensorOfWindSpeed();
        // Act
        SensorType result = sensorOfWindSpeed.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method of the SensorOfWindSpeed class.
     * Verifies that the method returns the value of the sensor correctly.
     */
    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        double valueExpected = 25.0;
        try (MockedConstruction<WindSpeedValue> valueDouble = mockConstruction(WindSpeedValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(valueExpected);
        })) {
            SensorOfWindSpeed sensorOfWindSpeed = new SensorOfWindSpeed();
            // Act
            Value result = sensorOfWindSpeed.getValue();
            // Assert
            List<WindSpeedValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(valueExpected, result.getValue());
        }
    }


}