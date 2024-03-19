package SmartHome.domain.sensors;


import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.DewPointValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * The SensorOfDewPointTest class contains unit tests for the SensorOfDewPoint class.
 */
class SensorOfDewPointTest {

    /**
     * Mocked instance of the Value interface for reuse in multiple test cases.
     */
    SensorOfDewPoint dewPointSensor;

    /**
     * Sets up common test fixtures before each test execution.
     * Sets up mock objects and valid data for testing the SensorOfDewPoint class.
     * It resets the sensor and actuator identifiers and initializes the mocked Value instance.
     */
    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        dewPointSensor = new SensorOfDewPoint();
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
     * Verifies that the getId method returns the correct ID for the sensor.
     */
    @Test
    void getId() {
        //Arrange
        int expected = 0;
        //Act
        int result = dewPointSensor.getId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the behavior of the getId and generateId methods of SensorOfDewPoint class.
     * Verifies that the methods return and generates the unique identifier of the sensor.
     */
    @Test
    void getIdAndGenerateId() {
        // Arrange
        int expected = 1;
        // Act;
        dewPointSensor.generateId();
        int result = dewPointSensor.getId();
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
        int result = dewPointSensor.generateId();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the getType method in the SensorOfDewPoint class.
     * Verifies that the correct sensor type is returned.
     */
    @Test
    void getType() {
        // Arrange
        SensorType typeExpected = SensorType.DEW_POINT;
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
        Value mockedValue = mock(Value.class);
        when(mockedValue.valueToString()).thenReturn("17.0");
        // Act
        SensorOfDewPoint sensor = new SensorOfDewPoint();
        // Assert
        assertEquals("17.0", sensor.getValue().valueToString());
    }
}