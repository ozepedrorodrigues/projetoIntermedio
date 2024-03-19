package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import SmartHome.domain.sensors.values.OnOffValue;
import SmartHome.domain.sensors.values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the SensorOfOnOff class.
 */
class SensorOfOnOffTest {

    /**
     * The sensor of on off instance to be used in the tests.
     */
    SensorOfOnOff sensorOfOnOff;

    /**
     * Sets up the sensor of on off instance for the tests.
     * Resets the sensor ID before each test.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        sensorOfOnOff = new SensorOfOnOff();
    }

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
     * Verifies that the getId method returns the correct ID for the sensor.
     */
    @Test
    void getId() {
        // Arrange
        int expected = 0;
        // Act
        int result = sensorOfOnOff.getId();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor after generating a new ID.
     */
    @Test
    void getIdAfterGenerateId() {
        // Arrange
        int expected = 1;

        // Act
        sensorOfOnOff.generateId();
        int result = sensorOfOnOff.getId();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the generateId method sets the correct ID for the sensor.
     */
    @Test
    void generateId() {
        //Arrange
        int expected = 1;

        //Act
        int result = sensorOfOnOff.generateId();

        //Assert
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
