package factories.implement;

import domain.GPS;
import factories.GPSFactory;

/**
 * An implementation of the GPSFactory interface.
 * This class provides a concrete implementation of the createGPS method.
 */
public class GPSFactoryImp implements GPSFactory {

    /**
     * Default constructor for GPSFactoryImp.
     */
    public GPSFactoryImp() {
    }

    /**
     * Creates a GPS object with the specified latitude and longitude.
     *
     * @param latitude  the latitude of the GPS location
     * @param longitude the longitude of the GPS location
     * @return a GPS object with the specified latitude and longitude
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public GPS createGPS(double latitude, double longitude) throws IllegalArgumentException {
        return new GPS(latitude, longitude);
    }
}
