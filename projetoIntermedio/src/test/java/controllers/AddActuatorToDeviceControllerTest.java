package controllers;

import domain.*;
import dto.ActuatorDTO;
import dto.DeviceDTO;
import dto.RoomDTO;
import factories.implement.*;
import mappers.ActuatorMapper;
import mappers.DeviceMapper;
import mappers.RoomMapper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the AddActuatorToDeviceController class.
 */
class AddActuatorToDeviceControllerTest {

    /**
     * The house instance to which the room is to be added.
     */
    private House house;

    /**
     * The room instance to be used in the tests.
     */
    private Room room;

    /**
     * The name of the room to be used in the tests.
     */
    private String roomName;

    /**
     * The name of the device to be used in the tests.
     */
    private String deviceName;

    /**
     * The path to the configuration file.
     */
    private String filepath;

    /**
     * The catalogue instance to be used in the tests.
     */
    private Catalogue catalogue;

    /**
     * The AddActuatorToDeviceController instance to be used in the tests.
     */
    private GetRoomListController getRoomListController;

    /**
     * The GetDeviceListController instance to be used in the tests.
     */
    private GetDeviceListController getDeviceListController;

    /**
     * The ActuatorMapper instance to be used in the tests.
     */
    private ActuatorMapper actuatorMapper;

    /**
     * The AddActuatorToDeviceController instance to be used in the tests.
     */
    private AddActuatorToDeviceController addActuatorToDeviceController;

