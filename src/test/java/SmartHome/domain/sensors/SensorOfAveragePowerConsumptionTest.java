package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.AveragePowerConsumptionValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the SensorOfAveragePowerConsumption class.
 */
class SensorOfAveragePowerConsumptionTest {
    /**
     * The value of the sensor of average power consumption instance to be used in the tests.
     */
    SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption;

    /**
     * Sets up the sensor of average power consumption instance for the tests.
     * Resets the sensor ID before each test.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();
    }

    /**
     * Tests the valid construction of the SensorOfAveragePowerConsumption class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfAveragePowerConsumption result = new SensorOfAveragePowerConsumption();
        // Assert
        assertNotNull(result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor.
     */
    @Test
    void getId() {
        // Arrange
        int idExpected = 0;
        // Act
        int result = sensorOfAveragePowerConsumption.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor after generating an ID.
     */
    @Test
    void getIdAfterGenerateId() {
        // Arrange
        int idExpected = 1;
        // Act
        sensorOfAveragePowerConsumption.generateId();
        int result = sensorOfAveragePowerConsumption.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Verifies that the generateId method generates the correct ID for the sensor.
     */
    @Test
    void generateId() {
        // Arrange
        int idExpected = 1;
        // Act
        int result = sensorOfAveragePowerConsumption.generateId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfAveragePowerConsumption class.
     * Verifies that the method returns the correct sensor type.
     */
    @Test
    void testGetType() {
        // Arrange
        SensorType typeExpected = SensorType.AVERAGE_POWER_CONSUMPTION;
        SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();
        // Act
        SensorType result = sensorOfAveragePowerConsumption.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method of the SensorOfAveragePowerConsumption class.
     * Verifies that the method returns the current average power consumption value of the sensor.
     */
    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        try (MockedConstruction<AveragePowerConsumptionValue> valueDouble = mockConstruction(AveragePowerConsumptionValue.class, (mock, context) -> {
        })) {
            SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();

            // Act
            Value result = sensorOfAveragePowerConsumption.getValue();
            List<AveragePowerConsumptionValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(result, values.getFirst());
        }
    }

    /**
     * Tests the getValue method of the SensorOfAveragePowerConsumption class.
     * This ensures that the sensor correctly reports its initial average power consumption value.
     * Uses a mocked value object to ensure that the valueToString method returns the expected value.
     */
    @Test
    void getValueAfterSetValue() {
        // Arrange
        int expectedSize = 1;
        String expectedValue = "1.0";
        try (MockedConstruction<AveragePowerConsumptionValue> valueDouble = mockConstruction(AveragePowerConsumptionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedValue);
        })) {
            SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();

            // Act
            Value result = sensorOfAveragePowerConsumption.getValue();
            List<AveragePowerConsumptionValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(expectedValue, result.valueToString());
        }
    }
}