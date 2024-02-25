package factories.implement;

import domain.Catalogue;
import domain.Sensor;
import domain.SensorType;
import factories.SensorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorFactoryImpTest {

    @Test
    void SensorFactoryConstructor() {
        //Act + assert
        assertDoesNotThrow(() -> new SensorFactoryImp("config.properties"), "Should not throw exception.");
    }

    @Test
    void SensorFactoryConstructor_invalidPath() {
        //Arrange
        String expectedMessage = "Wrong file path name.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorFactoryImp("config.house"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getSensor_SensorOfTemperature() throws InstantiationException {
        //Arrange
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties");
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
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties");
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
        SensorFactory sensorFactory = new SensorFactoryImp("config.properties");
        //Act
        Sensor result = sensorFactory.createSensor("domain.SensorOfMove");
        //Assert
        assertNull(result);
    }
}