package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * GPSTest is a test class for GPS.
 * It uses JUnit to test the functionality of GPS methods.
 */
class GPSTest {
    /**
     * Constructor with valid parameters
     */
    @Test
    void testConstructorValidParameters() {
        // Arrange
        double latitude = 1;
        double longitude = 2;
        // Act
        GPS gps = new GPS(latitude, longitude);
        double resultLatitude = gps.getLatitude();
        double resultLongitude = gps.getLongitude();
        // Assert
        assertEquals(latitude, resultLatitude);
        assertEquals(longitude, resultLongitude);
    }

    /**
     * Constructor with invalid negative parameters
     */
    @Test
    void testConstructorInvalidParametersNegativeLatitudeAndLongitude() {
        // Arrange
        double latitude = -91;
        double longitude = -181;
        String expectedMessage = "Invalid GPS Location";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GPS(latitude, longitude));
        String result = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Constructor with invalid positive parameters
     */
    @Test
    void testConstructorInvalidParametersPositiveLatitudeAndLongitude() {
        // Arrange
        double latitude = 91;
        double longitude = 181;
        String expectedMessage = "Invalid GPS Location";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new GPS(latitude, longitude));
        String result = exception.getMessage();
        // Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Constructor with valid parameters in the negative boundary
     */
    @Test
    void testConstructorValidParametersBoundaryNegativeLatitudeAndLongitude() {
        // Arrange
        double latitude = -90;
        double longitude = -180;
        // Act
        GPS gps = new GPS(latitude, longitude);
        double resultLatitude = gps.getLatitude();
        double resultLongitude = gps.getLongitude();
        // Assert
        assertEquals(latitude, resultLatitude);
        assertEquals(longitude, resultLongitude);
    }

    /**
     * Constructor with valid parameters in the positive boundary
     */
    @Test
    void testConstructorValidParametersBoundaryPositiveLatitudeAndLongitude() {
        // Arrange
        double latitude = 90;
        double longitude = 180;
        // Act
        GPS gps = new GPS(latitude, longitude);
        double resultLatitude = gps.getLatitude();
        double resultLongitude = gps.getLongitude();
        // Assert
        assertEquals(latitude, resultLatitude);
        assertEquals(longitude, resultLongitude);
    }
}