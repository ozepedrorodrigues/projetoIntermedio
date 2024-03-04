package factories;

import domain.GPS;

/**
 * This interface is used to create GPS objects
 */
public interface GPSFactory {

    /**
     * Creates a GPS object with the specified latitude and longitude.
     *
     * @param latitude  the latitude of the GPS location
     * @param longitude the longitude of the GPS location
     * @return a GPS object with the specified latitude and longitude
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    GPS createGPS(double latitude, double longitude);
}
