package Domain;

/**
 * Represents a GPS location with latitude and longitude.
 */
public class GPSLocation {
    /**
     * Latitude of the GPS location of the house
     */
    private double latitude;

    /**
     * Longitude of the GPS location of the house
     */
    private double longitude;

    /**
     * Constructor
     *
     * @param latitude  the latitude of the GPS location of the house
     * @param longitude the longitude of the GPS location of the house
     * @throws IllegalArgumentException if the latitude or longitude is invalid
     */
    public GPSLocation(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Invalid GPS Location");
        this.latitude = latitude;
        this.longitude = longitude;
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