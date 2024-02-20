package DTO;

/**
 * This class is a Data Transfer Object (DTO) for Location.
 * It is used to transfer data about a device between processes or layers in an application.
 * The LocationDTO class includes fields for the address, zip code, latitude and longitude of a location
 */
public class LocationDTO {
    /**
     * Address of the house (Street, Number, Door)
     */
    private String address;
    /**
     * Zip code of the house
     */
    private String zipCode;
    /**
     * GPS location of the house (latitude)
     */
    private double latitude;
    /**
     * GPS location of the house (longitude)
     */
    private double longitude;

    /**
     * Constructor for the location DTO
     *
     * @param address   the address of the house
     * @param zipCode   the zip code of the house
     * @param latitude  the latitude of the house
     * @param longitude the longitude of the house
     */
    public LocationDTO(String address, String zipCode, double latitude, double longitude) {
        this.address = address;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
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
     * Getter for the latitude of the house
     *
     * @return the latitude of the house
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter for the longitude of the house
     *
     * @return the longitude of the house
     */
    public double getLongitude() {
        return longitude;
    }
}
