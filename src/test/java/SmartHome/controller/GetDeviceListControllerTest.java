package SmartHome.controller;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactoryImp;
import SmartHome.domain.house.GPSFactoryImp;
import SmartHome.domain.house.House;
import SmartHome.domain.room.DimensionsFactoryImp;
import SmartHome.domain.room.Room;
import SmartHome.domain.actuators.ActuatorFactoryImp;
import SmartHome.domain.house.LocationFactoryImp;
import SmartHome.domain.room.RoomFactoryImp;
import SmartHome.domain.sensors.SensorFactoryImp;
import SmartHome.domain.utilities.Catalogue;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceMapper;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;
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
    private House house;
    /**
     * RoomMapper instance.
     */
    private RoomMapper roomMapper = new RoomMapper();
    /**
     * AddRoomController instance.
     */
    private AddRoomController addRoomController;
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
        house = new House(
                new LocationFactoryImp(new GPSFactoryImp()),
                new RoomFactoryImp(new DimensionsFactoryImp(), new DeviceFactoryImp(new SensorFactoryImp(filepath), new ActuatorFactoryImp(filepath))));
        deviceMapper = new DeviceMapper();
        getRoomListController = new GetRoomListController(house, new RoomMapper());
        controller = new GetDeviceListController(house, deviceMapper, getRoomListController);
        addRoomController = new AddRoomController(house, roomMapper);
    }

    /**
     * Test for the constructor with valid house and mapper.
     * Verifies that the controller is successfully created without throwing an exception.
     * Also, ensures that the controller instance is not null.
     */
    @Test
    void testConstructorValidHouseAndMapper() {
        // Act & Assert
        assertDoesNotThrow(() -> new GetDeviceListController(house, deviceMapper, getRoomListController));
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
        assertThrows(IllegalArgumentException.class, () -> new GetDeviceListController(house, invalidDeviceMapper, getRoomListController));
    }

    /**
     * Unit test for the GetRoomListController getRooms() method when adding a single room.
     * Ensures that the method returns a list of rooms, and the size and name of the room match the expected values.
     */
    @Test
    void getRoomsWhenAddingOneRoom() {
        //Arrange
        String roomName = "roomName";
        house.addRoom(roomName, 0, 10, 10, 10);
        int expected = 1;
        //Act
        List<RoomDTO> result = getRoomListController.getRooms();
        //Assert
        assertEquals(expected, result.size());
        assertEquals(roomName, result.getFirst().getName());
    }

    /**
     * Test the getRooms method with two rooms.
     * The method should return a list with two rooms.
     /**
     * Unit test for the GetRoomListController getRooms() method when adding two rooms.
     * Ensures that the method returns a list of rooms, and the size and names of the rooms match the expected values.
     *
     * @throws InstantiationException If there is an issue with the instantiation of objects.
     */
    @Test
    void getRoomsWhenAddingTwoRooms() {
        // Arrange
        String roomName1 = "Room1";
        String roomName2 = "Room2";
        RoomDTO roomDTO1 = new RoomDTO(roomName1, 1, 1, 1, 1);
        RoomDTO roomDTO2 = new RoomDTO(roomName2, 2, 2, 2, 2);
        addRoomController.addNewRoomToHouse(roomDTO1);
        addRoomController.addNewRoomToHouse(roomDTO2);
        int sizeExpected = 2;
        // Act
        int sizeResult = getRoomListController.getRooms().size();
        String nameResult1 = getRoomListController.getRooms().getFirst().getName();
        String nameResult2 = getRoomListController.getRooms().getLast().getName();
        // Assert
        assertEquals(sizeExpected, sizeResult);
        assertEquals(roomName1, nameResult1);
        assertEquals(roomName2, nameResult2);
    }

    /**
     * Unit test for the GetRoomListController getRooms() method when the list is empty.
     * Ensures that the method returns an empty list when there are no rooms in the house.
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

        int expectedSize = 0;
        //Act
        List<RoomDTO> result = getRoomListController.getRooms();
        int resultSize = result.size();
        //Assert
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Unit test for the GetDeviceListController getDevicesInRoom(String) method with a valid room name.
     * Ensures that the returned list is not null and contains a list of DeviceDTO objects
     * for the devices in the specified room.
     */
    @Test
    void getDevicesInRoomValidRoomNameShouldReturnDeviceDTOList() {
        // Arrange
        String validRoomName = "Valid Room";
        Room room1 = house.addRoom(validRoomName, 0, 10, 10, 10);
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        int sizeExpected = 1;
        // Act
        List<DeviceDTO> result = controller.getDevicesInRoom(validRoomName);
        int sizeResult = getRoomListController.getRooms().size();
        // Assert
        assertNotNull(result);
        assertEquals(sizeExpected, sizeResult);
        assertEquals("Device1", result.get(0).getName());
        assertEquals("Sensor1", result.get(0).getType());
        assertEquals(validRoomName, result.get(0).getRoomName());
    }

    /**
     * Unit test for the GetDeviceListController getDevicesInRoom(String) method with an invalid room name.
     * Ensures that the method returns null when attempting to retrieve devices for a non-existing room.
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