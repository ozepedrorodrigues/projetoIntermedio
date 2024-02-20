package Controllers;

import DTOs.RoomDTO;
import org.domain.House;

/**
 * ControllerUS02 is responsible for adding a new room to a house.
 * It validates the input parameters and creates a new Room object.
 * The Room object is then added to the House object.
 */
public class ControllerUC02 {
    /**
     * The house instance to which the room is to be added.
     */
    private House house;

    /**
     * Constructor for ControllerUC02.
     * Initializes the house instance with the singleton instance of House.
     */
    public ControllerUC02() {

        this.house = House.getInstance();
    }

    /**
     * Adds a new room to a house.
     *
     * @param roomDTO the data transfer object containing the details of the room to be added
     * @return true if the room is successfully added to the house, false otherwise
     */
    public boolean addNewRoomToHouse(RoomDTO roomDTO) {
        if (roomDTO == null || roomDTO.getName() == null) {
            return false;
        }
        return house.addRoom(roomDTO.getName(), roomDTO.getFloor(), roomDTO.getWidth(), roomDTO.getLength(), roomDTO.getHeight());
    }
}
