package controllers;

import dto.DeviceDTO;
import mappers.MapperToDeviceDTO;
import domain.Device;
import domain.House;
import domain.Room;

import java.util.List;

/**
 * ControllerGetDeviceList is a controller class responsible for fetching the list of devices in a room.
 * It interacts with the House and Room domain objects to fetch the devices.
 */
public class GetDeviceListController {
    /**
     * The house object.
     */
    private House house;
    /**
     * Constructor for ControllerGetDeviceList.
     */
    public GetDeviceListController(House house) {
        this.house = house;
    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices in the room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        Room room = house.getRoomByName(roomName);
        if (room == null)
            throw new IllegalArgumentException("Room does not exist.");
        List<Device> devices = room.getDeviceList();
        return new MapperToDeviceDTO().getDevices(devices, roomName);
    }
}