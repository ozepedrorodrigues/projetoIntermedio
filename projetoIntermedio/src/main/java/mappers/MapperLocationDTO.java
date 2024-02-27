package mappers;

import domain.Location;
import dto.LocationDTO;

/**
 * This class is a Mapper for LocationDTO.
 * It is used to map a location to a LocationDTO object.
 */
public class MapperLocationDTO {

    /**
     * Constructs a new MapperLocationDTO.
     * The constructor is empty because it is a Mapper.
     */
    public MapperLocationDTO() {
    }

    /**
     * Converts a location to a LocationDTO object.
     *
     * @param location the location to be converted.
     * @return the LocationDTO.
     */
    public LocationDTO locationToDto(Location location) {
        return new LocationDTO (
                location.getAddress(),
                location.getZipCode(),
                location.getGps().getLatitude(),
                location.getGps().getLongitude());
    }
}
