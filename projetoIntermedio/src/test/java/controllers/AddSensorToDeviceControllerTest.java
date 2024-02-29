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

}