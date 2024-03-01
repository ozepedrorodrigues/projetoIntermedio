package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Test class represents a group of tests to the class Catalogue.
 */
class CatalogueTest {

    /**
     * Test to verify that the constructor does not throw exceptions.
     */
    @Test
    void catalogueConstructorValidParameters() {
        //Act + assert
        assertDoesNotThrow(() -> new Catalogue("config.properties"), "Should not throw exception.");
    }

    /**
     * Test to verify that the constructor throws an exception.
     */
    @Test
    void catalogueConstructorInvalidPath() {
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
    void addSensorTypeRepeatedSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        catalogue.addSensorType(SensorType.HUMIDITY);
        //Act
        SensorType result = catalogue.addSensorType(SensorType.HUMIDITY);
        //Assert
        assertNull(result);
    }

    /**
     * Test to verify that the addActuatorType method returns the expected actuator type when it is possible to add.
     */
    @Test
    void addActuatorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        ActuatorType expected = ActuatorType.ONOFFSWITCH;
        //Act
        ActuatorType result = catalogue.addActuatorType(ActuatorType.ONOFFSWITCH);
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the addActuatorType method returns null when the actuator type added already exists.
     */
    @Test
    void addActuatorTypeRepeatedActuatorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        catalogue.addActuatorType(ActuatorType.LIMITER);
        //Act
        ActuatorType result = catalogue.addActuatorType(ActuatorType.LIMITER);
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
        int expectedSize = 10;
        //Act
        List<String> result = catalogue.getSensorCatalogue();
        //Assert
        assertEquals(expectedSize, result.size());
    }



    /**
     * Test to verify that the getSensorTypeList method returns list of unique sensor type.
     */
    @Test
    void getSensorTypeslistOfUniqueSensorType() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        catalogue.addSensorType(SensorType.HUMIDITY);
        int expectedSize = 1;
        SensorType expected = SensorType.HUMIDITY;
        //Act
        List<SensorType> result = catalogue.getSensorTypes();
        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expected, result.get(0));
    }

    /**
     * Test to verify that the getSensorTypeList method returns list of two sensor types.
     */
    @Test
    void getSensorTypesListOfTwoSensorTypes() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        catalogue.addSensorType(SensorType.HUMIDITY);
        catalogue.addSensorType(SensorType.TEMPERATURE);
        int expectedSize = 2;
        SensorType expected0 = SensorType.HUMIDITY;
        SensorType expected1 = SensorType.TEMPERATURE;
        //Act
        List<SensorType> result = catalogue.getSensorTypes();
        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expected0, result.get(0));
        assertEquals(expected1, result.get(1));
    }

    /**
     * Test to verify that the getSensorTypeList method returns an empty list when is empty.
     */
    @Test
    void getSensorTypesEmptyList() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        int expectedSize = 0;
        //Act
        List<SensorType> result = catalogue.getSensorTypes();
        //Assert
        assertEquals(expectedSize, result.size());
    }


}