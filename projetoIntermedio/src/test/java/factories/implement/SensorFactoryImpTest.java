package factories.implement;

import domain.Sensor;
import domain.SensorType;
import factories.SensorFactory;
import factories.ValueFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SensorFactoryImpTest {
    ValueFactory valueFactory = mock(ValueFactory.class);
    @Test
    void SensorFactoryConstructor() {
        //Act + assert
        assertDoesNotThrow(() -> new SensorFactoryImp("config.properties",valueFactory), "Should not throw exception.");
    }

    @Test
    void SensorFactoryConstructor_invalidPath() {
        //Arrange
        String expectedMessage = "Wrong file path name.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorFactoryImp("config.house",valueFactory));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getSensor_SensorOfTemperature() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties",valueFactory);
        SensorType expected = SensorType.TEMPERATURE;
        //Act
        Sensor result = sensorFactory.createSensor("SensorOfTemperature");
        //Assert
        assertNotNull(result);
        assertEquals(expected, result.getType());
    }

    @Test
    void getSensor_SensorOfHumidity() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties",valueFactory);
        SensorType expected = SensorType.HUMIDITY;
        //Act
        Sensor result = sensorFactory.createSensor("SensorOfHumidity");
        //Assert
        assertNotNull(result);
        assertEquals(expected, result.getType());
    }

    @Test
    void getSensor_SensorClassNameDoesNotExist() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties",valueFactory);
        //Act
        Sensor result = sensorFactory.createSensor("domain.SensorOfMove");
        //Assert
        assertNull(result);
    }
}