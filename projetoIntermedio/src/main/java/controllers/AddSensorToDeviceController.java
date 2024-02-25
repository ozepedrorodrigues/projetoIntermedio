package controllers;

import dto.DeviceDTO;
import domain.House;
import domain.Room;

import java.util.List;

/**
 * ControllerUC07 is a controller class for User Story 07.
 * It provides functionality to add a sensor to an existing device in a room.
 * The ControllerUC07 class includes methods to get a list of rooms, choose a room, get a list of devices in the chosen room, and get the sensor types.
 */
public class AddSensorToDeviceController {
    /**
     * The house instance to which the room is to be added.
     */
    private House house;
    private Room room;

    /**
     * Constructor for ControllerUC07.
     * Initializes the house instance.
     * I want to add a Sensor to an Existing Device in a Room
     * This controller will be used to add a sensor to an existing device
     */
    public AddSensorToDeviceController(House house) {
        this.house = house;

    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
/*    public List<RoomDTO> getRoomList() {
        return new GetRoomListController(house).getRoomList();}*/

    /**
     * Gets the list of devices in a room.
     * @param roomName the name of the room.
     * @return the list of devices in the room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        this.room = house.getRoomByName(roomName);
        return new GetDeviceListController(house).getDeviceList(roomName);}

    /**
     * Gets the list of sensor types.
     *
     * @return the list of sensor types.
     */
/*    public SensorTypeDTO getSensorTypeDTO() {
        List<String> sensors = house.getCatalog().getSensorTypes();
        return new MapperSensorTypeDTO().getSensorTypes(sensors);
    }*/

    /**
     * Adds a sensor to an existing device in a room.
     *
     * @param sensorDTO the dto of the sensor to be added.
     * @return Sensor if the sensor was successfully added, null otherwise.
     * @throws IllegalArgumentException if the deviceDTO, sensorDTO, device name, device location, sensor type, or sensor name is null.
     */
/*    public SensorDTO addSensorToExistingDevice(String deviceName, SensorDTO sensorDTO) {
        Sensor sensor = room.getDeviceByName(deviceName).addSensor(sensorDTO.getSensorName(), sensorDTO.getTypeOfSensor());
        if (sensor == null) {return null;}
        return sensorDTO;
    }*/
}