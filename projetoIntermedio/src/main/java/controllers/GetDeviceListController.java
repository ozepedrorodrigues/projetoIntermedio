package controllers;

import domain.Device;
import domain.House;
import domain.Room;
import dto.DeviceDTO;
import mappers.MapperToDeviceDTO;

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
    private MapperToDeviceDTO mapperToDeviceDTO;
    /**
     * Constructor for ControllerGetDeviceList.
     */
    public GetDeviceListController(House ourHouse, MapperToDeviceDTO mapperToDeviceDTO) {
        this.matryoshka = ourHouse;
        this.mapperToDeviceDTO = mapperToDeviceDTO;
    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices in the room, or an empty list if the room does not exist.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        Room room = matryoshka.getRoomByName(roomName);
        if (room == null) {
            return Collections.emptyList();
        }
        List<Device> devices = room.getDeviceList();
        return mapperToDeviceDTO.devicesToDTO(devices, roomName);
    }
}