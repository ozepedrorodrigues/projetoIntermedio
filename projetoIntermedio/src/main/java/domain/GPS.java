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


    public GPS(double latitude, double longitude) throws IllegalArgumentException {
        if (!validLatitude(latitude) || !validLongitude(longitude))
            throw new IllegalArgumentException("Invalid GPS Location");

        this.latitude = latitude;
        this.longitude = longitude;
    }

    private boolean validLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

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