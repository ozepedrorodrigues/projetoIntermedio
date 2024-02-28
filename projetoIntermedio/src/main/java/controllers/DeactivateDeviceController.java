package controllers;

import domain.Device;
import domain.House;
import domain.Room;
import dto.DeviceDTO;
import dto.RoomDTO;
import mappers.MapperToDeviceDTO;

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
     * MapperToDeviceDTO instance.
     */
    private MapperToDeviceDTO mapperToDeviceDTO;

    /**
     * Constructor for DeactivateDeviceController.
     * Initializes the house, getRoomListController, getDeviceListController and mapperToDeviceDTO instance.
     *
     * @param house                   House instance.
     * @param getRoomListController   GetRoomListController instance.
     * @param getDeviceListController GetDeviceListController instance.
     * @param mapperToDeviceDTO       MapperToDeviceDTO instance.
     * @throws InstantiationException if house is null.
     */
    public DeactivateDeviceController(House house, GetRoomListController getRoomListController, GetDeviceListController getDeviceListController, MapperToDeviceDTO mapperToDeviceDTO) throws InstantiationException {
        if (house == null)
            throw new InstantiationException("House cannot be null.");
        this.house = house;
        this.getRoomListController = getRoomListController;
        this.getDeviceListController = getDeviceListController;
        this.mapperToDeviceDTO = mapperToDeviceDTO;
    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return getRoomListController.getRoomList();
    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices in the room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        return getDeviceListController.getDevicesInRoom(roomName);
    }

    /**
     * Deactivates the device.
     * If the device is already deactivated, returns false.
     *
     * @param deviceDTO the device to be deactivated.
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
}