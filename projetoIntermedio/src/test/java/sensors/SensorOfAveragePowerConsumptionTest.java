package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.AveragePowerConsumptionValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SensorOfAveragePowerConsumptionTest {

    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfAveragePowerConsumption class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
    }

    /**
     * Tests the valid construction of the SensorOfAveragePowerConsumption class.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();
        // Assert
        assertNotNull(sensorOfAveragePowerConsumption);
    }

    /**
     * Tests the getId method of the SensorOfAveragePowerConsumption class.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();
        // Act
        int result = sensorOfAveragePowerConsumption.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method of the SensorOfAveragePowerConsumption class.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 1;
        SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();
        // Act
        int result = sensorOfAveragePowerConsumption.setId(1);
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfAveragePowerConsumption class.
     */
    @Test
    void getType() {
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
     */
    @Test
    void getValue() {
        // Arrange
        int expectedSize = 1;
        String expectedValue = "1.0";
        try (MockedConstruction<AveragePowerConsumptionValue> valueDouble = mockConstruction(AveragePowerConsumptionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedValue);
        })) {
            SensorOfAveragePowerConsumption sensorOfAveragePowerConsumption = new SensorOfAveragePowerConsumption();

            // Act
            Value result = sensorOfAveragePowerConsumption.getValue();

            // Assert
            List<AveragePowerConsumptionValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expectedValue, result.valueToString());
        }
    }
}