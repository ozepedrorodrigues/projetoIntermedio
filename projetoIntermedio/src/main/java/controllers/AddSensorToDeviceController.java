package controllers;

import domain.*;
import dto.DeviceDTO;
import dto.RoomDTO;
import dto.SensorDTO;
import mappers.SensorMapper;
import sensors.Sensor;

import java.util.List;

/**
 * AddSensorToDeviceController is a controller class that provides functionality to add a sensor to an existing device in a room.
 * The AddSensorToDeviceController class includes methods to get a list of rooms, choose a room, get a list of devices in the chosen room, and get the sensor types.
 */
public class AddSensorToDeviceController {

    /**
     * The house instance.
     */
    private House house;
    /**
     * The catalogue instance.
     */
    private Catalogue catalogue;
    /**
     * The mapperSensorDTO instance.
     */
    private SensorMapper sensorMapper;
    /**
     * The getRoomListController instance.
     */
    private GetRoomListController getRoomListController;
    /**
     * The getDeviceListController instance.
     */
    private GetDeviceListController getDeviceListController;

    /**
     * Constructor for AddSensorToDeviceController.
     * Initializes the house, catalogue, mapperSensorDTO, getRoomListController, and getDeviceListController instances.
     *
     * @param house                   the house instance
     * @param catalogue               the catalogue instance
     * @param sensorMapper            the mapperSensorDTO instance
     * @param getRoomListController   the getRoomListController instance
     * @param getDeviceListController the getDeviceListController instance
     * @throws IllegalArgumentException if the house, getDeviceListController, getRoomListController, catalogue, or sensorMapper are not valid
     */
    public AddSensorToDeviceController(House house, Catalogue catalogue, SensorMapper sensorMapper,
                                       GetRoomListController getRoomListController, GetDeviceListController getDeviceListController)
            throws IllegalArgumentException {
        if (!validParameters(house, getDeviceListController, getRoomListController, catalogue, sensorMapper)) {
            throw new IllegalArgumentException();
        }
        this.house = house;
        this.catalogue = catalogue;
        this.sensorMapper = sensorMapper;
        this.getRoomListController = getRoomListController;
        this.getDeviceListController = getDeviceListController;
    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return this.getRoomListController.getRoomList();
    }

    /**
     * Gets the list of devices in a room.
     *
     * @param roomName the name of the room
     * @return the list of devices in the room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        if (roomName == null) {return null;}
        return this.getDeviceListController.getDevicesInRoom(roomName);
    }

    /**
     * Gets the list of sensor types.
     *
     * @return the list of sensor types.
     */
    public List<String> getSensorModel() {
        return catalogue.getSensorsCatalogue();
    }


    /**
     * Adds a sensor to an existing device in a room.
     *
     * @param roomName    the name of the room where the device is located
     * @param deviceName  the name of the device to which the sensor will be added
     * @param sensorModel the model of the sensor to be added
     * @return the sensor DTO representing the newly added sensor
     */
    public SensorDTO addSensorToExistingDevice(String roomName, String deviceName, String sensorModel) {
        Room room = house.getRoomByName(roomName);
        Device device = room.getDeviceByName(deviceName);
        Sensor sensor = device.addSensor(sensorModel);
        return sensorMapper.getSensorDTO(sensor);
    }

    /**
     * Validates the parameters required for creating an instance of AddSensorToDeviceController.
     *
     * @param house                   the house instance
     * @param getDeviceListController the getDeviceListController instance
     * @param getRoomListController   the getRoomListController instance
     * @param catalogue               the catalogue instance
     * @param sensorMapper            the mapperSensorDTO instance
     * @return true if all parameters are non-null, false otherwise
     */
    private boolean validParameters(House house, GetDeviceListController getDeviceListController, GetRoomListController getRoomListController, Catalogue catalogue, SensorMapper sensorMapper) {
        return house != null && getDeviceListController != null && getRoomListController != null && catalogue != null && sensorMapper != null;
    }
}