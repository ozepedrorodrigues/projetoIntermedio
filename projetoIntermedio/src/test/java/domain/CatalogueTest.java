package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

    /**
     * Test to verify that the constructor does not throw exceptions.
     */
    @Test
    void catalogueConstructor_validParameters() {
        //Act + assert
        assertDoesNotThrow(() -> new Catalogue("config.properties"), "Should not throw exception.");
    }

    /**
     * Test to verify that the constructor throws an exception.
     */
    @Test
    void catalogueConstructor_invalidPath() {
        //Arrange
        String expectedMessage = "Wrong file path name.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new Catalogue("config.house"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test to verify that the addSensorType method returns the expected sensor type when it is possible to add.
     */
    @Test
    void addSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        SensorType expected = SensorType.HUMIDITY;
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the addSensorType method returns null when the sensor type added already exists.
     */
    @Test
    void addSensorType_repeatedSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        catalogue.addSensorType(SensorType.HUMIDITY);
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertNull(result);
    }

    /**
     * Test to verify that the getCatalogue method returns a list with the same size of the one in config.properties.
     */
    @Test
    void getCatalogue() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        int expectedSize = 4;
        //Act
        List<String> result = catalogue.getCatalogue();
        //Assert
        assertEquals(expectedSize, result.size());
    }

    /**
     * Test to verify that the getSensorTypeList method returns list of unique sensor type.
     */
    @Test
    void getSensorTypeList_listOfUniqueSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        catalogue.addSensorType(SensorType.HUMIDITY);
        int expectedSize = 1;
        SensorType expected = SensorType.HUMIDITY;
        //Act
        List<SensorType> result = catalogue.getSensorTypeList();
        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expected, result.get(0));
    }

    /**
     * Test to verify that the getSensorTypeList method returns list of two sensor types.
     */
    @Test
    void getSensorTypeList_listOfTwoSensorTypes() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
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

    /**
     * Test to verify that the getSensorTypeList method returns an empty list when is empty.
     */
    @Test
    void getSensorTypeList_emptyList() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        int expectedSize = 0;
        //Act
        List<SensorType> result = catalogue.getSensorTypeList();
        //Assert
        assertEquals(expectedSize, result.size());
    }


}