package Domain;

import Factories.GPSLocationFactory;

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
    private GPSLocation gpsLocation;

    /**
     * Constructor
     *
     * @param address the address of the house (Street, Number, Door)
     * @param zipCode the zip code of the house
     */
    public Location(String address, String zipCode, double latitude, double longitude, GPSLocationFactory gpsLocationFactory) {
        if (address.isBlank() || zipCode.isBlank())
            throw new IllegalArgumentException("Invalid Address or ZipCode");
        this.address = address;
        this.zipCode = zipCode;
        this.gpsLocation = gpsLocationFactory.createGPSLocation(latitude,longitude);}

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
    public GPSLocation getGpsLocation() {
        return gpsLocation;
    }
}