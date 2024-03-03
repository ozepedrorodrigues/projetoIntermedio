package sensors;

import domain.SensorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.DewPointValue;
import values.OnOffValue;
import values.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The SensorOfDewPointTest class contains unit tests for the SensorOfDewPoint class.
 */
class SensorOfDewPointTest {

    /**
     * Mocked instance of the Value interface for reuse in multiple test cases.
     */
    Value valueDouble;

    /**
     * Sets up common test fixtures before each test execution.
     * Sets up mock objects and valid data for testing the SensorOfDewPoint class.
     */
    @BeforeEach
    void setUp() {
        valueDouble = mock(Value.class);
    }

    /**
     * Tests the basic functionality of the SensorOfDewPoint constructor.
     * Verifies that a SensorOfDewPoint object can be created successfully.
     */
    @Test
    void testConstructor() {
        // Arrange + Act
        SensorOfDewPoint dewPointSensor = new SensorOfDewPoint();
        //Assert
        assertNotNull(dewPointSensor);
    }

    /**
     * Tests the default behavior of the getId method in the SensorOfDewPoint class.
     * Verifies that the default unique identifier is correctly initialized.
     */
    @Test
    void getIdDefault() {
        // Arrange
        int idExpected = 0;
        SensorOfDewPoint dewPointSensor = new SensorOfDewPoint();
        // Act
        int result = dewPointSensor.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the setId method in the SensorOfDewPoint class.
     * Verifies that the unique identifier is correctly set by the setId method.
     */
    @Test
    void setId() {
        // Arrange
        int idExpected = 22;
        SensorOfDewPoint dewPointSensor = new SensorOfDewPoint();
        // Act
        int result = dewPointSensor.setId(22);
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * Tests the getType method in the SensorOfDewPoint class.
     * Verifies that the correct sensor type is returned.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.DEW_POINT;
        SensorOfDewPoint dewPointSensor = new SensorOfDewPoint();
        // Act
        SensorType result = dewPointSensor.getType();
        // Assert
        assertEquals(typeExpected, result);
    }

    /**
     * Tests the getValue method in the SensorOfDewPoint class using the MockedConstruction feature.
     * Verifies that the SensorOfDewPoint class correctly constructs a DewPointValue instance.
     * <p>
     * MockedConstruction: This test utilizes the MockedConstruction feature from Mockito.
     * It intercepts the construction of the DewPointValue class and provides a callback
     * to define the behavior of the constructed object. In this case,
     * it ensures that when valueToString is called on the mocked DewPointValue instance,
     * it returns the predefined defaultValue.
     * <p>
     * Isolation Focus: The primary focus here is on isolating the construction
     * of the DewPointValue instance. The test doesn't directly use a mock of the Value interface
     * but instead mocks the entire construction process
     */
    @Test
    void getValueIsolatingConstructionOfDewPointValueInstance() {
        // Arrange
        int expectedSize = 1;
        String defaultValue = "17.0";

        try (MockedConstruction<DewPointValue> valueDouble = mockConstruction(DewPointValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(defaultValue);
        })) {
            SensorOfDewPoint dewPointSensor = new SensorOfDewPoint();

            // Act
            Value result = dewPointSensor.getValue();

            // Assert
            assertEquals(expectedSize, valueDouble.constructed().size());
            assertEquals(defaultValue, result.valueToString());
        }
    }

    /**
     * Tests the getValue method in the SensorOfDewPoint class using direct mocks.
     * Verifies that the SensorOfDewPoint class correctly returns the mocked value.
     * <p>
     * Direct Mocking: This test directly mocks the Value interface and injects the mocked value
     * Direct Mocking: This test uses direct mocks with Mockito. It creates a mock of the Value interface
     * (Value mockedValue = mock(Value.class)) and explicitly defines the behavior of the
     * valueToString method to return a constant string value ("17.0").
     *
     * Isolation Focus: The primary focus here is on isolating the behavior of the SensorOfDewPoint class
     * by mocking its dependency, the Value interface. It doesn't involve the actual construction
     * of a DewPointValue instance.
     */
    @Test
    void getValueIsolatingSensorOfDewPointClassAndMockingItsDependencyValueInterface() {
        // Arrange
        Value mockedValue = mock(Value.class);                      // Mocking the Value interface

        when(mockedValue.valueToString()).thenReturn("17.0");   // Mocking behavior: When valueToString is called, return the mocked value

        // Act
        SensorOfDewPoint sensor = new SensorOfDewPoint();           // Creating a SensorOfDewPoint instance

        // Assert
        assertEquals("17.0", sensor.getValue().valueToString());    // Verifying that the correct Value is returned
    }
}