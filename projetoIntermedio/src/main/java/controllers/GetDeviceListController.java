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
    private House matryoshka;

    /**
     * The mapper to convert devices to DTOs.
     */
    private DeviceMapper deviceMapper;

    /**
     * Constructs a new instance of the GetDeviceListController.
     *
     * @param ourHouse      The House object representing the overall structure.
     * @param deviceMapper  The mapper used to convert devices to DTOs.
     */
    public GetDeviceListController(House ourHouse, DeviceMapper deviceMapper) throws IllegalArgumentException {
        if (!validHouse(ourHouse) || !validMapper(deviceMapper)) {
            throw new IllegalArgumentException();
        }
        this.matryoshka = ourHouse;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Checks if the arguments passed to the constructor are valid.
     *
     * @param ourHouse the house to be checked
     * @return true if the arguments are valid, false otherwise
     */
    private boolean validHouse(House ourHouse) {
        return ourHouse != null;
    }

    /**
     * Checks if the arguments passed to the constructor are valid.
     *
     * @param deviceMapper the deviceMapper to be checked
     * @return true if the arguments are valid, false otherwise
     */
    private boolean validMapper(DeviceMapper deviceMapper) {
        return deviceMapper != null;
    }

    /**
     * Retrieves the list of devices in a specified room.
     * If the specified room does not exist in the house, an empty list is returned.
     *
     * @param roomName The name of the room for which to retrieve the list of devices.
     * @return List<DeviceDTO> The list of devices in the room, or an empty list if the room does not exist.
     */
    public List<DeviceDTO> getDevicesInRoom(String roomName) {
        Room room = matryoshka.getRoomByName(roomName);
        if (!validRoom(roomName)) {
            return null;
        }
        List<Device> devices = room.getDevicesInRoom();
        return deviceMapper.devicesToDTO(devices, roomName);
    }

    /**
     * Validates if the room is valid.
     *
     * @param roomName the room to be validated
     * @return true if the room is valid, false otherwise
     */
    private boolean validRoom(String roomName) {
        return roomName != null && matryoshka.getRoomByName(roomName) != null;
    }
}