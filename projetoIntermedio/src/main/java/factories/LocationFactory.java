package factories;

import domain.Location;

public interface LocationFactory {
    Location createLocation(String address, String zipCode, double latitude, double longitude);
}
