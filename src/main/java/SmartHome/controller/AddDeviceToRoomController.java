package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.DeviceMapper;

import java.util.List;

/**
 * AddDeviceToRoomController is a controller class that provides functionality to add a device to a room.
 * The AddDeviceToRoomController class includes methods to get a list of rooms, and add a device to a room.
 */
public class AddDeviceToRoomController {
    /**
     * The House instance.
     */
    private final House house;
    /**
     * The DeviceMapper instance.
     */
    private final DeviceMapper deviceMapper;
    /**
     * The GetRoomListController instance.
     */
    private final GetRoomListController getRoomListController;

    /**
     * Constructor of the controller to add a device to a room.
     *
     * @param house the house to be initialized
     * @param deviceMapper the deviceMapper to be initialized
     * @param getRoomListController the getRoomListController to be initialized
     * @throws IllegalArgumentException if the house, deviceMapper or getRoomListController are not valid
     */
    public AddDeviceToRoomController(House house, DeviceMapper deviceMapper, GetRoomListController getRoomListController) throws IllegalArgumentException {
        if (!validHouse(house) || !validMapperToDeviceDTO(deviceMapper) || !validGetRoomListController(getRoomListController)) {
            throw new IllegalArgumentException();
        }
        this.house = house;
        this.deviceMapper = deviceMapper;
        this.getRoomListController = getRoomListController;
    }

    /**
     * Fetches a list of RoomDTO objects by using ControllerGetRoomList.
     *
     * @return a list of RoomDTO objects representing the rooms in the house.
     */
    public List<RoomDTO> getRooms() {
        return getRoomListController.getRooms();
    }

    /**
     * Adds a device to a room.
     *
     * @param deviceDTO the device to be added to the room
     * @return the DeviceDTO object representing the device added to the room, or null if the device was not added
     */
    public DeviceDTO addDeviceToRoom(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            return null;
        }
        Room room = house.getRoomByName(deviceDTO.getRoomName());
        Device device = room.addNewDevice(deviceDTO.getName(), deviceDTO.getType());
        if (device == null) {
            return null;
        }
        return deviceMapper.deviceToDTO(device, room.getRoomName());
    }


    /**
     * Validates if the house is valid.
     *
     * @param house the house to be validated
     * @return true if the house is valid, false otherwise
     */
    private boolean validHouse(House house) {
        return house != null;
    }

    /**
     * Validates if the deviceMapper is valid.
     *
     * @param deviceMapper the deviceMapper to be validated
     * @return true if the deviceMapper is valid, false otherwise
     */
    private boolean validMapperToDeviceDTO(DeviceMapper deviceMapper) {
        return deviceMapper != null;
    }

    /**
     * Validates if the getRoomListController is valid.
     *
     * @param getRoomListController the getRoomListController to be validated
     * @return true if the getRoomListController is valid, false otherwise
     */
    private boolean validGetRoomListController(GetRoomListController getRoomListController) {
        return getRoomListController != null;
    }
}

