package factories;

import domain.GPS;

/**
 * This interface is used to create GPS objects
 */
public interface GPSFactory {
    GPS createGPS(double latitude, double longitude);
}
