package Factories.implement;

import Domain.GPSLocation;
import Factories.GPSLocationFactory;

public class GPSLocationFactoryImp implements GPSLocationFactory {
    public GPSLocationFactoryImp() {
    }

    public GPSLocation createGPSLocation(double latitude, double longitude) {
        return new GPSLocation(latitude, longitude);
    }
}
