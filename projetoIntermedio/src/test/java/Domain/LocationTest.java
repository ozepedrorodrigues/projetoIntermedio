package Domain;

import Factories.GPSLocationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class contains unit tests for the Location class.
 * It tests the constructor with valid and invalid parameters and getters.
 */
class LocationTest {

    GPSLocationFactory gpsLocationFactoryDouble;
    GPSLocation gpsLocationDouble;
    String validAddress;
    String validZipCode;
    double validLatitude;
    double validLongitude;

    /**
     * Sets up mock objects and valid data for testing the Location class.
     */
    @BeforeEach
    void setUp() {
        gpsLocationFactoryDouble = mock(GPSLocationFactory.class);
        gpsLocationDouble = mock(GPSLocation.class);
        validAddress = "Valid Address";
        validZipCode = "Valid ZipCode";
        validLatitude = 10.0;
        validLongitude = 10.0;
    }

    /**
     * Tests the valid construction of the Location class by setting up mock objects and valid data.
     * Verifies that the constructor initializes the Location object correctly.
     */
    @Test
    void testConstructorValid() {
        // Arrange
        when(gpsLocationFactoryDouble.createGPSLocation(validLatitude, validLongitude)).thenReturn(gpsLocationDouble);
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble);

        // Act
        String resultAddress = location.getAddress();
        String resultZipCode = location.getZipCode();
        GPSLocation resultGpsLocation = location.getGpsLocation();
        GPSLocationFactory resultGpsLocationFactory = location.getGpsLocationFactory();

        //Assert
        assertEquals(validAddress, resultAddress);
        assertEquals(validZipCode, resultZipCode);
        assertEquals(gpsLocationDouble, resultGpsLocation);
        assertEquals(gpsLocationFactoryDouble, resultGpsLocationFactory);
    }

    /**
     * Tests the behavior of the Location constructor when provided with an invalid address.
     * Verifies that an IllegalArgumentException is thrown with the expected error message.
     */
    @Test
    void testConstructorInvalidAddress() {
        // Arrange
        String invalidAddress = "";
        String expectedMessage = "Invalid parameters";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Location(invalidAddress, validZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble));
        String resultMessage = e.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the behavior of the Location constructor when provided with an invalid zipcode.
     * Verifies that an IllegalArgumentException is thrown with the expected error message.
     */
    @Test
    void testConstructorInvalidZipCode() {
        // Arrange
        String invalidZipCode = "";
        String expectedMessage = "Invalid parameters";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Location(validAddress, invalidZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble));
        String resultMessage = e.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the behavior of the Location constructor when provided with an invalid gps location factory.
     * Verifies that an IllegalArgumentException is thrown with the expected error message.
     */
    @Test
    void testConstructorInvalidGpsLocationFactory() {
        // Arrange
        GPSLocationFactory invalidGpsLocationFactory = null;
        String expectedMessage = "Invalid parameters";
        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Location(validAddress, validZipCode, validLatitude, validLongitude, invalidGpsLocationFactory));
        String resultMessage = e.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the getAddress method of the Location class.
     * Verifies that the method returns the expected address.
     */
    @Test
    void getAddress() {
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble);

        // Act
        String result = location.getAddress();

        //Assert
        assertEquals(validAddress, result);
    }

    /**
     * Tests the getZipCode method of the Location class.
     * Verifies that the method returns the expected zip code.
     */
    @Test
    void getZipCode() {
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble);

        // Act
        String result = location.getZipCode();

        //Assert
        assertEquals(validZipCode, result);
    }

    /**
     * Tests the getGpsLocation method of the Location class.
     * Verifies that the method returns the expected GPSLocation.
     */
    @Test
    void getGpsLocation() {
        when(gpsLocationFactoryDouble.createGPSLocation(validLatitude, validLongitude)).thenReturn(gpsLocationDouble);
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble);

        // Act
        GPSLocation result = location.getGpsLocation();

        //Assert
        assertEquals(gpsLocationDouble, result);
    }

    /**
     * Tests the getGpsLocationFactory method of the Location class.
     * Verifies that the method returns the expected GPSLocationFactory.
     */
    @Test
    void getGpsLocationFactory() {
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsLocationFactoryDouble);

        // Act
        GPSLocationFactory result = location.getGpsLocationFactory();

        //Assert
        assertEquals(gpsLocationFactoryDouble, result);
    }
}