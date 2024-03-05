package domain;

import factories.DeviceFactory;
import factories.DimensionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This test class represents a group of unit tests for the Room class.
 * It uses valid room parameters and mock objects for DeviceFactory and DimensionsFactory.
 * It is used to test the Room constructor, the getter methods for room attributes, and the methods for device management in a room.
 */

class RoomTest {

    /**
     * deviceFactoryMock attribute.
     */
    DeviceFactory deviceFactoryMock;
    /**
     * deviceMock attribute.
     */
    Device deviceMock;
    /**
     * dimensionsFactoryMock attribute.
     */
    DimensionsFactory dimensionsFactoryMock;
    /**
     * dimensionsMock attribute.
     */
    Dimensions dimensionsMock;
    /**
     * validWidth attribute.
     */
    double validWidth;
    /**
     * validLength attribute.
     */
    double validLength;
    /**
     * validHeight attribute.
     */
    double validHeight;
    /**
     * validFloor attribute.
     */
    int validFloor;
    /**
     * validRoomName attribute.
     */
    String validRoomName;
    /**
     * validDeviceName attribute.
     */
    String validDeviceName;
    /**
     * validDeviceType attribute.
     */
    String validDeviceType;
    /**
     * validRoom attribute.
     */
    Room validRoom;

    /**
     * This method sets up the testing environment before each test.
     * It prepares the mock objects and their behaviors for the tests.
     * It also creates a valid Room object to be used in the tests.
     */
    @BeforeEach
    void setUp() {
        deviceFactoryMock = mock(DeviceFactory.class);
        deviceMock = mock(Device.class);
        dimensionsFactoryMock = mock(DimensionsFactory.class);
        dimensionsMock = mock(Dimensions.class);
        validWidth = 10;
        validLength = 20;
        validHeight = 30;
        validFloor = 1;
        validRoomName = "Living Room";
        validDeviceName = "Device";
        validDeviceType = "DeviceType";
        when(dimensionsFactoryMock.createDimensions(validWidth, validLength, validHeight)).thenReturn(dimensionsMock);
        when(deviceFactoryMock.createDevice(validDeviceName, validDeviceType)).thenReturn(deviceMock);
        when(dimensionsMock.getWidth()).thenReturn(validWidth);
        when(dimensionsMock.getLength()).thenReturn(validLength);
        when(dimensionsMock.getHeight()).thenReturn(validHeight);
        when(deviceMock.getName()).thenReturn(validDeviceName);
        when(deviceMock.getType()).thenReturn(validDeviceType);
        validRoom = new Room(validRoomName, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock);
    }

    /**
     * Test to check if the constructor of the Room class does not throw an exception when valid parameters are used.
     */
    @Test
    void roomConstructorValidParametersShouldNotThrowException() {
        // Act
        Room room = new Room(validRoomName, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock);
        String roomName = room.getRoomName();
        int floor = room.getFloor();
        Dimensions dimensions = room.getDimensions();
        double width = dimensions.getWidth();
        double length = dimensions.getLength();
        double height = dimensions.getHeight();
        // Assert
        assertEquals(validRoomName, roomName);
        assertEquals(validFloor, floor);
        assertEquals(validWidth, width);
        assertEquals(validLength, length);
        assertEquals(validHeight, height);
    }

    /**
     * Test to check if the constructor of the Room class does not throw an exception when border parameters are used.
     */
    @Test
    void roomConstructorBorderParametersShouldNotThrowException() {
        // Arrange
        int floor = -1;
        double width = 0.1;
        double length = 0.1;
        double height = 0;
        when(dimensionsFactoryMock.createDimensions(width, length, height)).thenReturn(dimensionsMock);
        when(dimensionsMock.getWidth()).thenReturn(width);
        when(dimensionsMock.getLength()).thenReturn(length);
        when(dimensionsMock.getHeight()).thenReturn(height);
        // Act
        Room room = new Room(validRoomName, floor, width, length, height, dimensionsFactoryMock, deviceFactoryMock);
        String roomName = room.getRoomName();
        int floorResult = room.getFloor();
        Dimensions dimensions = room.getDimensions();
        double widthResult = dimensions.getWidth();
        double lengthResult = dimensions.getLength();
        double heightResult = dimensions.getHeight();
        // Assert
        assertEquals(validRoomName, roomName);
        assertEquals(floor, floorResult);
        assertEquals(width, widthResult);
        assertEquals(length, lengthResult);
        assertEquals(height, heightResult);
    }

