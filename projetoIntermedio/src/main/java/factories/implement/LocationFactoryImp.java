package factories.implement;

import factories.GPSFactory;
import factories.LocationFactory;
import domain.Location;

public class LocationFactoryImp implements LocationFactory {

    private GPSFactory gpsFactory;

    public LocationFactoryImp(GPSFactory gpsFactory) {
        this.gpsFactory = gpsFactory;
    }

    public Location createLocation(String address, String zipCode, double latitude, double longitude) {
        ;
        try {
            return new Location(address, zipCode, latitude, longitude, gpsFactory);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
