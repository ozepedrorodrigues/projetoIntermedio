package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.ScalePercentageValue;
import values.Value;
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
     * Tests the setId method of the SensorOfScalePercentage class.
     * Verifies that the method sets the ID of the sensor correctly.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 10;
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();
        // Act
        int result = sensorOfScalePercentage.setId(idExpected);
        // Assert
        assertEquals(idExpected, result);
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
        int defaultValue = 50;
        try (MockedConstruction<ScalePercentageValue> valueDouble = mockConstruction(ScalePercentageValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(defaultValue);})) {

            SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage();

            // Act
            Value result = sensorOfScalePercentage.getValue();

            // Assert
            List<ScalePercentageValue> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(defaultValue, result.getValue());
        }
    }
}