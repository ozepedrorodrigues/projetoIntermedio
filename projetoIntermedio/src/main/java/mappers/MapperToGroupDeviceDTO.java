package mappers;

import domain.Device;
import dto.DeviceDTO;

import java.util.*;

/**
 * The MapperToGroupDeviceDTO class is used to map devices to a dto (Data Transfer Object) format and group them by functionality.
 * It provides methods to group devices by functionality and add a device to the map.
 */
public class MapperToGroupDeviceDTO {

    /**
     * Constructs a new MapperToGroupDeviceDTO.
     */
    public MapperToGroupDeviceDTO() {
    }

    /**
     * Groups devices by functionality.
     *
     * @param devices         the list of devices grouped by room.
     * @param functionalities the list of functionalities available in the house.
     * @return a map of devicesDTO (including roomName) grouped by functionality.
     */
   public Map<String, List<DeviceDTO>> groupDevicesByFunctionality(Map<String, List<Device>> devices, List<String> functionalities) {
        Map<String, List<DeviceDTO>> devicesPerFunctionality = new HashMap<>();
        for (String functionality : functionalities) {
            devicesPerFunctionality.put(functionality, new ArrayList<>());
        }
        for (Map.Entry<String, List<Device>> entry : devices.entrySet()) {
            String roomName = entry.getKey();
            List<Device> deviceList = entry.getValue();
            for (Device device : deviceList) {
                addDeviceToMap(device, roomName, devicesPerFunctionality);
            }
        }
        return devicesPerFunctionality;
    }

    /**
     * Adds a device to the map.
     *
     * @param device                  the device to add.
     * @param roomName                the room where the device is located.
     * @param devicesPerFunctionality the map of devices grouped by functionality.
     */
    private void addDeviceToMap(Device device, String roomName, Map<String, List<DeviceDTO>> devicesPerFunctionality) {
        HashSet<String> functionalityTypes = device.getFunctionalities();
        DeviceDTO deviceDTO = deviceToDeviceDTO(device.getName(), device.getType(), roomName);
        for (String functionalityType : functionalityTypes) {
            List<DeviceDTO> deviceList = devicesPerFunctionality.get(functionalityType);
            if (!deviceList.contains(deviceDTO)) {
                deviceList.add(deviceDTO);
            }
        }
    }

    /**
     * @param deviceName the name of the device.
     * @param deviceType the type of the device.
     * @param roomName   the room where the device is located.
     * @return the DeviceDTO.
     */
    private DeviceDTO deviceToDeviceDTO(String deviceName, String deviceType, String roomName) {
        return new DeviceDTO(deviceName, deviceType, roomName);
    }
}
