package factories.implement;

import factories.GPSFactory;
import factories.LocationFactory;
import domain.Location;

/**
 * This class is used to create Location objects
 */
public class LocationFactoryImp implements LocationFactory {

    /**
     * The GPSFactory used to create GPS objects
     */
    private GPSFactory gpsFactory;

    /**
     * Constructor
     *
     * @param gpsFactory the GPSFactory used to create GPS objects
     */
    public LocationFactoryImp(GPSFactory gpsFactory) {
        this.gpsFactory = gpsFactory;
    }

    /**
     * Creates a Location object with the specified address, zip code, latitude, and longitude.
     *
     * @param address   the address of the location
     * @param zipCode   the zip code of the location
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     * @return a Location object with the specified address, zip code, latitude, and longitude
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public Location createLocation(String address, String zipCode, double latitude, double longitude) throws IllegalArgumentException {
        return new Location(address, zipCode, latitude, longitude, gpsFactory);
    }
}
