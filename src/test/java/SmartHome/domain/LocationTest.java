package SmartHome.domain;

import SmartHome.domain.house.GPS;
import SmartHome.domain.house.GPSFactory;
import SmartHome.domain.house.Location;
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

    /**
     * gpsFactory attribute.
     */
    GPSFactory gpsFactoryDouble;

    /**
     * gpsDouble attribute.
     */
    GPS gpsDouble;

    /**
     * validAddress attribute.
     * */
    String validAddress;

    /**
     * validZipCode attribute.
     */
    String validZipCode;

    /**
     * validLatitude attribute.
     */
    double validLatitude;

    /**
     * validLongitude attribute.
     */
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
     * Tests the constructor of the Location class with valid parameters.
     */
    @Test
    void testConstructor() {
        // Arrange - Act
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);
        // Assert
        assertNotNull(location);
    }

    /**
     * Tests the constructor of the Location class with valid parameters.
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

        //Assert
        assertEquals(validAddress, resultAddress);
        assertEquals(validZipCode, resultZipCode);
        assertEquals(gpsDouble, resultGps);
    }

    /**
     * Tests the constructor of the Location class with invalid parameters.
     * Verifies that the constructor throws an IllegalArgumentException when the address is null.
     */
    @Test
    void testConstructorInvalidAddressIsNull() {
        // Arrange
        String invalidAddress = null;

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidAddress,
                validZipCode, validLatitude, validLongitude, gpsFactoryDouble));
    }

    /**
     * Tests the constructor of the Location class with invalid parameters.
     * Verifies that the constructor throws an IllegalArgumentException when the address is empty.
     */
    @Test
    void testConstructorInvalidAddressIsEmpty() {
        // Arrange
        String invalidAddress = " ";

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidAddress,
                validZipCode, validLatitude, validLongitude, gpsFactoryDouble));
    }

    /**
     * Tests the constructor of the Location class with invalid parameters.
     * Verifies that the constructor throws an IllegalArgumentException when the zip code is null.
     */
    @Test
    void testConstructorInvalidZipCodeIsNull() {
        // Arrange
        String invalidZipCode = null;

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(validAddress,
                invalidZipCode, validLatitude, validLongitude, gpsFactoryDouble));
    }

    /**
     * Tests the constructor of the Location class with invalid parameters.
     * Verifies that the constructor throws an IllegalArgumentException when the zip code is empty.
     */
    @Test
    void testConstructorInvalidZipCodeIsEmpty() {
        // Arrange
        String invalidZipCode = " ";

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(validAddress,
                invalidZipCode, validLatitude, validLongitude, gpsFactoryDouble));
    }

    /**
     * Tests the constructor of the Location class with invalid parameters.
     * Verifies that the constructor throws an IllegalArgumentException when the GPSFactory is null.
     */
    @Test
    void testConstructorInvalidGpsLocationFactoryIsNull() {
        // Arrange
        GPSFactory invalidGpsFactory = null;

        // Act - Assert
        assertThrows(IllegalArgumentException.class, () -> new Location(validAddress,
                validZipCode, validLatitude, validLongitude, invalidGpsFactory));
    }

    /**
     * Tests the getAddress method of the Location class.
     * Verifies that the method returns the expected address.
     */
    @Test
    void testGetAddress() {
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
    void testGetZipCode() {
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
    void testGetGpsLocation() {
        // Arrange
        when(gpsFactoryDouble.createGPS(validLatitude, validLongitude)).thenReturn(gpsDouble);
        Location location = new Location(validAddress, validZipCode, validLatitude, validLongitude, gpsFactoryDouble);

        // Act
        GPS result = location.getGps();

        // Assert
        assertEquals(gpsDouble, result);
    }
}
