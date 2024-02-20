package Controllers;

import DTOs.DeviceDTO;
import DTOs.RoomDTO;
import org.domain.Device;
import org.domain.House;
import org.domain.Room;

import java.util.List;

/**
 * This class is a controller for UC08: Deactivate Device.
 * As a Power User [or Administrator], I want to deactivate a device, so that it is no
 * longer used. Nevertheless, it should be possible to access its configuration and
 * activity log.
 */
public class ControllerUC08 {
    /**
     * The house instance.
     */
    private final House house;

    /**
     * Constructs a new ControllerUC08 with the given house.
     */
    public ControllerUC08() {
        this.house = House.getInstance();
    }

    /**
     * Returns a list of all rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        GetRoomListController getRoomListController = new GetRoomListController();
        return getRoomListController.getRoomList();
    }

    /**
     * Returns a list of all devices in the given room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        GetDeviceListController getDeviceListController = new GetDeviceListController();
        return getDeviceListController.getDeviceList(roomName);
    }

    /**
     * Deactivates the given device.
     * Returns true if the device was deactivated successfully.
     */
    public boolean deactivateDevice(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException("DeviceDTO cannot be null");
        }
        String roomName = deviceDTO.getLocation();
        Room room = house.getRoomByName(roomName);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        String deviceName = deviceDTO.getName();
        Device device = room.getDeviceByName(deviceName);
        if (device == null) {
            throw new IllegalArgumentException("Device not found");
        }
        return device.deactivate();
    }
}