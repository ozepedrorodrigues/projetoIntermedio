package factories.implement;

import domain.GPS;
import factories.GPSFactory;

public class GPSFactoryImp implements GPSFactory {
    public GPSFactoryImp() {
    }

    public GPS createGPS(double latitude, double longitude) {
        try{return new GPS(latitude, longitude);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
    }
}
