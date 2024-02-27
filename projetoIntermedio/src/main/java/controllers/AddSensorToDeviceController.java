package controllers;

import domain.*;
import dto.DeviceDTO;
import dto.RoomDTO;
import dto.SensorDTO;
import dto.SensorTypeDTO;
import factories.SensorFactory;
import factories.ValueFactory;
import factories.implement.SensorFactoryImp;
import factories.implement.ValueFactoryImp;
import mappers.MapperSensorDTO;
import mappers.MapperSensorTypeDTO;
import sensors.Sensor;

import java.io.File;
import java.util.List;

/**
 * AddSensorToDeviceController is a controller class for User Story 07.
 * It provides functionality to add a sensor to an existing device in a room.
 * The AddSensorToDeviceController class includes methods to get a list of rooms, choose a room, get a list of devices in the chosen room, and get the sensor types.
 */
public class AddSensorToDeviceController {

    private House house;
    private Room room;
    private Catalogue catalogue;
    private MapperSensorDTO mapperSensorDTO;
    private GetRoomListController getRoomListController;
    private GetDeviceListController getDeviceListController;

    /**
     * Constructor for AddSensorToDeviceController.
     * Initializes the house, catalogue, mapperSensorDTO, getRoomListController, and getDeviceListController instances.
     * Throws an InstantiationException if any of the parameters are null.
     * @param house the house instance
     * @param catalogue the catalogue instance
     * @param mapperSensorDTO the mapperSensorDTO instance
     * @param getRoomListController the getRoomListController instance
     * @param getDeviceListController the getDeviceListController instance
     * @throws InstantiationException if any of the parameters are null
     */
    public AddSensorToDeviceController(House house, Catalogue catalogue, MapperSensorDTO mapperSensorDTO,
                                       GetRoomListController getRoomListController, GetDeviceListController getDeviceListController)
            throws InstantiationException {
        if (!validParameters(house, getDeviceListController, getRoomListController, catalogue, mapperSensorDTO)) {
            throw new InstantiationException("Invalid parameters");
        }
        this.house = house;
        this.catalogue = catalogue;
        this.mapperSensorDTO = mapperSensorDTO;
        this.getRoomListController = getRoomListController;
        this.getDeviceListController = getDeviceListController;
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
        this.room = house.getRoomByName(roomName);
        return this.getDeviceListController.getDeviceList(roomName);
    }

    /**
     * Gets the list of sensor types.
     *
     * @return the list of sensor types.
     */
    public List<String> getSensorModel() {
        return catalogue.getCatalogue();
    }

    /**
     * Adds a sensor to an existing device in a room.
     *
     * @return the sensor DTO.
     */


    public SensorDTO addSensorToExistingDevice(String deviceName, String sensorModel) {
        Device device = room.getDeviceByName(deviceName);
        Sensor sensor = device.addSensor(sensorModel, catalogue);

        return mapperSensorDTO.getSensorDTO(sensor);
    }

    /**
     * Adds a sensor to an existing device in a room.
     * @param house the house instance
     * @param getDeviceListController the getDeviceListController instance
     * @param getRoomListController the getRoomListController instance
     * @param catalogue the catalogue instance
     * @param mapperSensorDTO the mapperSensorDTO instance
     * @return
     */
    private boolean validParameters(House house, GetDeviceListController getDeviceListController, GetRoomListController getRoomListController, Catalogue catalogue, MapperSensorDTO mapperSensorDTO) {
        return house != null && getDeviceListController != null && getRoomListController != null && catalogue != null && mapperSensorDTO != null;
    }


}