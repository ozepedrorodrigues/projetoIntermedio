package mappers;

import dto.DeviceDTO;
import domain.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * The DeviceMapper class serves as a mapping utility for converting Device objects to DeviceDTO objects.
 * It enables the mapping of a list of devices to a list of corresponding DeviceDTO objects.
 * <p>
 * This mapper is designed to simplify the transformation of device information,
 * ensuring a consistent representation when transferring data between different layers of an application.
 * </p>
 */
public class DeviceMapper {

    /**
     * Constructs a new instance of the MapperToDeviceDTO.
     * <p>
     * This constructor initializes the mapper for Device to DeviceDTO conversions.
     * </p>
     */
    public DeviceMapper() {
    }

    /**
     * Converts a single Device object to its corresponding DeviceDTO.
     *
     * @param device    The Device object to be converted.
     * @param roomName  The room where the device is located.
     * @return DeviceDTO A DeviceDTO representing the converted device information.
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
     * @param roomDevices The list of Device objects to be converted to DeviceDTO.
     * @param roomName    The name of the room where the devices are located.
     * @return List<DeviceDTO> A list of DeviceDTO objects associated with the specified room.
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