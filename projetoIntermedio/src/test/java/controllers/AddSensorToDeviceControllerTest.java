package controllers;

import domain.*;
import dto.DeviceDTO;
import dto.RoomDTO;
import dto.SensorDTO;
import factories.implement.*;
import mappers.DeviceMapper;
import mappers.SensorMapper;
import mappers.RoomMapper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


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
    private String filepath = "config.properties";

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
     * The DeviceMapper instance to be used in tests.
     */
    private DeviceMapper deviceMapper;

    /**
     * Set up the necessary objects for the tests
     * Initializes the house, sensorMapper, getRoomListController, getDeviceListController, and catalogue instances.
     * Initializes the room instance.
     *
     * @throws InstantiationException if an instantiation exception occurs
     */
    @BeforeEach
    void setUpIntegration() throws InstantiationException {
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(
                        new DimensionsFactoryImp(),
                        new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));

        sensorMapper = new SensorMapper();
        getRoomListController = new GetRoomListController(house, new RoomMapper());
        getDeviceListController = new GetDeviceListController(house, new DeviceMapper());
        catalogue = new Catalogue(filepath);
        room = house.addRoom("Room1", 1, 1, 1, 1);
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
     * Test to assess the Constructor of AddSensorToDeviceController with invalid parameters
     */
    @Test
    void testConstructorValid() {
        // Act
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        // Assert
        assertNotNull(controller);
    }

    @Test
    void testConstructorNullHouse() {
        // Arrange
        House house = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController));
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
     * <p>
     * It checks if the method returns the correct size of the room list.
     */
    @Test
    void testGetRoomList() {
        // Arrange
        String roomName = "Room1";
        house.addRoom(roomName, 1, 1, 1, 1);
        int expectedSize = 1;

        // Act
        int resultSize = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController).getRooms().size();

        // Assert
        assertEquals(expectedSize, resultSize);
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
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 1, 1, 1);
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);

        // Act
        List<DeviceDTO> devices = controller.getDevices(roomDTO);

        // Assert
        assertNotNull(devices, "Devices should not be null");
        assertEquals(house.getRoomByName(roomName).getDevicesInRoom().size(), devices.size());
    }

    /**
     * Test to assess the getDevices method of AddSensorToDeviceController with a non-existent room.
     * It checks if the method returns an empty list when the room does not exist.
     * The test asserts that the returned list matches the expected empty list.
     */
    /*@Test
    void testGetDeviceListNullRoom() {
        // Arrange
        RoomDTO roomDTO = null;
        //Act
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        // Assert
        assertNull(controller.getDevices(roomDTO));}*/

    /**
     * Test to assess the getSensorModel method of AddSensorToDeviceController with an invalid Catalogue.
     * It checks if the method throws an IllegalArgumentException when the Catalogue is null.
     * The test asserts that the exception message matches the expected message.
     */
    @Test
    void testGetSensorModelInvalidCatalogue() {
        // Arrange
        Catalogue invalidCatalogue = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, invalidCatalogue, sensorMapper, getRoomListController, getDeviceListController).getSensorModel());
    }

    /**
     * Test to assess the addSensorToExistingDevice method of AddSensorToDeviceController.
     * It checks if the method correctly adds a sensor to an existing device in a room.
     * The test asserts that the type of the added sensor matches the expected sensor type.
     */
    /*@Test
    void testAddSensorToExistingDevice() {
        // Arrange
        String roomName = "Room1";
        String deviceName = "Device1";
        String sensorModel = "SensorOfTemperature";
        RoomDTO roomDTO = null;
        DeviceDTO deviceDTO = null;
        Device device = this.room.addNewDevice(deviceName, "Light"); // use the room field here
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        SensorType expected = SensorType.TEMPERATURE;

        // Act
        controller.getDevices(roomDTO);
        SensorDTO result = controller.addSensorToExistingDevice(deviceDTO, sensorModel);


        // Assert
        assertEquals(expected, result.getTypeOfSensor());

    }*/

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
        DeviceDTO invalidDeviceDTO = null;

        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);

        // Act and Assert
        assertThrows(NullPointerException.class, () ->
                controller.addSensorToExistingDevice(invalidDeviceDTO, invalidSensorModel));
    }

    /**
     * Test to assess the getSensorModel method of AddSensorToDeviceController.
     * It checks if the method returns the correct list of sensor models.
     * The test asserts that the returned list matches the expected list.
     */
    /*@Test
    void testGetSensorModel() {
        // Arrange
        List<String> expectedSensorModels = Arrays.asList("SensorModel1", "SensorModel2", "SensorModel3");
        Catalogue catalogue = mock(Catalogue.class);
        when(catalogue.getSensorsCatalogue()).thenReturn(expectedSensorModels);
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);

        // Act
        List<String> resultSensorModels = controller.getSensorModel();

        // Assert
        assertEquals(expectedSensorModels, resultSensorModels);
    }*/

    @Test
    void testGetSensorModels() throws ConfigurationException{
        // Arrange
        String prefix = "sensor";
        Configurations configurations = new Configurations();
        Configuration configuration = configurations.properties(new File(filepath));
        String[] sensorModels = configuration.getStringArray(prefix);
        int expectedSize = sensorModels.length;

        // Act
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue, sensorMapper, getRoomListController, getDeviceListController);
        List<String> result = controller.getSensorModel();
        int resultSize = result.size();

        // Assert
        assertEquals(expectedSize, resultSize);
    }
}

