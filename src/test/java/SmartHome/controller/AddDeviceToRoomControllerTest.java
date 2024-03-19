package SmartHome.controller;

import SmartHome.domain.device.DeviceFactory;
import SmartHome.domain.device.DeviceFactoryImp;
import SmartHome.domain.house.*;
import SmartHome.domain.actuators.ActuatorFactory;
import SmartHome.domain.actuators.ActuatorFactoryImp;
import SmartHome.domain.room.DimensionsFactory;
import SmartHome.domain.room.DimensionsFactoryImp;
import SmartHome.domain.room.RoomFactory;
import SmartHome.domain.room.RoomFactoryImp;
import SmartHome.domain.sensors.SensorFactory;
import SmartHome.domain.sensors.SensorFactoryImp;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomMapper;

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
     * Sets up the necessary objects for the tests.
     * Initializes factories, controllers, and other required instances.
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
     * The controller should be created successfully.
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
     * The controller should not be created and an IllegalArgumentException should be thrown.
     */
    @Test
    void constructorNullHouse() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(null, deviceMapper, getRoomListController));
    }

    /**
     * Test the constructor of the AddDeviceToRoomController with null deviceMapper.
     * The controller should not be created and an IllegalArgumentException should be thrown.
     */
    @Test
    void constructorNullMapper() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, null, getRoomListController));
    }

    /**
     * Test the constructor of the AddDeviceToRoomController with null getRoomListController.
     * The controller should not be created and an IllegalArgumentException should be thrown.
     */
    @Test
    void constructorNullGetRoomListController() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, null));
    }

    /**
     * Test the getRooms method with two rooms.
     * The method should return a list with two rooms.
     */
    @Test
    void getRoomListTwoRooms() {
        // Arrange
        String roomName1 = "Room1";
        String roomName2 = "Room2";
        RoomDTO roomDTO1 = new RoomDTO(roomName1, 1, 1, 1, 1);
        RoomDTO roomDTO2 = new RoomDTO(roomName2, 2, 2, 2, 2);
        addRoomController.addNewRoomToHouse(roomDTO1);
        addRoomController.addNewRoomToHouse(roomDTO2);
        int sizeExpected = 2;
        // Act
        int sizeResult = addDeviceToRoomController.getRooms().size();
        String nameResult1 = addDeviceToRoomController.getRooms().getFirst().getName();
        String nameResult2 = addDeviceToRoomController.getRooms().getLast().getName();
        // Assert
        assertEquals(sizeExpected, sizeResult);
        assertEquals(roomName1, nameResult1);
        assertEquals(roomName2, nameResult2);
    }

    /**
     * Test the addDeviceToRoom method with valid parameters.
     * The method should add a device to a room and return the deviceDTO representing the device added to the room.
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
        addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName(roomName).getDevicesInRoom().size();
        String nameResult = house.getRoomByName(roomName).getDevicesInRoom().getFirst().getName();
        // Assert
        assertEquals(initialSize + 1, finalSize);
        assertEquals(deviceName, nameResult);
    }

    /**
     * Test the addDeviceToRoom method with a repeated device name.
     * The method should not add the device to the room and return null.
     */
    @Test
    void addDeviceToRoomDeviceNameIsRepeated() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        DeviceDTO deviceDTO = new DeviceDTO("Device1", "DeviceType1", "Room1");
        int expectedSize = 1;
        // Act
        addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName("Room1").getDevicesInRoom().size();
        // Assert
        assertNull(result);
        assertEquals(expectedSize, finalSize);
    }

    /**
     * Test the addDeviceToRoom method with two rooms and adding a device to the last one.
     * The method should add the device to the correct room and return the deviceDTO representing the device added to the room.
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
     * The method should return null.
     */
    @Test
    void addDeviceToRoomNullDeviceDTO() {
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(null);
        // Assert
        assertNull(result);
    }
}