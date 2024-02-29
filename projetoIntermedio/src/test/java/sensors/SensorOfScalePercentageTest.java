package sensors;

import domain.SensorType;
import factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import values.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the SensorOfScalePercentage class.
 */
class SensorOfScalePercentageTest {

    /**
     * The ValueFactory to be treated as a double.
     */
    ValueFactory valueFactoryDouble;
    /**
     * The Value to be treated as a double.
     */
    Value valueDouble;

    /**
     * Sets up mock objects and valid data for testing the SensorOfScalePercentage class.
     */
    @BeforeEach
    void setUp() {
        valueFactoryDouble = mock(ValueFactory.class);
        valueDouble = mock(Value.class);
    }

    /**
     * Tests the valid construction of the SensorOfScalePercentage class by giving it an invalid value factory.
     * Verifies that the constructor throws an IllegalArgumentException when given an invalid value factory.
     * The value factory is invalid if it is null.
     */
    @Test
    void testConstructorValid() {
        // Arrange
        ValueFactory invalidValueFactory = null;
        String expectedMessage = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorOfScalePercentage(invalidValueFactory));
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the getId method of the SensorOfScalePercentage class.
     * Verifies that the method returns the ID of the sensor correctly.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;     // This is the default value of the id
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage(valueFactoryDouble);
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
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage(valueFactoryDouble);
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
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage(valueFactoryDouble);
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
    void getValue() {
        // Arrange
        when(valueFactoryDouble.createScalePercentageValue()).thenReturn(valueDouble);
        SensorOfScalePercentage sensorOfScalePercentage = new SensorOfScalePercentage(valueFactoryDouble);
        // Act
        Value result = sensorOfScalePercentage.getValue();
        // Assert
        assertEquals(valueDouble, result);
    }
}