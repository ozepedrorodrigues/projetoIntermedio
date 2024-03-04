package factories.implement;

import factories.GPSFactory;
import factories.LocationFactory;
import domain.Location;

public class LocationFactoryImp implements LocationFactory {

    private GPSFactory gpsFactory;

    public LocationFactoryImp(GPSFactory gpsFactory) {
        this.gpsFactory = gpsFactory;
    }

    public Location createLocation(String address, String zipCode, double latitude, double longitude) throws IllegalArgumentException {
        return new Location(address, zipCode, latitude, longitude, gpsFactory);
    }
}
