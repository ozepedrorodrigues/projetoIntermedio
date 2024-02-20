package Mappers;

import DTOs.DeviceDTO;
import org.domain.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Mapper for DeviceDTO.
 * It is used to map a list of devices to a list of DeviceDTO objects.
 */
public class MapperToDeviceDTO {
    /**
     * Constructs a new MapperToDeviceDTO.
     */
    public MapperToDeviceDTO() {
        // Constructor for MapperToDeviceDTO
    }

    /**
     * Converts a list of devices to a list of DeviceDTO objects.
     *
     * @param roomName the room where the devices are located.
     * @return the list of DeviceDTOs.
     */
    public List<DeviceDTO> getDeviceList(List<Device> devices, String roomName) {
        List<DeviceDTO> devicesDTO = new ArrayList<>(); // Create a new list each time
        for (int i = 0; i < devices.size(); i++) {
            DeviceDTO myDeviceDTO = deviceToDTO(devices.get(i).getName(), devices.get(i).getType(), roomName);
            devicesDTO.add(myDeviceDTO);
        }
        return devicesDTO;
    }

    /**
     * Converts a device to a DeviceDTO object.
     *
     * @param deviceName the name of the device.
     * @param deviceType the type of the device.
     * @param roomName   the room where the device is located.
     * @return the DeviceDTO.
     */
    private DeviceDTO deviceToDTO(String deviceName, String deviceType, String roomName) {
        return new DeviceDTO(deviceName, deviceType, roomName);
    }
}