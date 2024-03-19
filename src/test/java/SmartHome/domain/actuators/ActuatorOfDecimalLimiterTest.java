package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the ActuatorOfDecimalLimiter class.
 */
class ActuatorOfDecimalLimiterTest {

    /**
     * The lowerLimit attribute to be used in tests.
     */
    double lowerLimit;

    /**
     * The upperLimit attribute to be used in tests.
     */
    double upperLimit;

    /**
     * The precision attribute to be used in tests.
     */
    int precision;

    /**
     * The actuator attribute to be used in tests.
     */
    ActuatorOfDecimalLimiter actuator;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp() {
        lowerLimit = 25.0;
        upperLimit = 75.0;
        precision = 2;
        actuator = new ActuatorOfDecimalLimiter();
        actuator.setLimits(lowerLimit, upperLimit);
        actuator.setPrecision(precision);
        IdGenerator.resetActuatorId();
    }

    /**
     * Test of the constructor of ActuatorOfDecimalLimiter class.
     */
    @Test
    void testConstructor() {
        // Act + Assert
        assertNotNull(actuator);
    }

    /**
     * Test of the ActuatorOfDecimalLimiter constructor with valid limits.
     */
    @Test
    void setLimitsValidLimits() {
        // Arrange
        double newLowerLimit = 30.0;
        double newUpperLimit = 70.0;
        // Act
        boolean result = actuator.setLimits(newLowerLimit, newUpperLimit);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of the ActuatorOfDecimalLimiter constructor with invalid limits.
     * The method should return false.
     */
    @Test
    void setLimitsInvalidLimits() {
        // Arrange
        double newLowerLimit = 70.0;
        double newUpperLimit = 30.0;
        // Act
        boolean result = actuator.setLimits(newLowerLimit, newUpperLimit);
        // Assert
        assertFalse(result);
    }

    /**
     * Test of the ActuatorOfDecimalLimiter constructor with the same limits.
     * The method should return true.
     */
    @Test
    void setLimitsSameLimits() {
        // Arrange
        double newLowerLimit = 30.0;
        double newUpperLimit = 30.0;
        // Act
        boolean result = actuator.setLimits(newLowerLimit, newUpperLimit);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of the ActuatorOfDecimalLimiter constructor with valid precision.
     * The method should return true.
     */

    @Test
    void setPrecisionValidPrecision() {
        // Arrange
        int newPrecision = 3;
        // Act
        boolean result = actuator.setPrecision(newPrecision);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of the ActuatorOfDecimalLimiter constructor with zero precision.
     * The method should return true.
     */
    @Test
    void setPrecisionZero() {
        // Arrange
        int newPrecision = 0;
        // Act
        boolean result = actuator.setPrecision(newPrecision);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of the ActuatorOfDecimalLimiter constructor with invalid precision.
     * The method should return false.
     */
    @Test
    void setPrecisionInvalidPrecision() {
        // Arrange
        int newPrecision = -3;
        // Act
        boolean result = actuator.setPrecision(newPrecision);
        // Assert
        assertFalse(result);
    }

    /**
     * Test of getId method
     * The method should return the unique identifier of the ActuatorOfDecimalLimiter.
     */
    @Test
    void getId() {
        // Arrange
        int expected = 0;
        // Act
        int result = actuator.getId();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of generateId method.
     * The method should set the unique identifier of the ActuatorOfDecimalLimiter.
     * This test ensures that the generated ID is not zero.
     */
    @Test
    void generateIdNotZero() {
        //Arrange
        int expected = 0;
        //Act
        int result = actuator.generateId();
        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test of generateId method.
     * The method should set the unique identifier of the ActuatorOfDecimalLimiter.
     */
    @Test
    void getIdAfterGenerateId(){
        // Arrange
        int expected = 1;
        // Act
        actuator.generateId();
        int result = actuator.getId();
        // Assert
        assertEquals(expected, result);
    }
    /**
     * Test of getType method.
     * The method should return the correct ActuatorType(LIMITER).
     */
    @Test
    void getType() {
        // Arrange
        ActuatorType expected = ActuatorType.LIMITER_DECIMAL;
        // Act
        ActuatorType result = actuator.getType();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test of operate method.
     * The method should return the value of the ActuatorOfDecimalLimiter.
     */
    @Test
    void operateLowerLimitValue() {
        // Arrange
        int expectedSize = 1;
        Value value = new ValueDouble(25.0);
        try (MockedConstruction<ValueDouble> valueDouble = mockConstruction(ValueDouble.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn("25.0");
        })) {
            String expected = "25.0";
            //Act
            Value result = actuator.operate(value);
            //Assert
            List<ValueDouble> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expected, values.get(0).valueToString());
            assertEquals(expected, result.valueToString());
        }
    }

    /**
     * Test of operate method.
     * The method should return the value of the ActuatorOfDecimalLimiter.
     */
    @Test
    void operateUpperLimitValue() {
        // Arrange
        int expectedSize = 1;
        Value value = new ValueDouble(75.0);
        try (MockedConstruction<ValueDouble> valueDouble = mockConstruction(ValueDouble.class, (mock, context) -> {
            double actualValue = (Double) context.arguments().get(0);
            when(mock.valueToString()).thenReturn(String.valueOf(actualValue));
        })) {
            String expected = "75.0";
            // Act
            Value result = actuator.operate(value);
            // Assert
            List<ValueDouble> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expected, values.get(0).valueToString());
            assertEquals(expected, result.valueToString());
        }
    }

    /**
     * Test of operate method.
     * The method should return the value of the ActuatorOfDecimalLimiter.
     */
    @Test
    void operateUpdateValueRoundUp() {
        // Arrange
        int expectedSize = 1;
        Value value = new ValueDouble(50.00777);
        try (MockedConstruction<ValueDouble> valueDouble = mockConstruction(ValueDouble.class, (mock, context) -> {
            double actualValue = (Double) context.arguments().get(0);
            when(mock.valueToString()).thenReturn(String.valueOf(actualValue));
        })) {
            String expected = "50.01";
            //Act
            Value result = actuator.operate(value);
            //Assert
            List<ValueDouble> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expected, values.get(0).valueToString());
            assertEquals(expected, result.valueToString());
        }
    }

    /**
     * Test of operate method.
     * The method should return the value of the ActuatorOfDecimalLimiter.
     */
    @Test
    void operateUpdateValueRoundDown() {
        // Arrange
        int expectedSize = 1;
        Value value = new ValueDouble(50.003);
        try (MockedConstruction<ValueDouble> valueDouble = mockConstruction(ValueDouble.class, (mock, context) -> {
            double actualValue = (Double) context.arguments().get(0);
            when(mock.valueToString()).thenReturn(String.valueOf(actualValue));
        })) {
            String expected = "50.0";
            //Act
            Value result = actuator.operate(value);
            //Assert
            List<ValueDouble> values = valueDouble.constructed();
            assertEquals(expectedSize, values.size());
            assertEquals(expected, values.get(0).valueToString());
            assertEquals(expected, result.valueToString());
        }
    }

    /**
     * Test of operate method with an invalid value.
     * The method should throw an IllegalArgumentException.
     */
    @Test
    void operateInvalidAboveLimit() {
        // Arrange
        Value value = mock(Value.class);
        when(value.valueToString()).thenReturn("100.0");
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> actuator.operate(value));
    }

    /**
     * Test of operate method with an invalid value.
     * The method should throw an IllegalArgumentException.
     */
    @Test
    void operateInvalidBelowLimit() {
        // Arrange
        Value value = mock(Value.class);
        when(value.valueToString()).thenReturn("10.0");
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> actuator.operate(value));
    }
}