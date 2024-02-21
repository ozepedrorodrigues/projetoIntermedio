package Factories;

import Domain.GPSLocation;

public interface GPSLocationFactory {
    GPSLocation createGPSLocation(double latitude, double longitude);
}
