package Factories.implement;

import Factories.GPSLocationFactory;
import Factories.LocationFactory;
import Domain.Location;

public class LocationFactoryImp implements LocationFactory {

    public LocationFactoryImp() {
    }

    public Location createLocation(String address, String zipCode, double latitude, double longitude) {;
        GPSLocationFactory gpsLocationFactory = new GPSLocationFactoryImp();
        try{return new Location(address, zipCode, latitude, longitude,gpsLocationFactory);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
    }
}
