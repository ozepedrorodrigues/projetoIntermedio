package controllers;

import domain.Device;
import domain.House;
import domain.Room;
import dto.DeviceDTO;
import dto.RoomDTO;
import mappers.MapperToDeviceDTO;
import mappers.MapperToRoomDTO;

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
     * MapperToDeviceDTO instance.
     */
    private MapperToDeviceDTO mapperToDeviceDTO;
    /**
     * GetRoomListController instance.
     */
    private GetRoomListController getRoomListController;

    /**
     * Constructor of the controller to add a device to a room.
     * Initializes the house instance
     */
    public AddDeviceToRoomController(House house, MapperToDeviceDTO mapperToDeviceDTO, GetRoomListController getRoomListController) throws IllegalArgumentException {
        if (!validHouse(house) || !validMapperToDeviceDTO(mapperToDeviceDTO) || !validGetRoomListController(getRoomListController)) {
            throw new IllegalArgumentException("Invalid parameter(s).");
        }
        this.house = house;
        this.mapperToDeviceDTO = mapperToDeviceDTO;
        this.getRoomListController = getRoomListController;
    }

    /**
     * method to verify if the house is valid.
     *
     * @param house the house to be validated
     * @return true if the house is valid, false otherwise
     */
    private boolean validHouse(House house) {
        return house != null;
    }

    /**
     * method to verify if the mapperToDeviceDTO is valid.
     *
     * @param mapperToDeviceDTO the mapperToDeviceDTO to be validated
     * @return true if the mapperToDeviceDTO is valid, false otherwise
     */
    private boolean validMapperToDeviceDTO(MapperToDeviceDTO mapperToDeviceDTO) {
        return mapperToDeviceDTO != null;
    }

    /**
     *
     * @param getRoomListController the getRoomListController to be validated
     * @return true if the getRoomListController is valid, false otherwise
     */
    private boolean validGetRoomListController(GetRoomListController getRoomListController) {
        return getRoomListController != null;
    }

    /**
     * Fetches a list of RoomDTO objects by using ControllerGetRoomList.
     *
     * @return a list of RoomDTO objects representing the rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return getRoomListController.getRoomList();
    }

    /**
     * method to add a device to a room.
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
        return mapperToDeviceDTO.deviceToDTO(device.getName(), device.getType(), room.getRoomName());
    }
}
