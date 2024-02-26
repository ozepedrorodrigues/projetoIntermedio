package factories.implement;

import sensors.Sensor;
import domain.SensorType;
import factories.SensorFactory;
import factories.ValueFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SensorFactoryImpTest {
    ValueFactory valueFactory = mock(ValueFactory.class);

    /**
     * Test to verify that the constructor does not throw exceptions.
     */
    @Test
    void SensorFactoryConstructor() {
        //Act + assert
        assertDoesNotThrow(() -> new SensorFactoryImp("config.properties",valueFactory), "Should not throw exception.");
    }

    /**
     * Test to verify that the constructor throws an exception.
     */
    @Test
    void SensorFactoryConstructor_invalidPath() {
        //Arrange
        String expectedMessage = "Wrong file path name.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorFactoryImp("config.house",valueFactory));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the createSensor method returns a sensor when sensor class name exists.
     */
    @Test
    void createSensor_SensorOfTemperature() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties",valueFactory);
        SensorType expected = SensorType.TEMPERATURE;
        //Act
        Sensor result = sensorFactory.createSensor("SensorOfTemperature");
        //Assert
        assertNotNull(result);
        assertEquals(expected, result.getType());
    }

    /**
     * Test to verify that the createSensor method returns a sensor when sensor class name exists.
     */
    @Test
    void createSensor_SensorOfHumidity() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties",valueFactory);
        SensorType expected = SensorType.HUMIDITY;
        //Act
        Sensor result = sensorFactory.createSensor("SensorOfHumidity");
        //Assert
        assertNotNull(result);
        assertEquals(expected, result.getType());
    }

    /**
     * Test to verify that the createSensor method returns null when sensor class name does not exist.
     */
    @Test
    void createSensor_SensorClassNameDoesNotExist() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties",valueFactory);
        //Act
        Sensor result = sensorFactory.createSensor("domain.SensorOfMove");
        //Assert
        assertNull(result);
    }
}