package Controllers;

import DTO.DeviceDTO;
import DTO.RoomDTO;
import Domain.House;
import Domain.Room;

import java.util.List;

/**
 * Represents the controller of the US05:
 * "As a Power User [or Administrator], I want to add a new Device to a room from the
 * list of available sensor types, in order to configure it."
 */
public class AddDeviceToRoomController {
    /**
     * The name of the house.
     */
    private House house;

    /**
     * Constructor of the controller UC05
     * Initializes the house instance
     */
    public AddDeviceToRoomController(House house ) {
        this.house = house;
    }

    /**
     * Fetches a list of RoomDTO objects by using ControllerGetRoomList.
     *
     * @return a list of RoomDTO objects representing the rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return new GetRoomListController(house).getRoomList();
    }

    /**
     * method to add a device to a room.
     *
     * @return true if the device was added to the room, false otherwise
     */
    public boolean addDeviceToRoom(DeviceDTO deviceDTO) {
        Room room = house.getRoomByName(deviceDTO.getRoomName());
        if (room == null) return false;
        return room.createDevice(deviceDTO.getName(), deviceDTO.getType());}
}