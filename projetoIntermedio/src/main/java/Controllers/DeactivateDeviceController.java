package Controllers;

import DTO.DeviceDTO;
import DTO.RoomDTO;
import Domain.Device;
import Domain.House;
import Domain.Room;

import java.util.List;

/**
 * This class is a controller for UC08.puml: Deactivate Device.
 * As a Power User [or Administrator], I want to deactivate a device, so that it is no
 * longer used. Nevertheless, it should be possible to access its configuration and
 * activity log.
 */
public class DeactivateDeviceController {
    /**
     * The house instance.
     */
    private final House house;

    /**
     * Constructs a new ControllerUC08 with the given house.
     */
    public DeactivateDeviceController(House house) {
        this.house = house;}

    /**
     * Returns a list of all rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return new GetRoomListController(house).getRoomList();}

    /**
     * Returns a list of all devices in the given room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        return new GetDeviceListController(house).getDeviceList(roomName);}

    /**
     * Deactivates the given device.
     * Returns true if the device was deactivated successfully.
     */
    public boolean deactivateDevice(DeviceDTO deviceDTO) {
        String roomName = deviceDTO.getRoomName();
        Room room = house.getRoomByName(roomName);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");}
        String deviceName = deviceDTO.getName();
        Device device = room.getDeviceByName(deviceName);
        if (device == null) {
            throw new IllegalArgumentException("Device not found");}
        return device.deactivate();}
}