package SmartHome.domain.sensors;


import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.ElectricEnergyConsumptionValue;
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
 * This class contains unit tests for the SensorOfElectricEnergyConsumption class.
 */
class SensorOfElectricEnergyConsumptionTest {
    /**
     * The sensor of electric energy consumption instance to be used in the tests.
     */
    SensorOfElectricEnergyConsumption sensorOfElectricEnergyConsumption;

    /**
     * Sets up the sensor of electric energy consumption instance for the tests.
     * Resets the sensor ID before each test.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        sensorOfElectricEnergyConsumption = new SensorOfElectricEnergyConsumption();
    }

    /**
     * Tests the valid construction of the SensorOfElectricEnergyConsumption class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfElectricEnergyConsumption result = new SensorOfElectricEnergyConsumption();
        //Assert
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
        int result = sensorOfElectricEnergyConsumption.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Verifies that the getId method returns the correct ID for the sensor after generating it.
     */
    @Test
    void getIdAfterGenerateId() {
        // Arrange
        int idExpected = 1;
        // Act
        sensorOfElectricEnergyConsumption.generateId();
        int result = sensorOfElectricEnergyConsumption.getId();
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
        int result = sensorOfElectricEnergyConsumption.generateId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfElectricEnergyConsumption class.
     *  Verifies that the method returns the correct sensor type.
     */
    @Test
    void testGetType() {
        // Arrange
        SensorType typeExpected = SensorType.ELECTRIC_ENERGY_CONSUMPTION;
        SensorOfElectricEnergyConsumption sensorOfElectricEnergyConsumption = new SensorOfElectricEnergyConsumption();
        // Act
        SensorType result = sensorOfElectricEnergyConsumption.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method of the SensorOfElectricEnergyConsumption class.
     * Verifies that the method returns the current electric energy consumption value of the sensor.
     */
    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        try (MockedConstruction<ElectricEnergyConsumptionValue> valueDouble = mockConstruction(ElectricEnergyConsumptionValue.class, (mock, context) -> {
        })) {

            SensorOfElectricEnergyConsumption sensorOfElectricEnergyConsumption = new SensorOfElectricEnergyConsumption();

            // Act
            Value result = sensorOfElectricEnergyConsumption.getValue();
            List<ElectricEnergyConsumptionValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(result, values.getFirst());
        }
    }

    /**
     * Tests the behavior of the getValue method of SensorOfElectricEnergyConsumption class.
     * Verifies that the method returns the current electric energy consumption value of the sensor.
     * Uses the valueToString method of the ElectricEnergyConsumptionValue class.
     */
    @Test
    void getValueWithValueToString() {
        // Arrange
        int expectedSize = 1;
        String expectedValue = "1.0";
        try (MockedConstruction<ElectricEnergyConsumptionValue> valueDouble = mockConstruction(ElectricEnergyConsumptionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedValue);
        })) {
            SensorOfElectricEnergyConsumption sensorOfElectricEnergyConsumption = new SensorOfElectricEnergyConsumption();

            // Act
            Value result = sensorOfElectricEnergyConsumption.getValue();
            List<ElectricEnergyConsumptionValue> values = valueDouble.constructed();

            // Assert
            assertEquals(expectedSize, values.size());
            assertEquals(expectedValue, result.valueToString());
        }
    }
}