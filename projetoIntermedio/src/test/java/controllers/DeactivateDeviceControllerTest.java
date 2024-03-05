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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the ControllerUC08 class.
 * Each test case corresponds to a different scenario of deactivating a device in a room.
 */
class DeactivateDeviceControllerTest {

    /**
     * The gpsFactory attribute.
     */
    GPSFactory gpsFactory;
    /**
     * The locationFactory attribute.
     */
    LocationFactory locationFactory;
    /**
     * The filePathName attribute.
     */
    String filePathName;
    /**
     * The sensorFactory attribute.
     */
    SensorFactory sensorFactory;
    /**
     * The actuatorFactory attribute.
     */
    ActuatorFactory actuatorFactory;
    /**
     * The deviceFactory attribute.
     */
    DeviceFactory deviceFactory;
    /**
     * The dimensionsFactory attribute.
     */
    DimensionsFactory dimensionsFactory;
    /**
     * The roomFactory attribute.
     */
    RoomFactory roomFactory;
    /**
     * The house attribute.
     */
    House house;
    /**
     * The getRoomListController attribute.
     */
    GetRoomListController getRoomListController;
    /**
     * The roomMapper attribute.
     */
    RoomMapper roomMapper;
    /**
     * The getDeviceListController attribute.
     */
    GetDeviceListController getDeviceListController;
    /**
     * The deviceMapper attribute.
     */
    DeviceMapper deviceMapper;
    /**
     * The addDeviceToRoomController attribute.
     */
    AddDeviceToRoomController addDeviceToRoomController;
    /**
     * The addRoomController attribute.
     */
    AddRoomController addRoomController;
    /**
     * The deactivateDeviceController attribute.
     */
    DeactivateDeviceController deactivateDeviceController;
    /**
     * The myDeviceDTO attribute.
     */
    DeviceDTO myDeviceDTO;


    /**
     * This method sets up the necessary objects for the tests.
     * @throws InstantiationException if an error occurs while creating the factories.
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
        deviceMapper = new DeviceMapper();
        getRoomListController = new GetRoomListController(house, roomMapper);
        getDeviceListController = new GetDeviceListController(house, deviceMapper);
        addDeviceToRoomController = new AddDeviceToRoomController(house, deviceMapper, getRoomListController);
        addRoomController = new AddRoomController(house, roomMapper);
        deactivateDeviceController = new DeactivateDeviceController(house, getRoomListController, getDeviceListController, deviceMapper);

        RoomDTO roomDTO = new RoomDTO("Room", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        myDeviceDTO = new DeviceDTO("Device", "Type", "Room");
        addDeviceToRoomController.addDeviceToRoom(myDeviceDTO);
    }

    /**
     * Test for the constructor of the DeactivateDeviceController with Valid Parameters
     * Should not throw any exception and the object should be created as not Null.
     */
    @Test
    void constructorValidParametersShouldNotThrowException()throws IllegalArgumentException{
        // Arrange & Act
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house, getRoomListController, getDeviceListController, deviceMapper);
        // Assert
        assertNotNull(deactivateDeviceController);}

    /**
     * Test for the constructor of the DeactivateDeviceController with Null House
     * Should throw IllegalArgumentException.
     */
    @Test
    void constructorNullHouseShouldThrowException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceController(null, getRoomListController, getDeviceListController, deviceMapper));
    }

    /**
     * Test for the constructor of the DeactivateDeviceController with Null GetRoomListController
     * Should throw IllegalArgumentException.
     */
    @Test
    void constructorNullGetRoomListControllerShouldThrowException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceController(house, null, getDeviceListController, deviceMapper));
    }

    /**
     * Test for the constructor of the DeactivateDeviceController with Null GetDeviceListController
     * Should throw IllegalArgumentException.
     */
    @Test
    void constructorNullGetDeviceListControllerShouldThrowException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceController(house, getRoomListController, null, deviceMapper));
    }

    /**
     * Test for the constructor of the DeactivateDeviceController with Null DeviceMapper
     * Should throw IllegalArgumentException.
     */
    @Test
    void constructorNullDeviceMapperShouldThrowException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceController(house, getRoomListController, getDeviceListController, null));
    }

    /**
     * Test for the getRooms() method in the DeactivateDeviceController
     * The test checks if the method returns a list with the room in the house.
     * The expected result is a list with one room.
     * The room has the following attributes: name = "Room", floor = 1, width = 1, length = 1, height = 1.
     */
    @Test
    void testGetRooms() {
        // Arrange
        int expectedListSize = 1;
        String expectedRoomName = "Room";
        int expectedRoomFloor = 1;
        double expectedRoomWidth = 1;
        double expectedRoomLength = 1;
        double expectedRoomHeight = 1;
        // Act
        List<RoomDTO> roomList = deactivateDeviceController.getRooms();
        int resultSize = roomList.size();
        String resultRoomName = roomList.getFirst().getName();
        int resultRoomFloor = roomList.getFirst().getFloor();
        double resultRoomWidth = roomList.getFirst().getWidth();
        double resultRoomLength = roomList.getFirst().getLength();
        double resultRoomHeight = roomList.getFirst().getHeight();
        // Assert
        assertEquals(expectedListSize, resultSize);
        assertEquals(expectedRoomName, resultRoomName);
        assertEquals(expectedRoomFloor, resultRoomFloor);
        assertEquals(expectedRoomWidth, resultRoomWidth);
        assertEquals(expectedRoomLength, resultRoomLength);
        assertEquals(expectedRoomHeight, resultRoomHeight);
    }

    /**
     * Test for the getDevices() method in the DeactivateDeviceController
     * The test checks if the method returns a list with the devices in a room.
     * The expected result is a list with one device.
     *
     */
    @Test
    void testGetDevices() {
        // Arrange
        String roomName = "Room";
        int expectedSize = 1;
        String expectedDeviceName = myDeviceDTO.getName();
        String expectedDeviceType = myDeviceDTO.getType();
        //  Act
        List<DeviceDTO> deviceList = deactivateDeviceController.getDevices(roomName);
        int resultSize = deviceList.size();
        String resultDeviceName = deviceList.getFirst().getName();
        String resultDeviceType = deviceList.getFirst().getType();
        // Assert
        assertEquals(expectedSize, resultSize);
        assertEquals(expectedDeviceName, resultDeviceName);
        assertEquals(expectedDeviceType, resultDeviceType);
    }

    /**
     * Test for the deactivateDevice() method in the DeactivateDeviceController
     * The test checks if the method deactivates a device in a room.
     * The expected result is true.
     */
    @Test
    void testDeactivateDevice() {
        // Arrange & Act
        boolean result = deactivateDeviceController.deactivateDevice(myDeviceDTO);
        // Assert
        assertTrue(result);}

    /**
     * Test for the deactivateDevice() method in the DeactivateDeviceController when the device is already deactivated
     * The test checks if the method deactivates a device in a room.
     * The expected result is false.
     */
    @Test
    void testDeactivateDeviceAgain() {
        // Arrange & Act
        deactivateDeviceController.deactivateDevice(myDeviceDTO);
        boolean result = deactivateDeviceController.deactivateDevice(myDeviceDTO);
        // Assert
        assertFalse(result);
    }

    /**
     * Test for the deactivateDevice() method in the DeactivateDeviceController when the deviceDTO is null
     * The test checks if the method deactivates a device in a room.
     * The expected result is false.
     */
    @Test
    void testDeactivateDeviceDTONull() {
        // Arrange & Act
        boolean result = deactivateDeviceController.deactivateDevice(null);
        // Assert
        assertFalse(result);}
}