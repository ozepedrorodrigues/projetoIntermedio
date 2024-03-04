package factories.implement;

import domain.GPS;
import factories.GPSFactory;

public class GPSFactoryImp implements GPSFactory {
    public GPSFactoryImp() {
    }

    public GPS createGPS(double latitude, double longitude) throws IllegalArgumentException {
        return new GPS(latitude, longitude);
    }
}
