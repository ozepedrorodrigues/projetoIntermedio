package Controllers;

import DTOs.RoomDTO;
import org.domain.House;

import java.util.List;

/**
 * ControllerUC03 is a controller class responsible for handling operations related to UC03 use case.
 * It interacts with the House domain object and uses ControllerGetRoomList to fetch a list of rooms.
 */
public class ControllerUC03 {
    /**
     * Constructor for ControllerUC03. Initializes the house instance.
     */
    private House house;

    /**
     * Constructor for ControllerUC03. Initializes the house instance.
     */
    public ControllerUC03() {
        this.house = House.getInstance();
    }

    /**
     * Fetches a list of RoomDTO objects by using ControllerGetRoomList.
     *
     * @return a list of RoomDTO objects.
     */
    public List<RoomDTO> getRoomList() {
        return new GetRoomListController().getRoomList();
    }
}