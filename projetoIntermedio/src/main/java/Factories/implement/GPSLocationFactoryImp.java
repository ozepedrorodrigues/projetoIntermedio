package Factories.implement;

import Domain.GPSLocation;
import Factories.GPSLocationFactory;

public class GPSLocationFactoryImp implements GPSLocationFactory {
    public GPSLocationFactoryImp() {
    }

    public GPSLocation createGPSLocation(double latitude, double longitude) {
        try{return new GPSLocation(latitude, longitude);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
    }
}
