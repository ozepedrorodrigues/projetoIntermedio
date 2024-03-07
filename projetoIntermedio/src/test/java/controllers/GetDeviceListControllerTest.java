package controllers;

import domain.Device;
import domain.House;
import domain.Room;
import dto.DeviceDTO;
import factories.*;
import factories.implement.*;
import mappers.DeviceMapper;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests with integration for the GetDeviceListController class.
 * It focuses on testing the functionality of getting the list of devices in a room.
 */
class GetDeviceListControllerTest {

    /**
     * Common Controller class for getting the list of devices in a room.
     */
    private GetDeviceListController controller;
    /**
     * The GetRoomListController instance to be used in tests.
     */
    GetRoomListController getRoomListController;
    /**
     * DeviceMapper instance.
     */
    private DeviceMapper deviceMapper;
    /**
     * House instance.
     */
    private House matryoshka;
    /**
     * String filepath.
     */
    private String filepath = "config.properties";

    /**
     * Set up the test environment by instantiating the house and the device mapper.
     *
     * @throws InstantiationException If there is an issue with the instantiation.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        matryoshka = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(new DimensionsFactoryImp(), new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));
        deviceMapper = new DeviceMapper();
        getRoomListController = new GetRoomListController(matryoshka, new RoomMapper());
        controller = new GetDeviceListController(matryoshka, deviceMapper, getRoomListController);
    }

    /**
     * Test for the constructor with valid house and mapper.
     * Verifies that the controller is successfully created without throwing an exception.
     * Also, ensures that the controller instance is not null.
     */
    @Test
    void testConstructorValidHouseAndMapper() {
        // Act & Assert
        assertDoesNotThrow(() -> new GetDeviceListController(matryoshka, deviceMapper, getRoomListController));
        assertNotNull(controller);
    }

    /**
     * Test for the constructor with an invalid house, expecting an IllegalArgumentException.
     * Verifies that the controller creation throws an IllegalArgumentException when an invalid house is provided.
     */
    @Test
    void testConstructorInvalidHouseShouldThrowException() {
        // Arrange
        House invalidHouse = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetDeviceListController(invalidHouse, deviceMapper, getRoomListController));
    }

    /**
     * Test for the constructor with an invalid mapper, expecting an IllegalArgumentException.
     * Verifies that the controller creation throws an IllegalArgumentException when an invalid mapper is provided.
     */
    @Test
    void testConstructorInvalidMapperShouldThrowException() {
        // Arrange
        DeviceMapper invalidDeviceMapper = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetDeviceListController(matryoshka, invalidDeviceMapper, getRoomListController));
    }

    /**
     * Test for retrieving the list of devices in a room with a valid room name.
     * Verifies that the returned list is not null and contains the expected device details.
     */
    @Test
    void getDevicesInRoomValidRoomNameShouldReturnDeviceDTOList() {
        // Arrange
        String validRoomName = "Valid Room";
        Room room1 = matryoshka.addRoom(validRoomName, 0, 10, 10, 10);
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        // Act
        List<DeviceDTO> result = controller.getDevicesInRoom(validRoomName);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());  // Assuming you added one device
        assertEquals("Device1", result.get(0).getName());
        assertEquals("Sensor1", result.get(0).getType());
        assertEquals(validRoomName, result.get(0).getRoomName());
    }

    /**
     * Test for retrieving the list of devices in a room with an invalid room name.
     * Verifies that the returned list is null.
     */
    @Test
    void getDevicesInRoomInvalidRoom() {
        // Arrange
        String invalidRoomName = "Non existing Room";
        // Act
        List<DeviceDTO> result = controller.getDevicesInRoom(invalidRoomName);
        // Assert
        assertNull(result);
    }
}