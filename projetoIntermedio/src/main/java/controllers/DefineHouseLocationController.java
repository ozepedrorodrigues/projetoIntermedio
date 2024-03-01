package controllers;

import domain.House;
import domain.Location;
import dto.LocationDTO;
import mappers.LocationMapper;

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
    private final LocationMapper locationMapper;


    /**
     * Constructor
     * Initializes the house instance
     * Initializes the mapper location dto instance
     */
    public DefineHouseLocationController(House house, LocationMapper locationMapper) {
        if (!validParameters(house, locationMapper)) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        this.house = house;
        this.locationMapper = locationMapper;
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
            return locationMapper.locationToDto(location);
        }
    }

    /**
     * Checks if the given constructor parameters are not null.
     */
    private boolean validParameters(House house, LocationMapper locationMapper) {
        return house != null && locationMapper != null;
    }
}
