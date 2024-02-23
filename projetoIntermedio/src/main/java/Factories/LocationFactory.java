package Factories;

import Domain.GPSLocation;
import Domain.Location;

public interface LocationFactory {
    Location createLocation(String address, String zipCode, double latitude, double longitude);
}
