package controllers;

import domain.House;
import dto.DeviceDTO;
import dto.RoomDTO;
import factories.*;
import factories.implement.*;
import mappers.DeviceMapper;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the AddDeviceToRoomController class.
 * It tests the creation of the controller and its methods, ensuring that devices are correctly added to rooms,
 * and that the appropriate responses are given for invalid inputs.
 */

public class AddDeviceToRoomControllerTest {
    /**
     * The GPSFactory instance to be used in tests.
     */
    GPSFactory gpsFactory;
    /**
     * The LocationFactory instance to be used in tests.
     */
    LocationFactory locationFactory;
    /**
     * The path to the file to be used in tests.
     */
    String filePathName;
    /**
     * The SensorFactory instance to be used in tests.
     */
    SensorFactory sensorFactory;
    /**
     * The ActuatorFactory instance to be used in tests.
     */
    ActuatorFactory actuatorFactory;
    /**
     * The DeviceFactory instance to be used in tests.
     */
    DeviceFactory deviceFactory;
    /**
     * The DimensionsFactory instance to be used in tests.
     */
    DimensionsFactory dimensionsFactory;
    /**
     * The RoomFactory instance to be used in tests.
     */
    RoomFactory roomFactory;
    /**
     * The House instance to be used in tests.
     */
    House house;
    /**
     * The GetRoomListController instance to be used in tests.
     */
    GetRoomListController getRoomListController;
    /**
     * The DeviceMapper instance to be used in tests.
     */
    DeviceMapper deviceMapper;
    /**
     * The RoomMapper instance to be used in tests.
     */
    RoomMapper roomMapper;
    /**
     * The AddDeviceToRoomController instance to be used in tests.
     */
    AddDeviceToRoomController addDeviceToRoomController;
    /**
     * The AddRoomController instance to be used in tests.
     */
    AddRoomController addRoomController;

    /**
     * Set up the necessary objects for the tests.
     * Initializes the GPSFactory, LocationFactory, SensorFactory, ActuatorFactory, DeviceFactory, DimensionsFactory,
     *      RoomFactory, House, RoomMapper, GetRoomListController, DeviceMapper, AddDeviceToRoomController and
     *      AddRoomController instances to be used in tests.
     *
     * @throws InstantiationException if an instantiation exception occurs
     */
    @BeforeEach
    void setUpIntegration() throws InstantiationException {
        filePathName = "config.properties";
        gpsFactory = new GPSFactoryImp();
        locationFactory = new LocationFactoryImp(gpsFactory);
        sensorFactory = new SensorFactoryImp(filePathName);
        actuatorFactory = new ActuatorFactoryImp(filePathName);
        deviceFactory = new DeviceFactoryImp(sensorFactory,actuatorFactory);
        dimensionsFactory = new DimensionsFactoryImp();
        roomFactory = new RoomFactoryImp(dimensionsFactory, deviceFactory);
        house = new House(locationFactory, roomFactory);
        roomMapper = new RoomMapper();
        getRoomListController = new GetRoomListController(house, roomMapper);
        deviceMapper = new DeviceMapper();
        addDeviceToRoomController = new AddDeviceToRoomController(house, deviceMapper, getRoomListController);
        addRoomController = new AddRoomController(house, roomMapper);
    }

    /**
     * Test the constructor of the AddDeviceToRoomController with valid parameters.
     */
    @Test
    void constructorValid() {
        // Act
        AddDeviceToRoomController controller = new AddDeviceToRoomController(house, deviceMapper, getRoomListController);
        // Assert
        assertNotNull(controller);
    }

    /**
     * Test the constructor of the AddDeviceToRoomController with null house.
     */
    @Test
    void constructorNullHouse() {
        // Arrange
        House house = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, getRoomListController));
    }

    /**
     * Test the constructor of the AddDeviceToRoomController with null deviceMapper.
     */
    @Test
    void constructorNullMapper() {
        // Arrange
        DeviceMapper deviceMapper = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, getRoomListController));
    }

    /**
     * Test the constructor of the AddDeviceToRoomController with null getRoomListController.
     */
    @Test
    void constructorNullGetRoomListController() {
        // Arrange
        GetRoomListController getRoomListController = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, getRoomListController));
    }

    /**
     * Test the getRoomList method with one room.
     */
    @Test
    void getRoomListOneRoom() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        int sizeExpected = 1;
        // Act
        int sizeResult = addDeviceToRoomController.getRoomList().size();
        // Assert
        assertEquals(sizeExpected, sizeResult);
    }

    /**
     * Test the addDeviceToRoom method with valid parameters.
     */
    @Test
    void addDeviceToRoomValid() {
        // Arrange
        String roomName = "Room1";
        String deviceName = "Device1";
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "DeviceType1", "Room1");
        int initialSize = house.getRoomByName(roomName).getDevicesInRoom().size();
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName(roomName).getDevicesInRoom().size();
        String nameResult = house.getRoomByName(roomName).getDevicesInRoom().getFirst().getName();
        // Assert
        assertEquals(initialSize + 1, finalSize);
        assertEquals(deviceName, nameResult);
    }

    /**
     * Test the addDeviceToRoom method with a repeated device name.
     */
    @Test
    void addDeviceToRoomDeviceNameIsRepeated() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        DeviceDTO deviceDTO = new DeviceDTO("Device1", "DeviceType1", "Room1");
        // Act
        addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        // Assert
        assertNull(result);
    }

    /**
     * Test the addDeviceToRoom method with two rooms and adding a device to the last one.
     */
    @Test
    void addDeviceToCorrectRoom() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        RoomDTO roomDTO2 = new RoomDTO("Room2", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        addRoomController.addNewRoomToHouse(roomDTO2);
        DeviceDTO deviceDTO = new DeviceDTO("Device1", "DeviceType1", "Room2");
        int initialSize = house.getRoomByName("Room2").getDevicesInRoom().size();
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName("Room2").getDevicesInRoom().size();
        // Assert
        assertEquals(initialSize + 1, finalSize);
        assertNotNull(result);
    }

    /**
     * Test the addDeviceToRoom method with a null deviceDTO.
     */
    @Test
    void addDeviceToRoomNullDeviceDTO() {
        // Arrange
        DeviceDTO deviceDTO = null;
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        // Assert
        assertNull(result);
    }
}