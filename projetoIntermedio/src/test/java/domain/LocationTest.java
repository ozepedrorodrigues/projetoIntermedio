package domain;

import factories.GPSFactory;
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

    GPSFactory gpsFactoryDouble;
    GPS gpsDouble;
    String validAddress;
    String validZipCode;
    double validLatitude;
    double validLongitude;

    /**
     * Sets up mock objects and valid data for testing the Location class.
     */
    @BeforeEach
    void setUp() {
        gpsFactoryDouble = mock(GPSFactory.class);
        gpsDouble = mock(GPS.class);
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
        when(gpsFactoryDouble.createGPS(validLatitude, validLongitude)).thenReturn(gpsDouble);
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);

        // Act
        String resultAddress = location.getAddress();
        String resultZipCode = location.getZipCode();
        GPS resultGps = location.getGps();
        GPSFactory resultGpsFactory = location.getGpsLocationFactory();

        //Assert
        assertEquals(validAddress, resultAddress);
        assertEquals(validZipCode, resultZipCode);
        assertEquals(gpsDouble, resultGps);
        assertEquals(gpsFactoryDouble, resultGpsFactory);
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
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Location(invalidAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble));
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
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Location(validAddress, invalidZipCode, validLatitude, validLongitude, gpsFactoryDouble));
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
        GPSFactory invalidGpsFactory = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Location(validAddress, validZipCode, validLatitude, validLongitude, invalidGpsFactory));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Tests the getAddress method of the Location class.
     * Verifies that the method returns the expected address.
     */
    @Test
    void getAddress() {
        // Arrange
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);

        // Act
        String result = location.getAddress();

        // Assert
        assertEquals(validAddress, result);
    }

    /**
     * Tests the getZipCode method of the Location class.
     * Verifies that the method returns the expected zip code.
     */
    @Test
    void getZipCode() {
        // Arrange
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);

        // Act
        String result = location.getZipCode();

        // Assert
        assertEquals(validZipCode, result);
    }

    /**
     * Tests the getGpsLocation method of the Location class.
     * Verifies that the method returns the expected GPS.
     */
    @Test
    void getGpsLocation() {
        // Arrange
        when(gpsFactoryDouble.createGPS(validLatitude, validLongitude)).thenReturn(gpsDouble);
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);

        // Act
        GPS result = location.getGps();

        // Assert
        assertEquals(gpsDouble, result);
    }

    /**
     * Tests the getGpsLocationFactory method of the Location class.
     * Verifies that the method returns the expected GPSFactory.
     */
    @Test
    void getGpsLocationFactory() {
        // Arrange
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);

        // Act
        GPSFactory result = location.getGpsLocationFactory();

        // Assert
        assertEquals(gpsFactoryDouble, result);
    }
}