package sensors;

import domain.SensorType;
import factories.ValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import values.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * This class contains unit tests for the SensorOfSolarIrradiance class.
 */
class SensorOfSolarIrradianceTest {

    /**
     * Mock of the ValueFactory class.
     */
    ValueFactory valueFactoryMock;
    /**
     * Instance of the SensorOfSolarIrradiance class to be tested.
     */
    SensorOfSolarIrradiance sensorOfSolarIrradiance;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    void setUp() {
        valueFactoryMock = mock(ValueFactory.class);
        sensorOfSolarIrradiance = new SensorOfSolarIrradiance(valueFactoryMock);
    }

    /**
     * Test for the SensorOfSolarIrradiance constructor.
     */
    @Test
    void constructor() {
        assertNotNull(sensorOfSolarIrradiance);
    }

    /**
     * Test for the SensorOfSolarIrradiance constructor when the ValueFactory parameter is null.
     */
    @Test
    void constructor_nullValueFactory() {
        assertThrows(IllegalArgumentException.class, () -> new SensorOfSolarIrradiance(null));
    }

    /**
     * Test for the getId method of the SensorOfSolarIrradiance class.
     */
    @Test
    void getId() {
        assertEquals(0, sensorOfSolarIrradiance.getId());
    }

    /**
     * Test for the setId method of the SensorOfSolarIrradiance class.
     */
    @Test
    void setId() {
        sensorOfSolarIrradiance.setId(10);
        assertEquals(10, sensorOfSolarIrradiance.getId());
    }

    /**
     * Test for the getValue method of the SensorOfSolarIrradiance class.
     */
    @Test
    void getValue() {
        Value value = sensorOfSolarIrradiance.getValue();
        assertNull(value);
    }
}