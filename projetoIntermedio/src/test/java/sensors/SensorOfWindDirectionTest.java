package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.WindDirectionValue;
import values.Value;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SensorOfWindDirection class.
 */
class SensorOfWindDirectionTest {

    /**
     * The Value to be treated as an integer.
     */

    Value valueInteger;

    /**
     * Sets up mock objects and valid data for testing the SensorOfWindDirection class.
     */

    @BeforeEach
    void setUp() {
        valueInteger = mock(Value.class);
    }

    /**
     * Tests the valid construction of the SensorOfWindDirection class.
     */

    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfWindDirection sensorOfWindDirection = new SensorOfWindDirection();
        //Assert
        assertNotNull(sensorOfWindDirection);
    }

    /**
     * Tests the getId method of the SensorOfWindDirection class.
     * Verifies that the method returns the ID of the sensor correctly.
     */

    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfWindDirection sensorOfWindDirection = new SensorOfWindDirection();
        // Act
        int result = sensorOfWindDirection.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method of the SensorOfWindDirection class.
     * Verifies that the method sets the ID of the sensor correctly.
     */

    @Test
    void setId() {
        // Arrange
        int idExpected = 1;
        SensorOfWindDirection sensorOfWindDirection = new SensorOfWindDirection();
        // Act
        sensorOfWindDirection.setId(idExpected);
        int result = sensorOfWindDirection.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method of the SensorOfWindDirection class.
     * Verifies that the method returns the type of the sensor correctly.
     */

    @Test
    void getType() {
        // Arrange
        SensorOfWindDirection sensorOfWindDirection = new SensorOfWindDirection();
        // Act
        SensorType result = sensorOfWindDirection.getType();
        // Assert
        assertEquals(SensorType.WIND_DIRECTION, result);
    }

    /**
     * Tests the getValue method of the SensorOfWindDirection class.
     * Verifies that the method returns the value of the sensor correctly.*/


    @Test
    void testGetValue() {
        // Arrange
        int expectedSize = 1;
        String  defaultValue = "315";
        try(MockedConstruction<WindDirectionValue> valueInteger = mockConstruction(WindDirectionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);
        })) {
            SensorOfWindDirection sensorOfWindDirection = new SensorOfWindDirection();
            // Act
            Value result = sensorOfWindDirection.getValue();
            // Assert
            List<WindDirectionValue> constructed = valueInteger.constructed();
            assertEquals(expectedSize, constructed.size());
            assertEquals(defaultValue, result.valueToString());
        }
}}

