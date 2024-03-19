package SmartHome.domain.house;


/**
 * This interface is used to create Location objects
 */
public interface LocationFactory {

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
    Location createLocation(String address, String zipCode, double latitude, double longitude);
}
