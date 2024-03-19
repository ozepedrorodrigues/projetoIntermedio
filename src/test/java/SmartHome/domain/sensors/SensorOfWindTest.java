package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;
import SmartHome.domain.sensors.values.WindValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfWind class.
 */
class SensorOfWindTest {
    /**
     * The Value to be treated as a double.
     */
    Value valueDouble;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
        IdGenerator.resetSensorId();
        IdGenerator.resetActuatorId();
    }

    /**
     * Tests the valid construction of the SensorOfWind class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfWind wind = new SensorOfWind();
        //Assert
        assertNotNull(wind);
    }

    /**
     * Tests the getId method of the SensorOfWind class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfWind wind = new SensorOfWind();
        // Act
        int result = wind.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method of the SensorOfWind class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void getId() {
        // Arrange
        int idExpected = 1;
        SensorOfWind wind = new SensorOfWind();
        // Act
        int result = wind.generateId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the behavior of the generateId method of the SensorOfWind class.
     * Verifies that the method returns the next available ID for the sensor.
     */
    @Test
    void generateId() {
        // Arrange
        int idExpected = 1;
        SensorOfWind wind = new SensorOfWind();
        // Act
        wind.generateId();
        int result = wind.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Test the getType method of the SensorOfWind class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.WIND;
        SensorOfWind wind = new SensorOfWind();
        // Act
        SensorType result = wind.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Test the getValue method of the SensorOfWind class.
     * Verifies that the method returns the value of the sensor correctly.
     */
    @Test
    void getValue() {
        // Arrange
        int expectedSize = 1;
        String defaultValue = "Wind Direction: 0.0, Wind Speed: 0.0";
        try (MockedConstruction<WindValue> valueDouble = mockConstruction(WindValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);
        })) {
            SensorOfWind wind = new SensorOfWind();
            // Act
            Value result = wind.getValue();
            // Assert
            List<WindValue> constructed = valueDouble.constructed();
            assertEquals(expectedSize, constructed.size());
            assertEquals(defaultValue, result.valueToString());
        }
    }
}