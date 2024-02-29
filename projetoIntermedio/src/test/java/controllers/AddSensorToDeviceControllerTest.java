package controllers;

import domain.*;
import dto.DeviceDTO;
import dto.SensorDTO;
import factories.ValueFactory;
import factories.implement.*;
import mappers.MapperSensorDTO;
import mappers.MapperToDeviceDTO;
import mappers.MapperToRoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sensors.Sensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * AddSensorToDeviceControllerTest is a test class for the AddSensorToDeviceController class.
 * It tests the constructor with valid and invalid parameters.
 */
class AddSensorToDeviceControllerTest {

    private House house;

    private String filepath = "config.properties";

    private MapperSensorDTO mapperSensorDTO;

    private GetRoomListController getRoomListController;

    private GetDeviceListController getDeviceListController;

    private Catalogue catalogue;

    private Room room;

    private MapperToDeviceDTO mapperToDeviceDTO;

    private ValueFactory valueFactory;

    /**
     * Set up the house, catalogue, mapperSensorDTO, getRoomListController, and getDeviceListController instances.
     *
     * @throws InstantiationException if any of the parameters are null
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath, new ValueFactoryImp()))));

        mapperSensorDTO = new MapperSensorDTO();
        getRoomListController = new GetRoomListController(house, new MapperToRoomDTO());
        getDeviceListController = new GetDeviceListController(house, new MapperToDeviceDTO());
        catalogue = new Catalogue(filepath);
        room = house.addRoom("Room1", 1, 1, 1, 1);
        valueFactory = new ValueFactoryImp();


    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid house
     *
     * @throws InstantiationException if any of the parameters are null
     */
    @Test
    void testConstructorInvalidHouse() {
        // Arrange
        House invalidHouse = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(invalidHouse, catalogue, mapperSensorDTO, getRoomListController, getDeviceListController));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid mapperSensorDTO
     *
     * @throws InstantiationException if any of the parameters are null
     */
    @Test
    void testConstructorInvalidMapper() {
        // Arrange
        MapperSensorDTO invalidMapperSensorDTO = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, catalogue, invalidMapperSensorDTO, getRoomListController, getDeviceListController));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid GetRoomListController
     *
     * @throws InstantiationException if any of the parameters are null
     */
    @Test
    void testConstructorInvalidGetRoomListController() {
        // Arrange
        GetRoomListController invalidGetRoomListController = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, invalidGetRoomListController, getDeviceListController));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid GetDeviceListController
     *
     * @throws InstantiationException if any of the parameters are null
     */
    @Test
    void testConstructorInvalidGetDeviceListController() {
        // Arrange
        GetDeviceListController invalidGetDeviceListController = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, getRoomListController, invalidGetDeviceListController));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the Constructor of AddSensorToDeviceController with invalid Catalogue
     *
     * @throws InstantiationException if any of the parameters are null
     */
    @Test
    void testConstructorInvalidCatalogue() {
        // Arrange
        Catalogue invalidCatalogue = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, invalidCatalogue, mapperSensorDTO, getRoomListController, getDeviceListController));
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the getRoomList method of AddSensorToDeviceController.
     * It checks if the method returns the correct size of the room list.
     */
    @Test
    void testGetRoomList() {
        // Arrange
        String roomName = "Room1";
        house.addRoom(roomName, 1, 1, 1, 1);
        int expectedSize = 1;

        // Act
        int resultSize = new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, getRoomListController, getDeviceListController).getRoomList().size();

        // Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test to assess the getDeviceList method of AddSensorToDeviceController.
     * It checks if the method returns the correct size of the device list for a given room.
     * The room is retrieved from the house by its name.
     * The test asserts that the room is not null and that the size of the device list matches the expected size.
     */
    @Test
    void testGetDeviceList() {
        // Arrange
        String roomName = "Room1";
        Room room = house.getRoomByName(roomName);
        assertNotNull(room, "Room should not be null");

        int expectedSize = room.getDeviceList().size();

        // Act
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, getRoomListController, getDeviceListController);
        int resultSize = controller.getDeviceList(roomName).size();

        // Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test to assess the getDeviceList method of AddSensorToDeviceController with a non-existent room.
     * It checks if the method returns an empty list when the room does not exist.
     * The test asserts that the returned list matches the expected empty list.
     */
    @Test
    void testGetDeviceListNullRoom() {
        // Arrange
        String roomName = "RoomDoesNotExist";
        List<DeviceDTO> expected = new ArrayList<>();

        // Act
        List<DeviceDTO> result = new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, getRoomListController, getDeviceListController).getDeviceList(roomName);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to assess the getSensorModel method of AddSensorToDeviceController with an invalid Catalogue.
     * It checks if the method throws an IllegalArgumentException when the Catalogue is null.
     * The test asserts that the exception message matches the expected message.
     */
    @Test
    void tesGetSensorModelInvalidCatalogue() {
        // Arrange
        Catalogue invalidCatalogue = null;
        String expectedMessage = "Invalid parameters";

        // Act
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, invalidCatalogue, mapperSensorDTO, getRoomListController, getDeviceListController).getSensorModel());
        String resultMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController.
     * It checks if the method correctly adds a sensor to an existing device in a room.
     * The test asserts that the type of the added sensor matches the expected sensor type.
     */
    @Test
    void testAddSensorToExistingDevice() {
        // Arrange
        String roomName = "Room1";
        String deviceName = "Device1";
        String sensorModel = "SensorOfTemperature";
        Device device = this.room.addNewDevice(deviceName, "Light"); // use the room field here
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, getRoomListController, getDeviceListController);
        SensorType expected = SensorType.TEMPERATURE;

        // Act
        controller.getDeviceList(roomName);
        SensorDTO result = controller.addSensorToExistingDevice(roomName, deviceName, sensorModel);


        // Assert
        assertEquals(expected, result.getTypeOfSensor());

    }

    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController with invalid parameters.
     * It checks if the method throws a NullPointerException when the room name, device name, and sensor model are invalid.
     * The test asserts that the exception thrown is a NullPointerException.
     */
    @Test
    void testAddSensorToExistingDeviceInvalidParameters() {
        // Arrange
        String invalidRoomName = "InvalidRoom";
        String invalidDeviceName = "InvalidDevice";
        String invalidSensorModel = "InvalidSensorModel";
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, mapperSensorDTO, getRoomListController, getDeviceListController);

        // Act and Assert
        assertThrows(NullPointerException.class, () ->
                controller.addSensorToExistingDevice(invalidRoomName, invalidDeviceName, invalidSensorModel));
    }
}

