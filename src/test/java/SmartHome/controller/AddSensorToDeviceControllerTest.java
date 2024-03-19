package SmartHome.controller;

import SmartHome.domain.actuators.ActuatorFactoryImp;
import SmartHome.domain.device.DeviceFactoryImp;
import SmartHome.domain.house.GPSFactoryImp;
import SmartHome.domain.house.House;
import SmartHome.domain.house.LocationFactoryImp;
import SmartHome.domain.room.DimensionsFactoryImp;
import SmartHome.domain.room.Room;
import SmartHome.domain.room.RoomFactoryImp;
import SmartHome.domain.sensors.SensorFactoryImp;
import SmartHome.domain.sensors.SensorType;
import SmartHome.domain.utilities.Catalogue;
import SmartHome.domain.utilities.IdGenerator;
import SmartHome.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AddSensorToDeviceControllerTest is a test class for the AddSensorToDeviceController class.
 * It tests the constructor with valid and invalid parameters.
 */
class AddSensorToDeviceControllerTest {

    /**
     * The House instance to be used in tests.
     */
    private House house;

    /**
     * The path to the file to be used in tests.
     */
    private String filepath;

    /**
     * The SensorMapper instance to be used in tests.
     */
    private SensorMapper sensorMapper;

    /**
     * The GetRoomListController instance to be used in tests.
     */
    private GetRoomListController getRoomListController;


    /**
     * The GetDeviceListController instance to be used in tests.
     */
    private GetDeviceListController getDeviceListController;

    /**
     * The Catalogue instance to be used in tests.
     */
    private Catalogue catalogue;

    /**
     * The Room instance to be used in tests.
     */
    private Room room;

    /**
     * The room name to be used in tests.
     */
    private String roomName;

    /**
     * The device name to be used in tests.
     */
    private String deviceName;


