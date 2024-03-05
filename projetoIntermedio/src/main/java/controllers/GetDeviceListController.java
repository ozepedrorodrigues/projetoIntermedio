package controllers;

import domain.Device;
import domain.House;
import domain.Room;
import dto.DeviceDTO;
import mappers.DeviceMapper;

import java.util.List;

/**
 * The GetDeviceListController class serves as a controller for retrieving a list of devices in a specific room.
 * <p>
 * It is designed to interact with the House model to obtain information about rooms and devices.
 * The controller utilizes a deviceMapper to convert the retrieved list of devices to a list of DeviceDTOs.
 * </p>
 */
public class GetDeviceListController {

    /**
     * The house object.
     */
    private House house;

    /**
     * The mapper to convert devices to DTOs.
     */
    private DeviceMapper deviceMapper;

    /**
     * Constructs a new instance of the GetDeviceListController.
     *
     * @param ourHouse      The House object representing the overall structure.
     * @param deviceMapper  The mapper used to convert devices to DTOs.
     * @throws IllegalArgumentException if the house or deviceMapper are invalid.
     */
    public GetDeviceListController(House ourHouse, DeviceMapper deviceMapper) throws IllegalArgumentException {
        if (!validHouse(ourHouse) || !validMapper(deviceMapper)) {
            throw new IllegalArgumentException();
        }
        this.house = ourHouse;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Checks if the house passed to the constructor is valid.
     *
     * @param ourHouse the house to be checked
     * @return true if the house is valid, false otherwise
     */
    private boolean validHouse(House ourHouse) {
        return ourHouse != null;
    }

    /**
     * Checks if the DeviceMapper passed to the constructor is valid.
     *
     * @param deviceMapper the deviceMapper to be checked
     * @return true if the DeviceMapper is valid, false otherwise
     */
    private boolean validMapper(DeviceMapper deviceMapper) {
        return deviceMapper != null;
    }

    /**
     * Retrieves the list of devices (represented by DeviceDTO objects) in a specified room.
     * If the specified room does not exist in the house, a null object is returned.
     *
     * @param roomName The name of the room for which to retrieve the list of devices.
     * @return List<DeviceDTO> The list of devices (represented by DeviceDTO objects) in the room, or a null object if the room does not exist.
     */
    public List<DeviceDTO> getDevicesInRoom(String roomName) {
        Room room = house.getRoomByName(roomName);
        if (!validRoom(roomName)) {
            return null;
        }
        List<Device> devices = room.getDevicesInRoom();
        return deviceMapper.devicesToDTO(devices, roomName);
    }

    /**
     * Validates if the room is valid.
     *
     * @param roomName the name of the Room to be validated
     * @return true if the Room is valid, false otherwise
     */
    private boolean validRoom(String roomName) {
        return roomName != null && house.getRoomByName(roomName) != null;
    }
}