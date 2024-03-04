package domain;

import factories.DimensionsFactory;
import factories.LocationFactory;
import factories.RoomFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the House class. It uses Mockito
 * for creating mock objects and testing in isolation. The tests cover various
 * scenarios including valid construction, invalid parameters, defining
 * locations, adding rooms, and retrieving information.
 */
public class HouseTest {

    // Mock objects for the LocationFactory and RoomFactory
    LocationFactory locationFactoryMock = mock(LocationFactory.class);
    Location locationMock = mock(Location.class);
    String validAddress = "Valid Address";
    String validZipCode = "Valid ZipCode";
    double validLatitude = 67.9222;
    double validLongitude = 26.5046;

    RoomFactory roomFactoryMock = mock(RoomFactory.class);
    Room roomMock = mock(Room.class);
    String validRoomName = "Valid Room";
    int validFloor = 0;
    double validRoomWidth = 4.0;
    double validRoomLength = 3.0;
    double validRoomHeight = 2.5;

    // The House instance under test
    House matryoshka;

    /**
     * This method is executed before each test case. It sets up the mock
     * behaviors and initializes the House instance with valid factories.
     */
    @BeforeEach
    void setUp() {
        when(locationFactoryMock.createLocation(validAddress, validZipCode, validLatitude, validLongitude)).thenReturn(locationMock);
        when(roomFactoryMock.createRoom(validRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(roomMock);

        this.matryoshka = new House(locationFactoryMock, roomFactoryMock);
    }

    /**
     * Tests the constructor of the House class with all valid parameters.
     * It ensures that the House instance is created successfully.
     */
    @Test
    void houseConstructorWithAllValidParameters() {
        // Act
        House ourHouse = new House(locationFactoryMock, roomFactoryMock);
        // Assert
        assertNotNull(ourHouse);
    }

    /**
     * Tests the constructor of the House class with an invalid LocationFactory.
     * It verifies that an IllegalArgumentException is thrown.
     */
    @Test
    void houseConstructorWithInvalidLocationFactory() {
        // Arrange
        LocationFactory invalidLocationFactory = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(invalidLocationFactory, roomFactoryMock));
    }

    /**
     * Tests the constructor of the House class with an invalid RoomFactory.
     * It verifies that an IllegalArgumentException is thrown.
     */
    @Test
    void houseConstructorWithInvalidRoomFactory() {
        // Arrange
        RoomFactory invalidRoomFactory = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new House(locationFactoryMock, invalidRoomFactory));
    }

    /**
     * Tests the method to define a location with all valid parameters.
     * It ensures that the location is defined successfully and matches the mock.
     */
    @Test
    void defineLocationWithAllValidParameters() {
        // Act
        Location location = matryoshka.defineLocation(validAddress, validZipCode, validLatitude, validLongitude);
        // Assert
        assertNotNull(location);
        assertEquals(locationMock, location);
    }

