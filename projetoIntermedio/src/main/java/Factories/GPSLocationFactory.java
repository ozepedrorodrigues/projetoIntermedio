package Factories;

import Domain.GPSLocation;

/**
 * This interface is used to create GPSLocation objects
 */
public interface GPSLocationFactory {
    GPSLocation createGPSLocation(double latitude, double longitude);
}
