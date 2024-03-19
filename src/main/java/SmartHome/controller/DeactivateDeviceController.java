package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.house.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomDTO;

import java.util.List;

/**
 * DeactivateDeviceController is a controller class responsible for deactivating a device in a room.
 */
public class DeactivateDeviceController {
    /**
     * House instance.
     */
    private House house;
    /**
     * GetRoomListController instance.
     */
    private GetRoomListController getRoomListController;
    /**
     * GetDeviceListController instance.
     */
    private GetDeviceListController getDeviceListController;
    /**
     * DeviceMapper instance.
     */
    private DeviceMapper deviceMapper;

    /**
     * Constructor for DeactivateDeviceController.
     * Initializes the house, getRoomListController, getDeviceListController and deviceMapper instance.
     *
     * @param house                   House instance.
     * @param getRoomListController   GetRoomListController instance.
     * @param getDeviceListController GetDeviceListController instance.
     * @param deviceMapper            DeviceMapper instance.
     * @throws IllegalArgumentException if at least one of the parameters is not valid.
     */
    public DeactivateDeviceController(House house, GetRoomListController getRoomListController, GetDeviceListController getDeviceListController, DeviceMapper deviceMapper) throws IllegalArgumentException {
        if (!validParameters(house, getRoomListController, getDeviceListController, deviceMapper))
            throw new IllegalArgumentException();
        this.house = house;
        this.getRoomListController = getRoomListController;
        this.getDeviceListController = getDeviceListController;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Gets the list of rooms (as RoomDTO objects) in the house.
     *
     * @return the list of rooms (as RoomDTO) in the house.
     */
    public List<RoomDTO> getRooms() {
        return getRoomListController.getRooms();
    }

    /**
     * Gets the list of devices (as DeviceDTO's) in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices (as DeviceDTO's) in the room.
     */
    public List<DeviceDTO> getDevices(String roomName) {
        return getDeviceListController.getDevicesInRoom(roomName);
    }

    /**
     * Deactivates the device.
     *
     * @param deviceDTO the DTO representation of the device to be deactivated.
     * @return true if the device was deactivated successfully, false otherwise.
     */

    public boolean deactivateDevice(DeviceDTO deviceDTO) {
        if (deviceDTO == null) return false;
        String roomName = deviceDTO.getRoomName();
        Room room = house.getRoomByName(roomName);
        String deviceName = deviceDTO.getName();
        Device device = room.getDeviceByName(deviceName);

        return device.deactivate();
    }

    /**
     * Checks if the constructor parameters are valid
     *
     * @param house                   the house to be checked
     * @param getRoomListController   the getRoomListController to be checked
     * @param getDeviceListController the getDeviceListController to be checked
     * @param deviceMapper            the deviceMapper to be checked
     * @return true if the parameters are valid, false otherwise
     */
    private boolean validParameters(House house, GetRoomListController getRoomListController, GetDeviceListController getDeviceListController, DeviceMapper deviceMapper) {
        return house != null && getRoomListController != null && getDeviceListController != null && deviceMapper != null;
    }

}
