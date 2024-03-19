package SmartHome.controller;

import SmartHome.domain.actuators.ActuatorFactoryImp;
import SmartHome.domain.actuators.ActuatorType;
import SmartHome.domain.device.DeviceFactoryImp;
import SmartHome.domain.house.GPSFactoryImp;
import SmartHome.domain.house.House;
import SmartHome.domain.house.LocationFactoryImp;
import SmartHome.domain.room.DimensionsFactoryImp;
import SmartHome.domain.room.Room;
import SmartHome.domain.room.RoomFactoryImp;
import SmartHome.domain.sensors.SensorFactoryImp;
import SmartHome.domain.utilities.Catalogue;
import SmartHome.domain.utilities.IdGenerator;
import SmartHome.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        filepath = "configTest.properties";
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
        getDeviceListController = new GetDeviceListController(house, new DeviceMapper(), getRoomListController);
        actuatorMapper = new ActuatorMapper();

        addActuatorToDeviceController = new AddActuatorToDeviceController(
                house, catalogue, getRoomListController, getDeviceListController, actuatorMapper);
    }

    /**
     * Test the constructor of the AddActuatorToDeviceController class with valid parameters.
     */
    @Test
    void constructorValid() {
        //Arrange - Act
        AddActuatorToDeviceController result = new AddActuatorToDeviceController(
                house, catalogue, getRoomListController, getDeviceListController, actuatorMapper);
        //Assert
        assertNotNull(result);
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
     * Test the getRooms method.
     * Test if the method returns an empty list when the house has no rooms.
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

        AddActuatorToDeviceController addActuatorToDeviceController =
                new AddActuatorToDeviceController(houseWithoutRooms, catalogue, getRoomListController, getDeviceListController, actuatorMapper);

        int expectedSize = 0;

        //Act
        List<RoomDTO> result = addActuatorToDeviceController.getRooms();
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test the getRooms method.
     * Test if the method returns a list with the same size of the one in the house.
     */
    @Test
    void getRoomsHouseWithOneRoom() {
        //Arrange
        int expectedSize = 1;
        String expectedRoomName = "Kitchen";

        //Act
        List<RoomDTO> result = addActuatorToDeviceController.getRooms();
        int resultSize = result.size();
        String resultRoomName = result.getFirst().getName();

        //Assert
        assertEquals(expectedSize, resultSize);
        assertEquals(expectedRoomName, resultRoomName);
    }

    /**
     * Test the getDevices method.
     * Test if the method returns an empty list when the room has no devices.
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

        GetRoomListController getRoomListController = new GetRoomListController(houseWithoutDevices, new RoomMapper());
        GetDeviceListController getDeviceListController = new GetDeviceListController(houseWithoutDevices, new DeviceMapper(), getRoomListController);

        AddActuatorToDeviceController addActuatorToDeviceController =
                new AddActuatorToDeviceController(houseWithoutDevices, catalogue, getRoomListController, getDeviceListController, actuatorMapper);

        RoomDTO roomDTO = new RoomDTO(roomName, 1, 10, 10, 10);
        int expectedSize = 0;

        //Act
        List<DeviceDTO> result = addActuatorToDeviceController.getDevices(roomDTO);
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test the getDevices method.
     * Test if the method returns a list with the same size of the one in the room.
     */
    @Test
    void getDevicesRoomWithOneDevice() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 10, 10, 10);
        int expectedSize = 1;
        String expectedDeviceName = "Fridge";

        //Act
        List<DeviceDTO> result = addActuatorToDeviceController.getDevices(roomDTO);
        int resultSize = result.size();
        String resultDeviceName = result.getFirst().getName();

        //Assert
        assertEquals(expectedSize, resultSize);
        assertEquals(expectedDeviceName, resultDeviceName);
    }

    /**
     * Test to verify that the getActuatorModels method returns a list with the same size of the one in config.properties.
     */
    @Test
    void getActuatorModels() {
        //Arrange
        int expectedSize = 3;
        String firstActuator = "SmartHome.domain.actuators.ActuatorOfOnOff";
        String secondActuator = "SmartHome.domain.actuators.ActuatorOfBlinds";

        //Act
        List<String> result = addActuatorToDeviceController.getActuatorModels();
        int resultSize = result.size();
        String firstResult = result.getFirst();
        String secondResult = result.get(1);

        //Assert
        assertEquals(expectedSize, resultSize);
        assertEquals(firstActuator, firstResult);
        assertEquals(secondActuator, secondResult);
    }

    /**
     * Test the addActuatorToDevice method.
     * Test if a single actuator is added to a device.
     */
    @Test
    void testAddSingleActuatorToDevice() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String validClassName = "ActuatorOfBlinds";
        int expectedSize = 1;
        int expectedId = 1;
        ActuatorType expectedType = ActuatorType.BLINDSMANAGER;

        // Act
        ActuatorDTO actuatorDTO = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, validClassName);
        int resultSize = room.getDeviceByName(deviceName).getDeviceActuators().size();
        int resultId = actuatorDTO.getId();
        ActuatorType resultType = actuatorDTO.getType();

        // Assert
        assertEquals(expectedId, resultId);
        assertEquals(expectedSize, resultSize);
        assertEquals(expectedType, resultType);
    }

    /**
     * Test the addActuatorToDevice method.
     * Test if many actuators are added to a device.
     */
    @Test
    void testAddManyActuatorsToDevice() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String validClassName = "ActuatorOfBlinds";
        String validClassName2 = "ActuatorOfDecimalLimiter";
        int expectedSize = 2;
        int expectedIdActuator1 = 1;
        int expectedIdActuator2 = 2;

        // Act
        addActuatorToDeviceController.addActuatorToDevice(deviceDTO, validClassName);
        addActuatorToDeviceController.addActuatorToDevice(deviceDTO, validClassName2);
        int resultSize = room.getDeviceByName(deviceName).getDeviceActuators().size();
        int resultIdActuator1 = room.getDeviceByName(deviceName).getDeviceActuators().getFirst().getId();
        int resultIdActuator2 = room.getDeviceByName(deviceName).getDeviceActuators().getLast().getId();

        // Assert
        assertEquals(expectedIdActuator1, resultIdActuator1);
        assertEquals(expectedIdActuator2, resultIdActuator2);
        assertEquals(expectedSize, resultSize);
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

    /**
     * Test the addActuatorToDevice method with an inactive device.
     */
    @Test
    void testAddActuatorToInactivatedDevice() {
        // Arrange
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Device", roomName);
        String actuatorModel = "ActuatorOfOnOff";
        int expectedSize = 0;
        // Act
        room.getDeviceByName(deviceName).deactivate();
        ActuatorDTO result = addActuatorToDeviceController.addActuatorToDevice(deviceDTO, actuatorModel);
        int resultSize = room.getDeviceByName(deviceName).getDeviceActuators().size();
        // Assert
        assertNull(result);
        assertEquals(expectedSize, resultSize);
    }
}
