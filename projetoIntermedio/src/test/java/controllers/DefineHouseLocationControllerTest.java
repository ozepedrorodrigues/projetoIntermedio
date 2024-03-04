package controllers;

import domain.House;
import dto.LocationDTO;
import factories.implement.*;
import mappers.LocationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the DefineHouseLocationController class.
 */
class DefineHouseLocationControllerTest {

    /**
     * The House object used for testing.
     */
    private House house;

    /**
     * The LocationMapper object used for testing.
     */
    private LocationMapper locationMapper;

    /**
     * The DefineHouseLocationController object used for testing.
     */
    private DefineHouseLocationController defineHouseLocationController;

    /**
     * The filepath used for testing.
     */
    private String filepath = "config.properties";

    /**
     * Valid address for testing the DefineHouseLocationController class.
     */
    private String validAddress = "Valid Address";

    /**
     * Valid zip code for testing the DefineHouseLocationController class.
     */
    private String validZipCode = "Valid ZipCode";

    /**
     * Valid latitude for testing the DefineHouseLocationController class.
     */
    private double validLatitude = 10.0;

    /**
     * Valid longitude for testing the DefineHouseLocationController class.
     */
    private double validLongitude = 10.0;

    /**
     * Sets up the House, LocationMapper and DefineHouseLocationController objects for testing.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));

        locationMapper = new LocationMapper();
        defineHouseLocationController = new DefineHouseLocationController(house, locationMapper);
    }

    /**
     * Test the constructor of the DefineHouseLocationController class with invalid parameters.
     * Test if an IllegalArgumentException is thrown when the house is null.
     */
    @Test
    void testConstructorInvalidHouseIsNull() {
        // Arrange
        House invalidHouse = null;

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () ->
                new DefineHouseLocationController(invalidHouse, locationMapper));
    }

    /**
     * Test the constructor of the DefineHouseLocationController class with invalid parameters.
     * Test if an IllegalArgumentException is thrown when the locationMapper is null.
     */
    @Test
    void testConstructorInvalidMapperIsNull() {
        // Arrange
        LocationMapper invalidLocationMapper = null;

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () ->
                new DefineHouseLocationController(house, invalidLocationMapper));
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
    void testDefineLocationInvalidAddressIsNull() {
        // Arrange
        String invalidAddress = null;
        LocationDTO invalidLocationDTO = new LocationDTO(invalidAddress,
                validZipCode, validLatitude, validLongitude);
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
    void testDefineLocationInvalidAddressIsEmpty() {
        // Arrange
        String invalidAddress = " ";
        LocationDTO invalidLocationDTO = new LocationDTO(invalidAddress,
                validZipCode, validLatitude, validLongitude);
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
    void testDefineLocationInvalidZipCodeIsNull() {
        // Arrange
        String invalidZipCode = null;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress,
                invalidZipCode, validLatitude, validLongitude);
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
    void testDefineLocationInvalidZipCodeIsBlank() {
        // Arrange
        String invalidZipCode = " ";
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress,
                invalidZipCode, validLatitude, validLongitude);
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
    void testDefineLocationInvalidLatitudeBelowLowerLimit() {
        // Arrange
        double invalidLatitude = -90.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress,
                validZipCode, invalidLatitude, validLongitude);
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
    void testDefineLocationInvalidLatitudeHigherThanUpperLimit() {
        // Arrange
        double invalidLatitude = 90.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress,
                validZipCode, invalidLatitude, validLongitude);
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
    void testDefineLocationInvalidLongitudeBelowLowerLimit() {
        // Arrange
        double invalidLongitude = -180.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress,
                validZipCode, validLatitude, invalidLongitude);
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
    void testDefineLocationInvalidLongitudeHigherThanUpperLimit() {
        // Arrange
        double invalidLongitude = 180.1;
        LocationDTO invalidLocationDTO = new LocationDTO(validAddress,
                validZipCode, validLatitude, invalidLongitude);
        LocationDTO expectedDTO = null;

        // Act
        LocationDTO resultDTO = defineHouseLocationController.defineHouseLocation(invalidLocationDTO);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }
}
