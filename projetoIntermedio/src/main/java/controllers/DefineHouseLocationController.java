package controllers;

import domain.House;
import domain.Location;
import dto.LocationDTO;
import mappers.MapperLocationDTO;

/**
 * ControllerUC01
 * "As an Administrator, I want to configure the location of the house."
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
     * Constructor of the controller US01
     * Initializes the house instance
     * Initializes the mapper location dto instance
     */
    public DefineHouseLocationController(House house, MapperLocationDTO mapperLocationDTO) {
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
}
