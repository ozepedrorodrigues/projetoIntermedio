package mappers;

import domain.Device;
import domain.SensorType;
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
     * @param sensorTypes the list of sensorTypes available in the house.
     * @return a map of devicesDTO (including roomName) grouped by functionality.
     */
   public Map<String, List<DeviceDTO>> groupDevicesByFunctionality(Map<String, List<Device>> devices, SensorType[] sensorTypes) {
       List<String> functionalities = new ArrayList<>();
       for (SensorType sensorType : sensorTypes)
           functionalities.add(sensorType.getSensorType());
       Map<String, List<DeviceDTO>> devicesPerFunctionality = new HashMap<>();
       for (String functionality : functionalities)
           devicesPerFunctionality.put(functionality, new ArrayList<>());
       for (Map.Entry<String, List<Device>> entry : devices.entrySet()) {
           String roomName = entry.getKey();
           for (Device device : entry.getValue()) {
               for (String functionality : device.getFunctionalities()) {
                   DeviceDTO deviceDTO = new DeviceDTO(device.getName(), device.getType(), roomName);
                   devicesPerFunctionality.get(functionality).add(deviceDTO);}}}
       return devicesPerFunctionality;}




}
