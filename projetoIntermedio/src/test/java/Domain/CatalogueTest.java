package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

    Catalogue catalogue;
    @BeforeEach
    void prepare() {
        catalogue = new Catalogue();
    }

    @Test
    void addSensorType() {
        //Arrange
        SensorType expected = SensorType.HUMIDITY;
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void addSensorType_repeatedSensorType() {
        //Arrange
        catalogue.addSensorType(SensorType.HUMIDITY);
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertNull(result);
    }

    @Test
    void getCatalogue() {
        //Arrange

        //Act
        //Assert
    }


    @Test
    void getSensorTypeList_listOfUniqueSensorType() {
        //Arrange
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
    void getSensorTypeList_listOfTowSensorTypes() {
        //Arrange
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
    void getSensorTypeList_emptyList() {
        //Arrange
        int expectedSize = 0;
        //Act
        List<SensorType> result = catalogue.getSensorTypeList();
        //Assert
        assertEquals(expectedSize, result.size());

    }

    @Test
    void getSensor() {
        //Arrange
        //Act
        //Assert

    }
}