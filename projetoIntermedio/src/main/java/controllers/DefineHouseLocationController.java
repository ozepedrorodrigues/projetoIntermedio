package controllers;

import domain.House;
import domain.Location;
import dto.LocationDTO;
import mappers.LocationMapper;

/**
 * The DefineHouseLocationController class serves as a controller for defining the location of a house.
 * It is designed to interact with the House model to define the location of the house.
 * The controller utilizes a locationMapper to convert the location to a LocationDTO.
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
     * Constructs a new DefineHouseLocationController with the specified house and locationMapper.
     *
     * @param house          the house to which the location is to be defined
     * @param locationMapper the locationMapper to be used
     * @throws IllegalArgumentException if an error occurs during the instantiation of the objects.
     */
    public DefineHouseLocationController(House house, LocationMapper locationMapper) {
        if (!validParameters(house, locationMapper)) {
            throw new IllegalArgumentException();
        }

        this.house = house;
        this.locationMapper = locationMapper;
    }

    /**
     * Defines the location of the house with the specified address, zip code, latitude, and longitude.
     *
     * @param locationDTO the location to be defined for the house
     * @return the defined location as a LocationDTO, null if the location could not be defined
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
     * Checks if the arguments passed to the constructor are valid.
     *
     * @param house          the house to be checked
     * @param locationMapper the locationMapper to be checked
     * @return true if the arguments are valid, false otherwise
     */
    private boolean validParameters(House house, LocationMapper locationMapper) {
        return house != null && locationMapper != null;
    }
}
