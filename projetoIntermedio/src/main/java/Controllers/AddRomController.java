package Controllers;

import DTO.RoomDTO;
import Domain.House;

/**
 * ControllerUS02 is responsible for adding a new room to a house.
 * It validates the input parameters and creates a new Room object.
 * The Room object is then added to the House object.
 */
public class AddRomController {
    /**
     * The house instance to which the room is to be added.
     */
    private House house;

    /**
     * Constructor for ControllerUC02.
     */
    public AddRomController(House house) {
        this.house = house;
    }

    /**
     * Adds a new room to a house.
     *
     * @param roomDTO the data transfer object containing the details of the room to be added
     * @return true if the room is successfully added to the house, false otherwise
     */
    public boolean addNewRoomToHouse(RoomDTO roomDTO) {
        return house.addRoom(roomDTO.getName(), roomDTO.getFloor(), roomDTO.getWidth(), roomDTO.getLength(), roomDTO.getHeight());
    }
}
