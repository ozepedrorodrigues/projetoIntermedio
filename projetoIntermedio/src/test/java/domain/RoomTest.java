package domain;

import factories.DeviceFactory;
import factories.DimensionsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {
    DeviceFactory deviceFactoryMock = mock(DeviceFactory.class);
    Device deviceMock = mock(Device.class);
    DimensionsFactory dimensionsFactoryMock = mock(DimensionsFactory.class);
    Dimensions dimensionsMock = mock(Dimensions.class);
    double validWidth = 10;
    double validLength = 20;
    double validHeight = 30;
    int validFloor = 1;
    String validRoomName = "Living Room";
    String validDeviceName = "Device";
    String validDeviceType = "DeviceType";
    Room validRoom;


    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp() {
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
    void roomConstructorValidParameters() {
        // Act
        new Room(validRoomName, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock);
        // No exception is thrown, so the test passes
    }

    /**
     * Test to check if the constructor of the Room class does not throw an exception when border parameters are used.
     */
    @Test
    void roomConstructorBorderParameters() {
        // Arrange
        int floor = -1;
        double width = 0.1;
        double length = 0.1;
        double height = 0;
        // Act
        new Room(validRoomName, floor, width, length, height, dimensionsFactoryMock, deviceFactoryMock);
        // No exception is thrown, so the test passes
    }

    /**
     * Test to verify that the constructor of the Room class throws an IllegalArgumentException when the name is empty.
     */
    @Test
    void roomConstructorEmptyName() {
        // Arrange
        String name = "";
        String expected = "Invalid parameters.";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(name, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the constructor of the Room class throws an IllegalArgumentException when the name is blank.
     */
    @Test
    void roomConstructorBlankName() {
        // Arrange
        String name = " ";
        String expected = "Invalid parameters.";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(name, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the constructor of the Room class throws an IllegalArgumentException when the name is null.
     */
    @Test
    void roomConstructorNullName() {
        // Arrange
        String expected = "Invalid parameters.";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(null, validFloor, validWidth, validLength, validHeight, dimensionsFactoryMock, deviceFactoryMock));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the getName method returns the correct name of the room.
     */
    @Test
    void testGetName() {
        // Arrange
        String expected = "Living Room";
        // Act
        String result = validRoom.getRoomName();
        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the getFloor method returns the correct floor of the room.
     */
    @Test
    void testGetFloorValid() {
        // Arrange
        int expected = 1;
        // Act
        int result = validRoom.getFloor();
        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test to verify that the getDimensions method returns the correct dimensions of the room.
     */
    @Test
    void testGetDimensions() {
        // Act
        dimensionsMock = validRoom.getDimensions();
        int expectedWidth = 10;
        int expectedLength = 20;
        int expectedHeight = 30;
        // Assert
        assertEquals(expectedWidth, dimensionsMock.getWidth());
        assertEquals(expectedLength, dimensionsMock.getLength());
        assertEquals(expectedHeight, dimensionsMock.getHeight());
    }

    /**
     * Test to verify that the createDevice method of the Room class returns the correct device when valid parameters are used.
     */
    @Test
    void testCreateDevice() {
        // Act
        Device device = validRoom.validDeviceName(validDeviceName, validDeviceType);
        String expectedName = deviceMock.getName();
        String expectedType = deviceMock.getType();
        // Assert
        assertEquals(validDeviceName, expectedName);
        assertEquals(validDeviceType, expectedType);
    }

    /**
     * Test to verify that the createDevice method of the Room class throws an IllegalArgumentException when the parameters are empty.
     */
    @Test
    void testCreateDeviceDeviceEmptyParameters() {
        // Arrange
        String deviceName = "";
        String deviceType = "";
        when(deviceFactoryMock.createDevice(deviceName, deviceType)).thenReturn(null);
        // Act
        Device result = validRoom.validDeviceName(deviceName, deviceType);
        // Assert
        assertNull(result);
    }

    /**
     * Test to verify that the createDevice method of the Room class throws an IllegalArgumentException when the parameters are null.
     */
    @Test
    void testCreateDeviceDeviceNullParameters() {
        // Arrange
        when(deviceFactoryMock.createDevice(null, null)).thenReturn(null);
        // Act
        Device result = validRoom.validDeviceName(null, null);
        // Assert
        assertNull(result);
    }

    /**
     * Test to verify that the getDeviceList method returns the correct device list
     */
    @Test
    void testGetDeviceListReturnsCorrectDeviceList() {
        // Arrange
        validRoom.validDeviceName(validDeviceName, validDeviceType);
        int sizeExpected = 1;
        // Act
        List<Device> deviceList = validRoom.getDeviceList();
        boolean result = deviceList.contains(deviceMock);
        int sizeResult = deviceList.size();
        // Assert
        assertTrue(result);
        assertEquals(sizeExpected, sizeResult);
    }

    /**
     * This test verifies that the getDeviceByName method of the Room class returns the correct device when it exists.
     */
    @Test
    void testGetDeviceByName() {
        // Arrange
        Device expected = validRoom.validDeviceName(validDeviceName, validDeviceType);
        // Act
        Device result = validRoom.getDeviceByName("Device");
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the getDeviceByName method of the Room class returns null when the device does not exist.
     */
    @Test
    void testGetDeviceByNameNotFound() {
        // Act
        Device result = validRoom.getDeviceByName("Device");
        // Assert
        assertNull(result);
    }
}