    /**
     * Set up the necessary objects for the tests.
     * It instantiates a house, a sensorMapper, a getRoomListController, a getDeviceListController, a catalogue, a room, a roomName, a deviceName, a filepath, and a deviceMapper.
     * It also adds a room to the house.
     *
     * @throws InstantiationException if an instantiation exception occurs
     */
    @BeforeEach
    void setUpIntegration() throws InstantiationException {
        IdGenerator.resetSensorId();
        roomName = "Room1";
        deviceName = "Device1";
        filepath = "configTest.properties";
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));

        sensorMapper = new SensorMapper();
        getRoomListController = new GetRoomListController(house, new RoomMapper());
        getDeviceListController = new GetDeviceListController(house, new DeviceMapper(), getRoomListController);
        catalogue = new Catalogue(filepath);
        room = house.addRoom(roomName, 1, 1, 1, 1);
    }


    /**
     * Test to assess the Constructor of AddSensorToDeviceController with valid parameters.
     */
    @Test
    void testConstructorValid() {
        // Act
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        // Assert
        assertNotNull(controller);
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid house
     */
    @Test
    void testConstructorInvalidHouse() {
        // Arrange
        House invalidHouse = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(invalidHouse, catalogue, sensorMapper, getRoomListController, getDeviceListController));
    }


    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid mapperSensorDTO
     */
    @Test
    void testConstructorInvalidMapper() {
        // Arrange
        SensorMapper invalidSensorMapper = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, catalogue, invalidSensorMapper, getRoomListController, getDeviceListController));
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid GetRoomListController
     */
    @Test
    void testConstructorInvalidGetRoomListController() {
        // Arrange
        GetRoomListController invalidGetRoomListController = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, catalogue, sensorMapper, invalidGetRoomListController, getDeviceListController));
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid GetDeviceListController
     */
    @Test
    void testConstructorInvalidGetDeviceListController() {
        // Arrange
        GetDeviceListController invalidGetDeviceListController = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, invalidGetDeviceListController));
    }


    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid Catalogue
     */
    @Test
    void testConstructorInvalidCatalogue() {
        // Arrange
        Catalogue invalidCatalogue = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, invalidCatalogue, sensorMapper, getRoomListController, getDeviceListController));
    }


    /**
     * Test to assess the getRooms method of AddSensorToDeviceController.
     * It checks if the method returns the correct size of the room list.
     * The test asserts that the size of the room list matches the expected size.
     */
    @Test
    void testGetRoomList() {
        // Arrange
        house.addRoom(roomName, 1, 1, 1, 1);
        int expected = 1;

        // Act
        List<RoomDTO> result = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController).getRooms();

        // Assert
        assertEquals(expected, result.size());
        assertEquals(roomName, result.getFirst().getName());

    }

    /**
     * Test to assess the getDevices method of AddSensorToDeviceController.
     * It checks if the method returns the correct size of the device list for a given room.
     * The room is retrieved from the house by its name.
     * The test asserts that the room is not null and that the size of the device list matches the expected size.
     */
    @Test
    void testGetDeviceList() {
        // Arrange
        house.addRoom(roomName, 1, 1, 1, 1);
        house.getRoomByName(roomName).addNewDevice(deviceName, "Light");
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 1, 1, 1);
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        int expected = 1;

        // Act
        List<DeviceDTO> devices = controller.getDevices(roomDTO);

        // Assert
        assertNotNull(devices);
        assertEquals(expected, devices.size());
        assertEquals(deviceName, devices.getFirst().getName());
    }


    /**
     * Test to assess the getDevices method of AddSensorToDeviceController with an invalid room.
     * It checks if the method returns null when the room does not exist.
     * The test asserts that the returned list is null.
     */
    @Test
    void testGetDeviceListInvalidRoom() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO("InvalidRoom", 1, 1, 1, 1);
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        //  Act
        List<DeviceDTO> result = controller.getDevices(roomDTO);
        //  Assert
        assertNull(result);
    }


    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController.
     * It checks if the method adds a sensor to an existing device in a room.
     * The test asserts that the sensor is not null and that the sensor type matches the expected type.
     */
    @Test
    void testAddSensorToExistingDevice() {
        // Arrange
        String sensorModel = "SensorOfTemperature";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Light", roomName);
        house.getRoomByName(roomName).addNewDevice(deviceName, "Light");
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        SensorType expectedType = SensorType.TEMPERATURE;
        int expectedId = 1;

        // Act
        SensorDTO result = controller.addSensorToExistingDevice(deviceDTO, sensorModel);

        // Assert
        assertEquals(expectedType, result.getTypeOfSensor());
        assertEquals(expectedId, result.getSensorId());
    }

    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController with an invalid device.
     * It checks if the method returns null when the device does not exist.
     */
    @Test
    void testAddSensorToDeactivateDevice() {
        // Arrange
        String sensorModel = "SensorOfTemperature";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Light", roomName);
        house.getRoomByName(roomName).addNewDevice(deviceName, "Light");
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        SensorType expected = SensorType.TEMPERATURE;

        // Act
        house.getRoomByName(roomName).getDeviceByName(deviceName).deactivate();
        SensorDTO result = controller.addSensorToExistingDevice(deviceDTO, sensorModel);

        // Assert
        assertNull(result);
    }


    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController with an invalid device.
     * It checks if the method returns null when the device does not exist.
     * The test asserts that the returned sensor is null.
     */
    @Test
    void testAddSensorToExistingDeviceInvalidParametersDeviceIsNull() {
        // Arrange
        String sensorModel = "SensorOfTemperature";
        DeviceDTO invalidDeviceDTO = null;
        house.getRoomByName(roomName).addNewDevice(deviceName, "Light");
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        SensorType expected = SensorType.TEMPERATURE;

        // Act and Assert
        assertThrows(NullPointerException.class, () ->
                controller.addSensorToExistingDevice(invalidDeviceDTO, sensorModel));
    }


    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController with an invalid sensor model.
     * It checks if the method returns null when the sensor model does not exist.
     * The test asserts that the returned sensor is null.
     */
    @Test
    void testAddSensorToExistingDeviceInvalidSensorModel() {
        String sensorModel = "SensorOfLight";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Light", roomName);
        house.getRoomByName(roomName).addNewDevice(deviceName, "Light");
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);

        // Act
        SensorDTO result = controller.addSensorToExistingDevice(deviceDTO, sensorModel);

        // Assert
        assertNull(result);

    }

    /**
     * Test to assess the getSensorModel method of AddSensorToDeviceController.
     * It checks if the method returns the correct list of sensor models from the Catalogue.
     * The test asserts that the size of the returned list matches the expected size and that the sensor models in the list match the expected models.
     */
    @Test
    void testGetSensorModel() {
        // Arrange
        String sensorModel = "SmartHome.domain.sensors.SensorOfTemperature";
        String sensorModel2 = "SmartHome.domain.sensors.SensorOfHumidity";
        int expectedSize = 2;
        // Act
        List<String> result = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController).getSensorModel();
        String resultModel = result.get(0);
        String resultModel2 = result.get(1);

        // Assert
        assertEquals(expectedSize, result.size());
        assertEquals(sensorModel, resultModel);
        assertEquals(sensorModel2, resultModel2);
    }

}