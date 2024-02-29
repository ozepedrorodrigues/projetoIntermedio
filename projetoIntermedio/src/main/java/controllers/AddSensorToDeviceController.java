package controllers;

import domain.*;
import dto.DeviceDTO;
import dto.RoomDTO;
import dto.SensorDTO;
import factories.ValueFactory;
import factories.implement.ValueFactoryImp;
import mappers.MapperSensorDTO;
import sensors.Sensor;

import java.util.List;

/**
 * AddSensorToDeviceController is a controller class that provides functionality to add a sensor to an existing device in a room.
 * This class is part of the implementation of User Story 07 (provide a brief description of User Story 07 here).
 * The AddSensorToDeviceController class includes methods to get a list of rooms, choose a room, get a list of devices in the chosen room, and get the sensor types.
 */
public class AddSensorToDeviceController {

    private House house;
    private Catalogue catalogue;
    private MapperSensorDTO mapperSensorDTO;
    private GetRoomListController getRoomListController;
    private GetDeviceListController getDeviceListController;
    private ValueFactory valueFactory;

    /**
     * Constructor for AddSensorToDeviceController.
     * Initializes the house, catalogue, mapperSensorDTO, getRoomListController, and getDeviceListController instances.
     * Throws an InstantiationException if any of the parameters are null.
     * @param house                   the house instance
     * @param catalogue               the catalogue instance
     * @param mapperSensorDTO         the mapperSensorDTO instance
     * @param getRoomListController   the getRoomListController instance
     * @param getDeviceListController the getDeviceListController instance
     * @throws InstantiationException if any of the parameters are null
     */
    public AddSensorToDeviceController(House house, Catalogue catalogue, MapperSensorDTO mapperSensorDTO,
                                       GetRoomListController getRoomListController, GetDeviceListController getDeviceListController)
            throws IllegalArgumentException {
        if (!validParameters(house, getDeviceListController, getRoomListController, catalogue, mapperSensorDTO)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.house = house;
        this.catalogue = catalogue;
        this.mapperSensorDTO = mapperSensorDTO;
        this.getRoomListController = getRoomListController;
        this.getDeviceListController = getDeviceListController;
        this.valueFactory = new ValueFactoryImp();
    }

    /**
     * Gets the list of rooms in the house.
     * @return the list of rooms in the house.
     */
    public List<RoomDTO> getRoomList() {
        return this.getRoomListController.getRoomList();
    }

    /**
     * Gets the list of devices in a room.
     * @param roomName the name of the room
     * @return the list of devices in the room.
     */
    public List<DeviceDTO> getDeviceList(String roomName) {
        return this.getDeviceListController.getDevicesInRoom(roomName);
    }

    /**
     * Gets the list of sensor types.
     * @return the list of sensor types.
     */
    public List<String> getSensorModel() {
        return catalogue.getCatalogue();
    }
    //Esse metodo só pode ser testado no final ou a lista de sensores está sempre sendo atualizada

    /**
     * Adds a sensor to an existing device in a room.
     * @param roomName the name of the room where the device is located
     * @param deviceName the name of the device to which the sensor will be added
     * @param sensorModel the model of the sensor to be added
     * @return the sensor DTO representing the newly added sensor
     */
    public SensorDTO addSensorToExistingDevice(String roomName, String deviceName, String sensorModel) {
        Room room = house.getRoomByName(roomName);
        Device device = room.getDeviceByName(deviceName);
        Sensor sensor = device.addSensor(sensorModel, catalogue);

        return mapperSensorDTO.getSensorDTO(sensor);
    }

    /**
     * Validates the parameters required for creating an instance of AddSensorToDeviceController.
     * @param house the house instance
     * @param getDeviceListController the getDeviceListController instance
     * @param getRoomListController the getRoomListController instance
     * @param catalogue the catalogue instance
     * @param mapperSensorDTO the mapperSensorDTO instance
     * @return true if all parameters are non-null, false otherwise
     */
    private boolean validParameters(House house, GetDeviceListController getDeviceListController, GetRoomListController getRoomListController, Catalogue catalogue, MapperSensorDTO mapperSensorDTO) {
        return house != null && getDeviceListController != null && getRoomListController != null && catalogue != null && mapperSensorDTO != null;
    }
}