    /**
     * Test to verify that the constructor of the Room class throws an IllegalArgumentException when the name is empty.
     */
    @Test
    void roomConstructorEmptyName() {
        // Arrange
        String name = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(name, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock));
    }

    /**
     * Test to verify that the constructor of the Room class throws an IllegalArgumentException when the name is blank.
     */
    @Test
    void roomConstructorBlankName() {
        // Arrange
        String name = " ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(name, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock));

    }

    /**
     * Test to verify that the constructor of the Room class throws an IllegalArgumentException when the name is null.
     */
    @Test
    void roomConstructorNullName() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Room(null, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock));
    }

    /**
     * Test to verify that the getName method returns the correct name of the room.
     */
    @Test
    void getNameValid() {
        // Act
        String result = validRoom.getRoomName();
        // Assert
        assertEquals(validRoomName, result);
    }

    /**
     * Test to verify that the getFloor method returns the correct floor of the room.
     */
    @Test
    void getFloorValid() {
        // Act
        int result = validRoom.getFloor();
        // Assert
        assertEquals(validFloor, result);
    }


    /**
     * Test to verify that the getDimensions method returns the correct dimensions of the room.
     */
    @Test
    void getDimensionsValid() {
        // Act
        dimensionsMock = validRoom.getDimensions();
        double width = dimensionsMock.getWidth();
        double length = dimensionsMock.getLength();
        double height = dimensionsMock.getHeight();
        // Assert
        assertEquals(validWidth, width);
        assertEquals(validLength, length);
        assertEquals(validHeight, height);
    }

    /**
     * Test to verify that the createDevice method of the Room class returns the correct device when valid parameters are used.
     */
    @Test
    void addNewDeviceValid() {
        // Act
        Device device = validRoom.addNewDevice(validDeviceName, validDeviceType);
        String name = deviceMock.getName();
        String type = deviceMock.getType();
        // Assert
        assertEquals(validDeviceName, name);
        assertEquals(validDeviceType, type);
    }

    /**
     * Test to verify that the addNewDevice method of the Room class returns null when the device name already exists.
     */
    @Test
    void addNewDeviceNameAlreadyExists() {
        // Arrange
        validRoom.addNewDevice(validDeviceName, validDeviceType);
        // Act
        Device result = validRoom.addNewDevice(validDeviceName, validDeviceType);
        // Assert
        assertNull(result);
    }

    /**
     * Test to verify that the createDevice method of the Room class throws an IllegalArgumentException when the parameters are empty.
     */
    @Test
    void addNewDeviceDeviceEmptyParameters() {
        // Arrange
        String deviceName = "";
        String deviceType = "";
        when(deviceFactoryMock.createDevice(deviceName, deviceType)).thenReturn(null);
        // Act
        Device result = validRoom.addNewDevice(deviceName, deviceType);
        // Assert
        assertNull(result);
    }

    /**
     * Test to verify that the createDevice method of the Room class throws an IllegalArgumentException when the parameters are null.
     */
    @Test
    void addNewDeviceDeviceNullParameters() {
        // Arrange
        when(deviceFactoryMock.createDevice(null, null)).thenThrow(IllegalArgumentException.class);
        // Act
        Device result = validRoom.addNewDevice(null, null);
        // Assert
        assertNull(result);
    }

    /**
     * Test to verify that the getDevicesInRoom method returns the correct device
     */
    @Test
    void testGetDevicesInRoomReturnsCorrectDevices() {
        // Arrange
        validRoom.addNewDevice(validDeviceName, validDeviceType);
        int sizeExpected = 1;
        // Act
        List<Device> deviceList = validRoom.getDevicesInRoom();
        boolean deviceIsPresent = deviceList.contains(deviceMock);
        int sizeResult = deviceList.size();
        // Assert
        assertTrue(deviceIsPresent);
        assertEquals(sizeExpected, sizeResult);
    }

    /**
     * This test verifies that the getDeviceByName method of the Room class returns the correct device when it exists.
     */
    @Test
    void testGetDeviceByName() {
        // Arrange
        Device expected = validRoom.addNewDevice(validDeviceName, validDeviceType);
        // Act
        Device result = validRoom.getDeviceByName(validDeviceName);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the getDeviceByName method of the Room class returns null when the device does not exist.
     */
    @Test
    void testGetDeviceByNameNotFound() {
        // Act
        Device result = validRoom.getDeviceByName("OtherDevice");
        // Assert
        assertNull(result);
    }
}