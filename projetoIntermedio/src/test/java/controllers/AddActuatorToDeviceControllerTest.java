package controllers;

import domain.Catalogue;
import domain.House;
import domain.Room;
import dto.ActuatorDTO;
import dto.DeviceDTO;
import factories.implement.*;
import mappers.ActuatorMapper;
import mappers.DeviceMapper;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddActuatorToDeviceControllerTest {

    private House house;
    private Room room;
    private String roomName = "Kitchen";
    private String deviceName = "Fridge";

    private String filepath;
    private Catalogue catalogue;

    private GetRoomListController getRoomListController;
    private GetDeviceListController getDeviceListController;
    private ActuatorMapper actuatorMapper;

    private AddActuatorToDeviceController addActuatorToDeviceController;

    @BeforeEach
    void setUp() throws InstantiationException {
        filepath = "config.properties";
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));
        room = house.addRoom(roomName, 1, 10, 10,10);
        room.addNewDevice(deviceName, "Device");

        catalogue = new Catalogue(filepath);
        getRoomListController = new GetRoomListController(house, new RoomMapper());
        getDeviceListController = new GetDeviceListController(house, new DeviceMapper());
        actuatorMapper = new ActuatorMapper();

        addActuatorToDeviceController = new AddActuatorToDeviceController(
                house, catalogue, getRoomListController, getDeviceListController, actuatorMapper);
    }

    @Test
    void addActuatorToDevice() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String actuatorModel = "ActuatoOfOnOff";
        ActuatorDTO expected = new ActuatorDTO(false);

        // Act
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, actuatorModel);

        // Assert
        assertEquals(expected.getState(), result.getState());
    }
}