    /**
     * Set up the necessary objects for the tests.
     *
     * @throws InstantiationException if an error occurs during the instantiation of the objects.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        IdGenerator.resetActuatorId();
        filepath = "config.properties";
        roomName = "Kitchen";
        deviceName = "Fridge";
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));
        room = house.addRoom(roomName, 1, 10, 10, 10);
        room.addNewDevice(deviceName, "Device");

        catalogue = new Catalogue(filepath);
        getRoomListController = new GetRoomListController(house, new RoomMapper());
        getDeviceListController = new GetDeviceListController(house, new DeviceMapper());
        actuatorMapper = new ActuatorMapper();

        addActuatorToDeviceController = new AddActuatorToDeviceController(
                house, catalogue, getRoomListController, getDeviceListController, actuatorMapper);

    }


    /**
     * Test the constructor of the AddActuatorToDeviceController class with invalid parameters.
     * The house is null.
     */
    @Test
    void constructorInvalidHouse() {
        //Arrange
        House invalidHouse = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(
                invalidHouse, catalogue, getRoomListController, getDeviceListController, actuatorMapper));
    }

    /**
     * Test the constructor of the AddActuatorToDeviceController class with invalid parameters.
     * The catalogue is null.
     */
    @Test
    void constructorInvalidCatalogue() {
        //Arrange
        Catalogue invalidCatalogue = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(
                house, invalidCatalogue, getRoomListController, getDeviceListController, actuatorMapper));
    }

    /**
     * Test the constructor of the AddActuatorToDeviceController class with invalid parameters.
     * The getRoomListController is null.
     */
    @Test
    void constructorInvalidGetRoomListController() {
        //Arrange
        GetRoomListController invalidGetRoomListController = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(
                house, catalogue, invalidGetRoomListController, getDeviceListController, actuatorMapper));
    }

    /**
     * Test the constructor of the AddActuatorToDeviceController class with invalid parameters.
     * The getDeviceListController is null.
     */
    @Test
    void constructorInvalidGetDeviceListController() {
        //Arrange
        GetDeviceListController invalidGetDeviceListController = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(
                house, catalogue, getRoomListController, invalidGetDeviceListController, actuatorMapper));
    }

    /**
     * Test the constructor of the AddActuatorToDeviceController class with invalid parameters.
     * The actuatorMapper is null.
     */
    @Test
    void constructorInvalidActuatorMapper() {
        //Arrange
        ActuatorMapper invalidActuatorMapper = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(
                house, catalogue, getRoomListController, getDeviceListController, invalidActuatorMapper));
    }

    /**
     * Test the getRooms method when the house has no rooms.
     * The expected result is an empty list.
     *
     * @throws InstantiationException if an error occurs during the instantiation of the objects.
     */
    @Test
    void getRoomsEmptyList() throws InstantiationException {
        //Arrange
        House houseWithoutRooms = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));
        GetRoomListController getRoomListController = new GetRoomListController(houseWithoutRooms, new RoomMapper());
        AddActuatorToDeviceController addActuatorToDeviceController = new AddActuatorToDeviceController(
                houseWithoutRooms, catalogue, getRoomListController, getDeviceListController, actuatorMapper);
        int expectedSize = 0;

        //Act
        List<RoomDTO> result = addActuatorToDeviceController.getRooms();
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test the getRooms method when the house has one room.
     * The expected result is a list with one room.
     */
    @Test
    void getRoomsHouseWithOneRoom() {
        //Arrange
        int expectedSize = 1;

        //Act
        List<RoomDTO> result = addActuatorToDeviceController.getRooms();
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, result.size());
    }

    /**
     * Test the getDevices method when the room has no devices.
     * The expected result is an empty list.
     *
     * @throws InstantiationException if an error occurs during the instantiation of the objects.
     */
    @Test
    void getDevicesEmptyList() throws InstantiationException {
        //Arrange
        House houseWithoutDevices = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));
        houseWithoutDevices.addRoom(roomName, 1, 10, 10, 10);
        GetDeviceListController getDeviceListController = new GetDeviceListController(houseWithoutDevices, new DeviceMapper());
        AddActuatorToDeviceController addActuatorToDeviceController = new AddActuatorToDeviceController(
                houseWithoutDevices, catalogue, getRoomListController, getDeviceListController, actuatorMapper);
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 10, 10, 10);
        int expectedSize = 0;

        //Act
        List<DeviceDTO> result = addActuatorToDeviceController.getDevices(roomDTO);
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test the getDevices method when the room has one device.
     * The expected result is a list with one device.
     */
    @Test
    void getDevicesHouseWithOneDevice() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 10, 10, 10);
        int expectedSize = 1;

        //Act
        List<DeviceDTO> result = addActuatorToDeviceController.getDevices(roomDTO);
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, result.size());
    }

    /**
     * Test to verify that the getActuatorModels method returns a list with the same size of the one in config.properties.
     *
     * @throws ConfigurationException if an error occurs during the instantiation of the objects.
     */
    @Test
    void getActuatorModels() throws ConfigurationException {
        //Arrange
        String prefix = "actuator";
        Configurations configurations = new Configurations();
        Configuration configuration = configurations.properties(new File(filepath));
        String[] actuatorsModels = configuration.getStringArray(prefix);
        int expectedSize = actuatorsModels.length;

        //Act
        List<String> result = addActuatorToDeviceController.getActuatorModels();
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test the addActuatorToDevice method.
     * Test if the actuator is added to the device.
     */
    @Test
    void testAddActuatorToDevice() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String validClassName = "ActuatorOfOnOff";
        ActuatorDTO expected = new ActuatorDTO(1, ActuatorType.ONOFFSWITCH, false);

        // Act
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, validClassName);

        // Assert
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getType(), result.getType());
        assertEquals(expected.getState(), result.getState());
    }

    /**
     * Test the addActuatorToDevice method with an invalid class name.
     * The class name is empty.
     */
    @Test
    void testAddActuatorToDeviceInvalidClassNameIsEmpty() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String invalidClassName = "";

        // Act
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, invalidClassName);

        // Assert
        assertNull(result);
    }

    /**
     * Test the addActuatorToDevice method with an invalid class name.
     * The class name is blank.
     */
    @Test
    void testAddActuatorToDeviceInvalidClassNameIsBlank() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String invalidClassName = "     ";

        // Act
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, invalidClassName);

        // Assert
        assertNull(result);
    }

    /**
     * Test the addActuatorToDevice method with an invalid class name.
     * The class name is null.
     */
    @Test
    void testAddActuatorToDeviceInvalidClassNameIsNull() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String actuatorModel = null;

        // Act
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, actuatorModel);

        // Assert
        assertNull(result);
    }

    /**
     * Test the addActuatorToDevice method with an invalid class name.
     * The class name doesn't exist.
     */
    @Test
    void testAddActuatorToDeviceInvalidClassNameDoesntExist() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String actuatorModel = "sensorOfTemperature";

        // Act
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, actuatorModel);

        // Assert
        assertNull(result);
    }

}