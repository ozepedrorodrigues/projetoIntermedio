package Factories.implement;

import Domain.GPSLocation;
import Factories.GPSLocationFactory;
import Factories.LocationFactory;
import Domain.Location;

public class LocationFactoryImpl implements LocationFactory {

    public LocationFactoryImpl() {
    }

    public Location createLocation(String address, String zipCode, double latitude, double longitude, GPSLocationFactory gpsLocationFactory) {;
        return new Location(address, zipCode, latitude, longitude, gpsLocationFactory);
    }
}
