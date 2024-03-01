package controllers;

import domain.Device;
import domain.House;
import domain.Room;
import dto.DeviceDTO;
import mappers.DeviceMapper;

import java.util.Collections;
import java.util.List;

/**
 * ControllerGetDeviceList is a controller class responsible for fetching the list of devices in a room.
 * It interacts with the House and Room domain objects to fetch the devices.
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
     * Constructor for ControllerGetDeviceList.
     */
    public GetDeviceListController(House ourHouse, DeviceMapper deviceMapper) {
        this.matryoshka = ourHouse;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices in the room, or an empty list if the room does not exist.
     */
    public List<DeviceDTO> getDevicesInRoom(String roomName) {
        Room room = matryoshka.getRoomByName(roomName);
        if (room == null) {
            return Collections.emptyList();
        }
        List<Device> devices = room.getDeviceList();
        return deviceMapper.devicesToDTO(devices, roomName);
    }
}