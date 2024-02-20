package Controllers;

import DTO.DeviceDTO;
import DTO.RoomDTO;
import Domain.House;

import java.util.List;

/**
 * ControllerUC06 is a controller class for User Story 06.
 * It provides functionality to get a list of all devices in a room.
 * The ControllerUC06 class includes methods to get a list of rooms, choose a room, and get a list of devices in the chosen room.
 */
public class GetDeviceListFromSpecificRoomController {
    /**
     * The House instance from which the rooms and devices are retrieved.
     */
    private House myHouse;

    /**
     * Constructor for ControllerUC06.
     * Initializes the house instance.
     */
    public GetDeviceListFromSpecificRoomController(House house) {
        this.myHouse = house;}

    /**
     * Gets the list of rooms in the house.
     * @return the list of rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return new GetRoomListController(myHouse).getRoomList();
    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices in the room.
     * @throws IllegalArgumentException if the room name is null.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        try {
            return new GetDeviceListController(myHouse).getDeviceList(roomName);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());}

    }
}