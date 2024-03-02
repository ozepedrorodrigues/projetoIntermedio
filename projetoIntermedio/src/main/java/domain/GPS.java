package domain;

/**
 * Represents a GPS location with latitude and longitude.
 */
public class GPS {
    /**
     * Latitude of the GPS location of the house
     */
    private double latitude;

    /**
     * Longitude of the GPS location of the house
     */
    private double longitude;

    /**
     * Constructor for GPS class
     * Validates the latitude and longitude of the GPS location
     *
     * @param latitude  The latitude of the GPS location of the house
     * @param longitude The longitude of the GPS location of the house
     * @throws IllegalArgumentException if the latitude or longitude is invalid
     */
    public GPS(double latitude, double longitude) throws IllegalArgumentException {
        if (!validLatitude(latitude) || !validLongitude(longitude))
            throw new IllegalArgumentException();

        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Validates the longitude of the GPS location
     * Longitude must be between -180 and 180
     *
     * @param longitude The longitude of the GPS location
     * @return true if the longitude is valid, false otherwise
     */
    private boolean validLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    /**
     * Validates the latitude of the GPS location
     * Latitude must be between -90 and 90
     *
     * @param latitude The latitude of the GPS location
     * @return true if the latitude is valid, false otherwise
     */
    private boolean validLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    /**
     * Getter for the latitude of the GPS location of the house
     *
     * @return the latitude of the GPS location of the house
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter for the longitude of the GPS location of the house
     *
     * @return the longitude of the GPS location of the house
     */
    public double getLongitude() {
        return longitude;
    }
}