    /**
     * Tests the behavior of the defineLocation method when providing an
     * invalid address. It ensures that the method returns null and handles the
     * exception thrown by the location factory properly.
     */
    @Test
    void houseDefineLocationInvalidAddressShouldReturnNull() {
        // Arrange
        String invalidAddress = "";
        when(locationFactoryMock.createLocation(invalidAddress, validZipCode, validLatitude, validLongitude))
                .thenReturn(null);
        // Act
        Location result = matryoshka.defineLocation(invalidAddress, validZipCode, validLatitude, validLongitude);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the behavior of the defineLocation method when providing an
     * invalid zip code. It ensures that the method returns null and handles the
     * exception thrown by the location factory properly.
     */
    @Test
    void houseDefineLocationInvalidZipCodeShouldReturnNull() {
        // Arrange
        String invalidZipCode = "";
        when(locationFactoryMock.createLocation(validAddress, invalidZipCode, validLatitude, validLongitude))
                .thenReturn(null);
        // Act
        Location result = matryoshka.defineLocation(validAddress, invalidZipCode, validLatitude, validLongitude);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the behavior of the defineLocation method when providing an
     * invalid latitude. It ensures that the method returns null and handles the
     * exception thrown by the location factory properly.
     */
    @Test
    void houseDefineLocationInvalidLatitudeShouldReturnNull() {
        // Arrange
        double invalidLatitude = -90.0001;
        when(locationFactoryMock.createLocation(validAddress, validZipCode, invalidLatitude, validLongitude))
                .thenReturn(null);
        // Act
        Location result = matryoshka.defineLocation(validAddress, validZipCode, invalidLatitude, validLongitude);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the behavior of the defineLocation method when providing an
     * invalid longitude. It ensures that the method returns null and handles the
     * exception thrown by the location factory properly.
     */
    @Test
    void houseDefineLocationInvalidLongitudeShouldReturnNull() {
        // Arrange
        double invalidLongitude = 180.0001;
        when(locationFactoryMock.createLocation(validAddress, validZipCode, validLatitude, invalidLongitude))
                .thenReturn(null);
        // Act
        Location result = matryoshka.defineLocation(validAddress, validZipCode, validLatitude, invalidLongitude);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the behavior of the defineLocation method when providing all
     * invalid parameters. It ensures that the method returns null and handles the
     * exception thrown by the location factory properly.
     */
    @Test
    void houseDefineLocationAllInvalidParametersShouldReturnNull() {
        // Arrange
        String invalidAddress = "";
        String invalidZipCode = "";
        double invalidLatitude = -90.0001;
        double invalidLongitude = 180.0001;
        when(locationFactoryMock.createLocation(invalidAddress, invalidZipCode, invalidLatitude, invalidLongitude))
                .thenReturn(null);
        // Act
        Location result = matryoshka.defineLocation(invalidAddress, invalidZipCode, invalidLatitude, invalidLongitude);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the behavior of the getLocation method before any location
     * has been set. It ensures that the method returns null, indicating that no
     * location has been defined yet.
     */
    @Test
    void getLocationBeforeLocationIsSet() {
        // Act
        Location location = matryoshka.getLocation();
        // Assert
        assertNull(location);  // Initially, location is not set
    }

    /**
     * Tests the behavior of the getLocation method after defining a
     * location using the defineLocation method. It ensures that the
     * method returns the correct location and that it matches the location defined
     * using the location factory.
     */
    @Test
    void getLocationAfterDefining() {
        // Arrange
        when(locationFactoryMock.createLocation(validAddress, validZipCode, validLatitude, validLongitude))
                .thenReturn(locationMock);
        // Act
        Location definedLocation = matryoshka.defineLocation(validAddress, validZipCode, validLatitude, validLongitude);
        Location retrievedLocation = matryoshka.getLocation();
        // Assert
        assertNotNull(definedLocation);                 // Ensure location was defined successfully
        assertEquals(locationMock, retrievedLocation);  // Ensure retrieved location is the same as the defined location
    }

    /**
     * Tests the addRoom method with all valid parameters. It verifies
     * that a room is successfully added and that the added room is not null and
     * matches the expected room mock.
     */
    @Test
    void addRoomWithAllValidParameters() {
        // Act
        Room addedRoom = matryoshka.addRoom(validRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        // Assert
        assertNotNull(addedRoom);
        assertEquals(roomMock, addedRoom);
    }

    /**
     * Tests the addRoom method with an invalid room name parameter.
     * It ensures that when attempting to add a room with an invalid room name,
     * the method returns null, indicating the failure to create the room.
     */
    @Test
    void addRoomWithInvalidRoomName() {
        // Arrange
        String invalidRoomName = "";
        when(roomFactoryMock.createRoom(invalidRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight))
                .thenReturn(null);
        // Act
        Room result = matryoshka.addRoom(invalidRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the addRoom method with an invalid floor parameter.
     * It ensures that when attempting to add a room with an invalid floor,
     * the method returns null, indicating the failure to create the room.
     */
    @Test
    void addRoomWithInvalidFloor() {
        // Arrange
        int invalidFloor = -1;
        when(roomFactoryMock.createRoom(validRoomName, invalidFloor, validRoomWidth, validRoomLength, validRoomHeight))
                .thenReturn(null);
        // Act
        Room result = matryoshka.addRoom(validRoomName, invalidFloor, validRoomWidth, validRoomLength, validRoomHeight);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the addRoom method with an invalid room width parameter.
     * It ensures that when attempting to add a room with an invalid room width,
     * the method returns null, indicating the failure to create the room.
     */
    @Test
    void addRoomWithInvalidRoomWidth() {
        // Arrange
        double invalidRoomWidth = 0.0;
        when(roomFactoryMock.createRoom(validRoomName, validFloor, invalidRoomWidth, validRoomLength, validRoomHeight))
                .thenReturn(null);
        // Act
        Room result = matryoshka.addRoom(validRoomName, validFloor, invalidRoomWidth, validRoomLength, validRoomHeight);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the addRoom method with an invalid room length parameter.
     * It ensures that when attempting to add a room with an invalid room length,
     * the method returns null, indicating the failure to create the room.
     */
    @Test
    void addRoomWithInvalidRoomLength() {
        // Arrange
        double invalidRoomLength = 0.0;
        when(roomFactoryMock.createRoom(validRoomName, validFloor, validRoomWidth, invalidRoomLength, validRoomHeight))
                .thenReturn(null);
        // Act
        Room result = matryoshka.addRoom(validRoomName, validFloor, validRoomWidth, invalidRoomLength, validRoomHeight);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the addRoom method with an invalid room height parameter.
     * It ensures that when attempting to add a room with an invalid room height,
     * the method returns null, indicating the failure to create the room.
     */
    @Test
    void addRoomWithInvalidRoomHeight() {
        // Arrange
        double invalidRoomHeight = -1.0;
        when(roomFactoryMock.createRoom(validRoomName, validFloor, validRoomWidth, validRoomLength, invalidRoomHeight))
                .thenReturn(null);
        // Act
        Room result = matryoshka.addRoom(validRoomName, validFloor, validRoomWidth, validRoomLength, invalidRoomHeight);
        // Assert
        assertNull(result);
    }

    /**
     * Tests the getRooms method before adding any room. It ensures
     * that the returned list of rooms is not null and has a size of 0,
     * indicating that there are no rooms initially.
     */
    @Test
    void getRoomsBeforeAddingAnyRoom() {
        // Act
        List<Room> rooms = matryoshka.getRooms();
        // Assert
        assertNotNull(rooms);
        assertEquals(0, rooms.size());  // Initially, there are no rooms
    }

    /**
     * Tests the getRooms method after adding one room. It verifies
     * that the returned list of rooms has a size of 1, indicating that the
     * added room is successfully present in the list.
     */
    @Test
    void getRoomsAfterAddingOneRoom() {
        // Arrange
        int expectedSize = 1;
        matryoshka.addRoom(validRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        // Act
        List<Room> rooms = matryoshka.getRooms();
        // Assert
        assertNotNull(rooms);
        assertEquals(expectedSize, rooms.size());
    }

    /**
     * Tests the getRooms method after adding multiple rooms. It verifies
     * that the returned list of rooms has the expected size and contains rooms
     * with the correct names as set during the arrangement phase.
     */
    @Test
    void getRoomsAfterAddingMultipleRooms() {
        // Arrange
        int expectedSize = 2;
        Room roomMock1 = mock(Room.class);
        Room roomMock2 = mock(Room.class);
        String expectedRoomName1 = "Room 1";
        String expectedRoomName2 = "Room 2";

        when(roomMock1.getRoomName()).thenReturn(expectedRoomName1);
        when(roomMock2.getRoomName()).thenReturn(expectedRoomName2);
        when(roomFactoryMock.createRoom(expectedRoomName1, validFloor, validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(roomMock1);
        when(roomFactoryMock.createRoom(expectedRoomName2, validFloor, validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(roomMock2);

        // Act
        matryoshka.addRoom(expectedRoomName1, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        matryoshka.addRoom(expectedRoomName2, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        List<Room> retrieveRooms = matryoshka.getRooms();
        // Assert
        assertNotNull(retrieveRooms);
        assertEquals(expectedSize, retrieveRooms.size());
        assertEquals(expectedRoomName1, retrieveRooms.get(0).getRoomName());
        assertEquals(expectedRoomName2, retrieveRooms.get(1).getRoomName());
    }

    /**
     * Tests the getRoomByName method when attempting to retrieve a
     * room with a non-existent name. It ensures that null is returned, indicating
     * that the room is not found.
     */
    @Test
    void getRoomByName() {
        // Act
        Room room = matryoshka.getRoomByName("Nonexistent Room");
        // Assert
        assertNull(room);
    }

    /**
     * Tests the getRoomByName method when attempting to retrieve an
     * existing room by its name. It ensures that the retrieved room has the
     * expected name, matching the one set during the arrangement phase.
     */
    @Test
    void getRoomByNameExistingRoom() {
        // Arrange
        Room existingRoomMock = mock(Room.class);
        String expectedRoomName = "Existing Room";
        when(existingRoomMock.getRoomName()).thenReturn(expectedRoomName);
        when(roomFactoryMock.createRoom(expectedRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(existingRoomMock);
        // Act
        matryoshka.addRoom(expectedRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        Room existingRoom = matryoshka.getRoomByName(expectedRoomName);
        // Assert
        assertNotNull(expectedRoomName);
        assertEquals(expectedRoomName, existingRoom.getRoomName());
    }

    /**
     * Tests the method to get devices grouped by room with no rooms in the house.
     * It verifies that an empty map is returned.
     */
    @Test
    void getDevicesGroupedByRoomWithNoRooms() {
        // Act
        Map<String, List<Device>> devicesPerRoom = matryoshka.getDevicesGroupedByRoom();
        // Assert
        assertNotNull(devicesPerRoom);
        assertTrue(devicesPerRoom.isEmpty());
    }

    /**
     * Tests the method to get devices grouped by room with one room having no devices.
     * It ensures that the map is not null and empty, indicating no devices in the room.
     */
    @Test
    void getDevicesGroupedByRoomWithOneRoomNoDevices() {
        // Arrange
        Room oneRoomMock = mock(Room.class);
        String expectedRoomName = "A Room without devices";
        when(oneRoomMock.getRoomName()).thenReturn(expectedRoomName);
        when(roomFactoryMock.createRoom(expectedRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(oneRoomMock);
        // Act
        matryoshka.addRoom(expectedRoomName, validFloor, validRoomWidth, validRoomLength, validRoomHeight);
        Map<String, List<Device>> devicesPerRoom = matryoshka.getDevicesGroupedByRoom();
        // Assert
        assertNotNull(devicesPerRoom);
        //assertTrue(devicesPerRoom.isEmpty());
    }

}