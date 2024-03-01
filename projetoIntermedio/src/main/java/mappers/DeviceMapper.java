package mappers;

import dto.DeviceDTO;
import domain.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Mapper for DeviceDTO.
 * It is used to map a list of devices to a list of DeviceDTO objects.
 */
public class DeviceMapper {
    /**
     * Constructs a new DeviceMapper.
     */
    public DeviceMapper() {
        // Constructor for DeviceMapper
    }

    /**
     * Converts a Device object to its corresponding DeviceDTO.
     *
     * @param device    The Device object to be converted.
     * @param roomName  The room where the device is located.
     * @return A DeviceDTO representing the converted device information.
     */
    public DeviceDTO deviceToDTO(Device device, String roomName) {
        return new DeviceDTO(device.getName(), device.getType(), roomName);
    }

    /**
     * This method takes a list of Device objects and a roomName parameter, and converts each device to a
     * corresponding DeviceDTO. The resulting list of DeviceDTO objects is associated with the specified room.
     *
     * If the provided list of devices is empty, the method returns an empty list.
     *
     * @param roomDevices the list of Device objects to be converted to DeviceDTO.
     * @param roomName    the name of the room where the devices are located.
     * @return a list of DeviceDTO objects associated with the specified room.
     */
    public List<DeviceDTO> devicesToDTO(List<Device> roomDevices, String roomName) {
        List<DeviceDTO> roomDevicesDTO = new ArrayList<>();
        if (!roomDevices.isEmpty()) {
            for (Device device : roomDevices) {
                roomDevicesDTO.add(deviceToDTO(device, roomName));
            }
        }
        return roomDevicesDTO;
    }

}