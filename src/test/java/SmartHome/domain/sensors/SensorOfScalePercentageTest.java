package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.ScalePercentageValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfScalePercentage class.
 */
class SensorOfScalePercentageTest {

    /**
     * The Value to be treated as a double.
     */
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfScalePercentage class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
        IdGenerator.resetSensorId();
    }

    /**
     * Tests the valid construction of the SensorOfScalePercentage class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        //Assert
        assertNotNull(sensorOfScalePercentage);
    }

    /**
     * Tests the getId method of the SensorOfScalePercentage class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        // Act
        int result = sensorOfScalePercentage.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor.
     */
    @Test
    void getId() {
        //Arrange
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        int expected = 0;
        //Act
        int result = sensorOfScalePercentage.getId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor after generating a new ID.
     */
    @Test
    void getIdAfterGenerateId() {
        //Arrange
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        int expected = 1;
        //Act
        sensorOfScalePercentage.generateId();
        int result = sensorOfScalePercentage.getId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the generateId method sets the correct ID for the sensor.
     */
    @Test
    void generateId() {
        //Arrange
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        int expected = 1;
        //Act
        int result = sensorOfScalePercentage.generateId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the getType method of the SensorOfScalePercentage class.
     * Verifies that the method returns the type of the sensor correctly.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.SCALE_PERCENTAGE;
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        // Act
        SensorType result = sensorOfScalePercentage.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method of the SensorOfScalePercentage class.
     * Verifies that the method returns the value of the sensor correctly.
     */
    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        try (MockedConstruction<ScalePercentageValue> valueDouble = mockConstruction(ScalePercentageValue.class, (mock, context) -> {
        })) {

            SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();

            // Act
            Value result = sensorOfScalePercentage.getValue();
            List<ScalePercentageValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(result, values.getFirst());
        }
    }

    /**
     * Tests the getValue method of the SensorOfScalePercentage class.
     * Verifies that the method returns the value of the sensor correctly using the valueToString method.
     */
    @Test
    void testGetValueWithValueToString() {
        // Arrange
        int expectedSize = 1;
        String expectedString = "50.0";
        try (MockedConstruction<ScalePercentageValue> valueDouble = mockConstruction(ScalePercentageValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedString);
        })) {

            SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();

            // Act
            Value result = sensorOfScalePercentage.getValue();
            List<ScalePercentageValue> values = valueDouble.constructed();
            String resultString = result.valueToString();
            int size = values.size();

            // Assert
            assertEquals(expectedSize, size);
            assertEquals(expectedString, resultString);
        }
    }


}