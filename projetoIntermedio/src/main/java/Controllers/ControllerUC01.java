package Controllers;

import DTO.LocationDTO;
import Domain.House;

/**
 * ControllerUC01
 * "As an Administrator, I want to configure the location of the house."
 */
public class ControllerUC01 {
    /**
     * The house instance
     */
    private final House house;

    /**
     * Constructor of the controller US01
     * Initializes the house instance
     */
    public ControllerUC01(House house) {
        this.house = house;
    }

    /**
     * Method that configures the location of the house
     *
     * @param locationDTO the location DTO
     * @return true if the location is valid, false otherwise
     */
    public boolean configureHouseLocation(LocationDTO locationDTO) {
        if (locationDTO == null) return false;
        //Mapper para devolver location
        return house.configLocation(locationDTO.getAddress(), locationDTO.getZipCode(),
                locationDTO.getLatitude(),
                locationDTO.getLongitude());
    }
}