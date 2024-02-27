package domain;

import factories.GPSFactory;

/**
 * Represents the location of a house.
 * The location includes the address, zip code, and GPS location.
 */
public class Location {

    /**
     * the address of the house (Street, Number, Door)
     */
    private String address;

    /**
     * the zip code of the house
     */
    private String zipCode;

    /**
     * GPS location of the house
     */
    private GPS gps;

    /**
     * GPSFactory of the location
     */
    private GPSFactory gpsFactory;

    /**
     * Constructor
     *
     * @param address            the address of the house (Street, Number, Door)
     * @param zipCode            the zip code of the house
     * @param latitude           the latitude of the GPS location of the house
     * @param longitude          the longitude of the GPS location of the house
     * @param gpsFactory the GPSFactory of the location
     */
    public Location(String address, String zipCode, double latitude, double longitude, GPSFactory gpsFactory) throws IllegalArgumentException {
        if (!validAddress(address) || !validZipCode(zipCode) || !validGpsFactory(gpsFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.address = address;
        this.zipCode = zipCode;
        this.gpsFactory = gpsFactory;
        this.gps = this.gpsFactory.createGPS(latitude, longitude);
    }

    /**
     * Getter for the address of the house
     *
     * @return the address of the house
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for the zip code of the house
     *
     * @return the zip code of the house
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Getter for the GPS location of the house
     *
     * @return the GPS location of the house
     */
    public GPS getGps() {
        return gps;
    }

    /**
     * Getter for the GPSFactory of the location
     *
     * @return the GPS factory of the location
     */
    public GPSFactory getGpsLocationFactory() {
        return gpsFactory;
    }

    /**
     * Checks if the given address is non-null and not blank.
     */
    private boolean validAddress(String address) {
        return address != null && !address.isBlank();
    }

    /**
     * Checks if the given zip code is non-null and not blank.
     */
    private boolean validZipCode(String zipCode) {
        return zipCode != null && !zipCode.isBlank();
    }

    /**
     * Checks if the given GPSFactory is non-null.
     */
    private boolean validGpsFactory(GPSFactory gpsFactory) {
        return gpsFactory != null;
    }
}