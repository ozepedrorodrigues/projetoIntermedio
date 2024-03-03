package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.EnergyConsumptionValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class SensorOfEnergyConsumptionTest {
    /**
     * The Value to be treated as a double.
     */
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfEnergyConsumption class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
    }

    /**
     * Tests the valid construction of the SensorOfEnergyConsumption class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfEnergyConsumption sensorOfEnergyConsumption = new SensorOfEnergyConsumption();
        //Assert
        assertNotNull(sensorOfEnergyConsumption);
    }

    /**
     * Tests the getId method of the SensorOfEnergyConsumption class.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfEnergyConsumption sensorOfEnergyConsumption = new SensorOfEnergyConsumption();
        // Act
        int result = sensorOfEnergyConsumption.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method of the SensorOfEnergyConsumption class.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 1;
        SensorOfEnergyConsumption sensorOfEnergyConsumption = new SensorOfEnergyConsumption();
        // Act
        int result = sensorOfEnergyConsumption.setId(1);
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfEnergyConsumption class.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.ENERGY_CONSUMPTION;
        SensorOfEnergyConsumption sensorOfEnergyConsumption = new SensorOfEnergyConsumption();
        // Act
        SensorType result = sensorOfEnergyConsumption.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method of the SensorOfEnergyConsumption class.
     */
    @Test
    void getValue() {
        // Arrange
        int expectedSize = 1;
        String defaultValue = "1.0";
        try (MockedConstruction<EnergyConsumptionValue> valueDouble = mockConstruction(EnergyConsumptionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);
        })) {

            SensorOfEnergyConsumption sensorOfEnergyConsumption = new SensorOfEnergyConsumption();

            // Act
            Value result = sensorOfEnergyConsumption.getValue();

            // Assert
            List<EnergyConsumptionValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.valueToString();
        }
    }
}