package controllers;

import domain.House;
import domain.Location;
import dto.LocationDTO;
import factories.GPSFactory;
import factories.implement.*;
import mappers.MapperLocationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the DefineHouseLocationController class.
 */
class DefineHouseLocationControllerTest {

    private House house;
    private MapperLocationDTO mapperLocationDTO;
    private DefineHouseLocationController defineHouseLocationController;
    private String filepath = "config.properties";

    private String validAddress = "Valid Address";
    private String validZipCode = "Valid ZipCode";
    private double validLatitude = 10.0;
    private double validLongitude = 10.0;

    /**
     * Sets up valid data for testing the DefineHouseLocationController class.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath, new ValueFactoryImp()))));

        mapperLocationDTO = new MapperLocationDTO();
        defineHouseLocationController = new DefineHouseLocationController(house, mapperLocationDTO);
    }

    /**
     * Test the constructor of the DefineHouseLocationController class with invalid House.
     */
    @Test
    void testConstructorInvalidHouse() {
        // Arrange
        House invalidHouse = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new DefineHouseLocationController(invalidHouse, mapperLocationDTO));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test the constructor of the DefineHouseLocationController class with invalid MapperLocationDTO.
     */
    @Test
    void testConstructorInvalidMapper() {
        // Arrange
        MapperLocationDTO invalidMapperLocationDTO = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new DefineHouseLocationController(house, invalidMapperLocationDTO));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is the one expected.
     */
    @Test
    void testDefineLocationValid() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO(validAddress, validZipCode, validLatitude, validLongitude);

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(locationDTO);

        // Assert
        assertEquals(resultDTO.getAddress(), validAddress);
        assertEquals(resultDTO.getZipCode(), validZipCode);
        assertEquals(resultDTO.getLatitude(), validLatitude);
        assertEquals(resultDTO.getLongitude(), validLongitude);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid address(null) is used.
     */
    @Test
    void testDefineLocationNullAddress() {
        // Arrange
        String invalidAddress = null;
        LocationDTO invalidLocationDTO = new LocationDTO(invalidAddress, validZipCode, validLatitude, validLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid address(blank) is used.
     */
    @Test
    void testDefineLocationBlankAddress() {
        // Arrange
        String invalidAddress = " ";
        LocationDTO invalidLocationDTO = new LocationDTO(invalidAddress, validZipCode, validLatitude, validLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid zipcode(null) is used.
     */
    @Test
    void testDefineLocationNullZipCode() {
        // Arrange
        String invalidZipCode = null;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress, invalidZipCode, validLatitude, validLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid zipcode(blank) is used.
     */
    @Test
    void testDefineLocationBlankZipCode() {
        // Arrange
        String invalidZipCode = " ";
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress, invalidZipCode, validLatitude, validLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid latitude(under 90) is used.
     */
    @Test
    void testDefineLocationInvalidLatitudeLowerLimit() {
        // Arrange
        double invalidLatitude = -90.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress, validZipCode, invalidLatitude, validLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid latitude(above 90) is used.
     */
    @Test
    void testDefineLocationInvalidLatitudeUpperLimit() {
        // Arrange
        double invalidLatitude = 90.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress, validZipCode, invalidLatitude, validLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid longitude(under 180) is used.
     */
    @Test
    void testDefineLocationInvalidLongitudeLowerLimit() {
        // Arrange
        double invalidLongitude = -180.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress, validZipCode, validLatitude, invalidLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * Test the defineHouseLocation method.
     * Test if the LocationDTO returned is null when an invalid longitude(above 180) is used.
     */
    @Test
    void testDefineLocationInvalidLongitudeUpperLimit() {
        // Arrange
        double invalidLongitude = 180.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress, validZipCode, validLatitude, invalidLongitude);

        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }
}
