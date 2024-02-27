package controllers;

import domain.House;
import domain.Location;
import dto.LocationDTO;
import mappers.MapperLocationDTO;

/**
 * UC01
 * "As an Administrator, I want to configure the location of the house."
 * <p>
 * This class represents the controller to define the house location
 */
public class DefineHouseLocationController {

    /**
     * The house instance
     */
    private final House house;

    /**
     * The mapper location dto instance
     */
    private final MapperLocationDTO mapperLocationDTO;


    /**
     * Constructor
     * Initializes the house instance
     * Initializes the mapper location dto instance
     */
    public DefineHouseLocationController(House house, MapperLocationDTO mapperLocationDTO) {
        if (!validParameters(house, mapperLocationDTO)) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        this.house = house;
        this.mapperLocationDTO = mapperLocationDTO;
    }

    /**
     * Method that configures the location of the house
     *
     * @param locationDTO the location dto
     * @return a location dto if the location is valid, null otherwise
     */
    public LocationDTO defineHouseLocation(LocationDTO locationDTO) {
        Location location = house.defineLocation(
                locationDTO.getAddress(),
                locationDTO.getZipCode(),
                locationDTO.getLatitude(),
                locationDTO.getLongitude());

        if (location == null) {
            return null;
        } else {
            return mapperLocationDTO.locationToDto(location);
        }
    }

    /**
     * Checks if the given constructor parameters are not null.
     */
    private boolean validParameters(House house, MapperLocationDTO mapperLocationDTO) {
        return house != null && mapperLocationDTO != null;
    }
}
