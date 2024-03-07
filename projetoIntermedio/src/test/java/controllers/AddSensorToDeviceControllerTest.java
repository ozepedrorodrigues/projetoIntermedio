package controllers;

import domain.*;
import dto.DeviceDTO;
import dto.RoomDTO;
import dto.SensorDTO;
import factories.implement.*;
import mappers.DeviceMapper;
import mappers.SensorMapper;
import mappers.RoomMapper;
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
     * The DeviceMapper instance to be used in tests.
     */
    private DeviceMapper deviceMapper;

    /**
     * Set up the necessary objects for the tests.
     * It instantiates a house, a sensorMapper, a getRoomListController, a getDeviceListController, a catalogue, a room, a roomName, a deviceName, a filepath, and a deviceMapper.
     * It also adds a room to the house.
     *
     * @throws InstantiationException if an instantiation exception occurs
     */
    @BeforeEach
    void setUpIntegration() throws InstantiationException {
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
        String roomName = "Room1";
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
        String roomName = "Room1";
        String deviceName = "Device1";
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
     * It checks if the method returns the correct sensor type when adding a sensor to an existing device.
     * The test asserts that the returned sensor type matches the expected sensor type.
     * The device is added to the room before adding the sensor.
     * The room name, device name, and sensor model are valid.
     */
    @Test
    void testAddSensorToExistingDevice() {
        // Arrange
        String sensorModel = "SensorOfTemperature";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "Light", roomName);
        house.getRoomByName(roomName).addNewDevice(deviceName, "Light");
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        SensorType expected = SensorType.TEMPERATURE;

        // Act
        SensorDTO result = controller.addSensorToExistingDevice(deviceDTO, sensorModel);

        // Assert
        assertEquals(expected, result.getTypeOfSensor());
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
        SensorType expected = SensorType.TEMPERATURE;

        // Act
        SensorDTO result = controller.addSensorToExistingDevice(deviceDTO, sensorModel);

        // Assert
        assertNull(result);

    }

    /**
     * Test to assess the getSensorModel method of AddSensorToDeviceController.
     * It checks if the method returns the correct size of the sensor model list.
     */
    @Test
    void testGetSensorModel() {
        // Arrange
        int expectedSize = 2;
        // Act
        List<String> result = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController).getSensorModel();
        // Assert
        assertEquals(expectedSize, result.size());
    }

}

