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
     * Test to verify that the getSensorsCatalogue method returns a list with the same size of the one in config.properties.
     */
    @Test
    void getSensorsCatalogue() throws InstantiationException {
        //Arrange
        Catalogue catalogue = new Catalogue("config.properties");
        int expectedSize = 13;
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
        Catalogue catalogue = new Catalogue("config.properties");
        int expectedSize = 4;
        //Act
        List<String> result = catalogue.getActuatorsCatalogue();
        //Assert
        assertEquals(expectedSize, result.size());
    }

}