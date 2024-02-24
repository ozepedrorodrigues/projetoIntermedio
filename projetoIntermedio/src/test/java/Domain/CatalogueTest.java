package Domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {


    @Test
    void addSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        SensorType expected = SensorType.HUMIDITY;
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void addSensorType_repeatedSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        catalogue.addSensorType(SensorType.HUMIDITY);
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertNull(result);
    }

    @Test
    void getCatalogue() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        int expectedSize = 2;
        //Act
        List<String> result = catalogue.getCatalogue();
        //Assert
        assertEquals(expectedSize, result.size());
    }

    @Test
    void getSensorTypeList_listOfUniqueSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        catalogue.addSensorType(SensorType.HUMIDITY);
        int expectedSize = 1;
        SensorType expected = SensorType.HUMIDITY;
        //Act
        List<SensorType> result = catalogue.getSensorTypeList();
        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expected, result.get(0));
    }

    @Test
    void getSensorTypeList_listOfTowSensorTypes() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        catalogue.addSensorType(SensorType.HUMIDITY);
        catalogue.addSensorType(SensorType.TEMPERATURE);
        int expectedSize = 2;
        SensorType expected0 = SensorType.HUMIDITY;
        SensorType expected1 = SensorType.TEMPERATURE;
        //Act
        List<SensorType> result = catalogue.getSensorTypeList();
        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expected0, result.get(0));
        assertEquals(expected1, result.get(1));
    }

    @Test
    void getSensorTypeList_emptyList() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        int expectedSize = 0;
        //Act
        List<SensorType> result = catalogue.getSensorTypeList();
        //Assert
        assertEquals(expectedSize, result.size());
    }

    @Test
    void getSensor_SensorOfTemperature() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        SensorType expected = SensorType.TEMPERATURE;
        //Act
        Sensor result = catalogue.getSensor("domain.SensorOfTemperature");
        //Assert
        assertNotNull(result);
        assertEquals(expected, result.getType());
    }

    @Test
    void getSensor_SensorOfHumidity() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        SensorType expected = SensorType.HUMIDITY;
        //Act
        Sensor result = catalogue.getSensor("domain.SensorOfHumidity");
        //Assert
        assertNotNull(result);
        assertEquals(expected, result.getType());
    }

    @Test
    void getSensor_SensorClassNameDoesNotExist() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue();
        //Act
        Sensor result = catalogue.getSensor("domain.SensorOfMove");
        //Assert
        assertNull(result);
    }
}