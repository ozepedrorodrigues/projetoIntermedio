package mappers;

import domain.Location;
import dto.LocationDTO;

public class MapperLocationDTO {

    public MapperLocationDTO() {
    }

    public LocationDTO locationToDto(Location location) {
        return new LocationDTO (
                location.getAddress(),
                location.getZipCode(),
                location.getGps().getLatitude(),
                location.getGps().getLongitude());
    }
}
