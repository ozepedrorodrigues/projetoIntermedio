package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.TemperatureValue;
import values.Value;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfTemperature class.
 */
class SensorOfTemperatureTest {


    /**
     * The Value to be treated as a double.
     */
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfTemperature class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
    }


    /**
     * Tests the valid construction of the SensorOfTemperature class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature();
        //Assert
        assertNotNull(sensorOfTemperature);
    }

    /**
     * Tests the getId method of the SensorOfTemperature class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature();
        // Act
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
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature();
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
        SensorOfTemperature sensorOfTemperature = new SensorOfTemperature();
        // Act
        SensorType result = sensorOfTemperature.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the behavior of the getValue method of SensorOfTemperature class.
     * Verifies that the method returns the current temperature value of the sensor.*/

    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        String defaultValue = "25.0";
        try (MockedConstruction<TemperatureValue> valueDouble = mockConstruction(TemperatureValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);})) {

            SensorOfTemperature sensorOfTemperature = new SensorOfTemperature();

            // Act
            Value result = sensorOfTemperature.getValue();

            // Assert
            List<TemperatureValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.valueToString());
        }
    }
}