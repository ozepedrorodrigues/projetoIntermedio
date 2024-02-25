package Controllers;

import DTO.RoomDTO;
import Mappers.MapperToRoomDTO;
import Domain.House;
import Domain.Room;

import java.util.List;

/**
 * ControllerGetRoomList is a controller class responsible for fetching the list of rooms in a house.
 * It interacts with the House domain object to fetch the rooms.
 */
public class GetRoomListController {
    /**
     * House instance.
     */
    private House house;

    /**
     * Constructor for ControllerGetRoomList.
     * Initializes the house instance.
     */
    public GetRoomListController(House house) {
        this.house = house;
    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
/*    public List<RoomDTO> getRoomList() {
        List<Room> roomList = house.getRoomList();
        return new MapperToRoomDTO().getRoomList(roomList);
    }*/
}