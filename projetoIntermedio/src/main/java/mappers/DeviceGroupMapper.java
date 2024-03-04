package mappers;

import domain.Device;
import domain.SensorType;
import dto.DeviceDTO;

import java.util.*;

/**
 * The DeviceGroupMapper class is used to map devices to a dto (Data Transfer Object) format and group them by functionality.
 * It provides methods to group devices by functionality and add a device to the map.
 */
public class DeviceGroupMapper {

    /**
     * Constructs a new DeviceGroupMapper.
     * This constructor is empty because it does not have any attributes to initialize.
     */
    public DeviceGroupMapper() {
    }

    /**
     * Groups devicesByRoom by functionality.
     *
     * @param devicesByRoom         the list of {@link Device} grouped by {@link domain.Room}.
     * @param sensorTypes the list of {@link SensorType} enum instances available
     * @return a map of {@link dto.DeviceDTO} (including roomName) grouped by functionality.
     */
   public Map<String, List<DeviceDTO>> groupDevicesByFunctionality(Map<String, List<Device>> devicesByRoom, SensorType[] sensorTypes) {
       List<String> functionalities = new ArrayList<>();
       for (SensorType sensorType : sensorTypes)
           functionalities.add(sensorType.getSensorType());
       Map<String, List<DeviceDTO>> devicesPerFunctionality = new HashMap<>();
       for (String functionality : functionalities)
           devicesPerFunctionality.put(functionality, new ArrayList<>());
       for (Map.Entry<String, List<Device>> entry : devicesByRoom.entrySet()) {
           String roomName = entry.getKey();
           for (Device device : entry.getValue()) {
               for (String functionality : device.getFunctionalities()) {
                   DeviceDTO deviceDTO = new DeviceDTO(device.getName(), device.getType(), roomName);
                   devicesPerFunctionality.get(functionality).add(deviceDTO);}}}
       return devicesPerFunctionality;}




}
