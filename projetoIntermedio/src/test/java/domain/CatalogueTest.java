package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This Test class represents a group of tests to the class Catalogue.
 */
class CatalogueTest {
    /**
     * The path (as String) for the properties file where the available sensors and actuators are configured.
     */
    String filePathname = "configTest.properties";;

    /**
     * Test to verify that the constructor does not throw exceptions.
     * The file path is valid.
     */
    @Test
    void catalogueConstructorValidParameters() {
        //Act + assert
        assertDoesNotThrow(() -> new Catalogue(filePathname));
    }

    /**
     * Test to verify that the constructor throws an exception.
     */
    @Test
    void catalogueConstructorInvalidPath() {
        //Arrange
        String invalidFilePathname = "invalidPath.properties";
        //Act and Assert
        assertThrows(InstantiationException.class, () -> new Catalogue(invalidFilePathname));
    }

    /**
     * Test to verify that the getSensorsCatalogue method returns a list with the same size of the one in config.properties.
     */
    @Test
    void getSensorsCatalogue() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue(filePathname);
        int expectedSize = 2;
        //Act
        List<String> result = catalogue.getSensorsCatalogue();
        //Assert
        assertEquals(expectedSize, result.size());
    }

    /**
     * Test to verify that the getActuatorsCatalogue method returns a list with the same size of the one in config.properties.
     */
    @Test
    void getActuatorsCatalogue() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue(filePathname);
        int expectedSize = 1;
        //Act
        List<String> result = catalogue.getActuatorsCatalogue();
        //Assert
        assertEquals(expectedSize, result.size());
    }

}