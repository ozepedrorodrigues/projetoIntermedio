package Controllers;

import DTOs.DeviceDTO;
import DTOs.RoomDTO;
import DTOs.SensorDTO;
import DTOs.SensorTypeDTO;
import Mappers.MapperSensorTypeDTO;
import org.domain.Device;
import org.domain.House;
import org.domain.Room;

import java.util.List;

/**
 * ControllerUC07 is a controller class for User Story 07.
 * It provides functionality to add a sensor to an existing device in a room.
 * The ControllerUC07 class includes methods to get a list of rooms, choose a room, get a list of devices in the chosen room, and get the sensor types.
 */
public class ControllerUC07 {
    /**
     * The house instance to which the room is to be added.
     */
    private House house;

    /**
     * Constructor for ControllerUC07.
     * Initializes the house instance.
     * I want to add a Sensor to an Existing Device in a Room
     * This controller will be used to add a sensor to an existing device
     */
    public ControllerUC07() {
        this.house = House.getInstance();

    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        GetRoomListController getRoomListController = new GetRoomListController();
        return getRoomListController.getRoomList();

    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room.
     * @return the list of devices in the room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        GetDeviceListController getDeviceListController = new GetDeviceListController();
        return getDeviceListController.getDeviceList(roomName);
    }

    /**
     * Gets the list of sensor types.
     *
     * @return the list of sensor types.
     */
    public SensorTypeDTO getSensorTypeDTO() {
        List<String> sensors = house.getCatalog().getCatalog();
        return new MapperSensorTypeDTO().getSensorTypes(sensors);
    }

    /**
     * Adds a sensor to an existing device in a room.
     *
     * @param deviceDTO the DTO of the device to which the sensor is to be added.
     * @param sensorDTO the DTO of the sensor to be added.
     * @return true if the sensor was successfully added, false otherwise.
     * @throws IllegalArgumentException if the deviceDTO, sensorDTO, device name, device location, sensor type, or sensor name is null.
     */
    public boolean addSensorToExistingDevice(DeviceDTO deviceDTO, SensorDTO sensorDTO) {
        if (deviceDTO == null || sensorDTO == null || deviceDTO.getName() == null ||
                deviceDTO.getLocation() == null || sensorDTO.getTypeOfSensor() == null ||
                sensorDTO.getSensorName() == null) return false;
        Room room = this.house.getRoomByName(deviceDTO.getLocation());
        if (room == null) return false;
        Device device = room.getDeviceByName(deviceDTO.getName());
        if (device == null) return false;
        return device.addSensor(sensorDTO.getSensorName(), sensorDTO.getTypeOfSensor());
    }
}