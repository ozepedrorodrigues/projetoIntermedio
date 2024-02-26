package domain;

import factories.DimensionsFactory;
import factories.GPSFactory;
import factories.LocationFactory;
import factories.RoomFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseTest {
    House validHouse;
    String validAddress = "Rua do Ouro";
    String validZipCode = "1234-567";
    double validLatitude = 1.0;
    double validLongitude = 1.0;
    GPSFactory gpsFactory = mock(GPSFactory.class);
    LocationFactory locationFactory = mock(LocationFactory.class);
    Location location = mock(Location.class);
    RoomFactory roomFactory = mock(RoomFactory.class);
    GPS gps = mock(GPS.class);
    DimensionsFactory dimensionsFactory = mock(DimensionsFactory.class);
    Room room = mock(Room.class);
    Dimensions dimensions = mock(Dimensions.class);
    String validRoomname = "Sala";
    int validRoomfloor = 1;
    double validRoomWidth = 1.0;
    double validRoomLength = 2.0;
    double validRoomHeight = 3.0;

    @BeforeEach
    void setUp() {
        when(roomFactory.createRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(room);
        when(dimensionsFactory.createDimensions(validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(dimensions);
        when(locationFactory.createLocation(validAddress,validZipCode,validLatitude,validLongitude)).thenReturn(location);
        when(location.getAddress()).thenReturn(validAddress);
        when(location.getZipCode()).thenReturn(validZipCode);
        when(location.getGpsLocation()).thenReturn(gps);
        when(gps.getLatitude()).thenReturn(validLatitude);
        when(gps.getLongitude()).thenReturn(validLongitude);
        when(room.getRoomName()).thenReturn(validRoomname);
        //validHouse = new House(validAddress, validZipCode, validLatitude, validLongitude, locationFactory, gpsFactory);
    }

    @Test
    void constructorValidParametersShouldNotThrowException() throws IllegalArgumentException{
        // Arrange Act and Assert
        new House(locationFactory, roomFactory);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorNullLocationFactoryShouldThrowIllegalArgumentException() {
        // Arrange
        String expectedMessage = "Invalid parameters";
        // Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(null, roomFactory));
        // Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorNullRoomFactoryShouldThrowIllegalArgumentException() {
        // Arrange
        String expectedMessage = "Invalid parameters";
        // Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(locationFactory, null));
        // Assert
        assertEquals(expectedMessage, e.getMessage());}



    @Test
    void constructorBorderParametersLongitudeMinus180ShouldNotThrowException() throws IllegalArgumentException{
        when(locationFactory.createLocation(validAddress,validZipCode,validLatitude,-180)).thenReturn(location);
        // Act and Assert
        new House(locationFactory, roomFactory);
        // No exception is thrown, so the test passes
    }

    @Test
    void defineLocationInvalidParametersLatitudeOver90ShouldThrowException(){
        // Arrange
        double latitude = 91.0;
        when(locationFactory.createLocation(validAddress,validZipCode,latitude,validLongitude)).thenThrow(new IllegalArgumentException("Invalid GPS Location"));
        // Act and Assert
        String expectedMessage = "Invalid GPS Location";
        House house = new House(locationFactory, roomFactory);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> house.defineLocation(validAddress, validZipCode, latitude, validLongitude));
        assertEquals(expectedMessage, e.getMessage());}

@Test
void defineLocationEmptyAddressShouldThrowException(){
        // Arrange
        String address = "";
        String expectedMessage = "Invalid parameters";
        when(locationFactory.createLocation(address,validZipCode,validLatitude,validLongitude)).thenThrow(new IllegalArgumentException("Invalid parameters"));
        // Act and Assert
        House house = new House(locationFactory, roomFactory);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> house.defineLocation(address, validZipCode, validLatitude, validLongitude));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomValidInputShouldNotThrowException() {
        //Arrange and Assert
        assertFalse(validHouse.getRooms().contains(room));
        //Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight);
        //Assert
        assertEquals(room1,room);
        assertTrue(validHouse.getRooms().contains(room1));}

    @Test
    void addRoomEmptyNameShouldThrowIllegalArgumentException(){
        //Arrange
        String name = "";
        when(roomFactory.createRoom(name, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight)).thenThrow(new IllegalArgumentException("Empty name"));
        //Act
        String expectedMessage = "Empty name";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(name, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomInvalidWidthShouldThrowIllegalArgumentException(){
        //Arrange
        double width = -1.0;
        when(roomFactory.createRoom(validRoomname, validRoomfloor, width, validRoomLength, validRoomHeight)).thenThrow(new IllegalArgumentException("Invalid dimensions"));
        //Act
        String expectedMessage = "Invalid dimensions";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, width, validRoomLength, validRoomHeight));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomInvalidLengthShouldThrowIllegalArgumentException(){
        //Arrange
        double length = -1.0;
        when(roomFactory.createRoom(validRoomname, validRoomfloor, validRoomWidth, length, validRoomHeight)).thenThrow(new IllegalArgumentException("Invalid dimensions"));
        //Act
        String expectedMessage = "Invalid dimensions";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, length, validRoomHeight));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomInvaLidHeightShouldThrowIllegalArgumentException(){
        //Arrange
        double height = -1.0;
        when(roomFactory.createRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, height)).thenThrow(new IllegalArgumentException("Invalid dimensions"));
        //Act
        String expectedMessage = "Invalid dimensions";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, height));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomNullRoomFactoryShouldThrowIllegalArgumentException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomARoomWithTheSameNameAlreadyExistsShouldReturnNull() {
        //Arrange and Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight);
        Room room2 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight);
        //Assert
        assertNotEquals(room1, room2);
        assertNull(room2);
        assertFalse(validHouse.getRooms().contains(room2));}

    @Test
    void getRoomByNameValidShouldReturnRoom() {
        //Arrange
        when(room.getRoomName()).thenReturn(validRoomname);
        //Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight);
        Room room2 = validHouse.getRoomByName(validRoomname);
        //Assert
        assertEquals(room1, room2);}

    @Test
    void getRoomByNameDoesNotExistShouldReturnNull(){
        //Arrange
        when(room.getRoomName()).thenReturn(validRoomname);
        //Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight);
        Room room2 = validHouse.getRoomByName("Cozinha");
        //Assert
        assertNull(room2);}

    @Test
    void getRoomListEmptyList() {
        //Arrange
        int expectedSize = 0;
        //Act
        int result = validHouse.getRooms().size();
        //Assert
        assertEquals(expectedSize, result);}

    @Test
    void getRoomListOneRoomShouldReturn1(){
        //Arrange and Act
        validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight);
        List<Room> result = validHouse.getRooms();
        //Assert
        assertEquals(1, result.size());}
    @Test
    void getLocation() {
        //Arrange
        Location location1 = validHouse.getLocation();
        //Assert
        assertEquals(location,location1);}
    @Test
    void configLocationValidDataShouldNotThrowException() {}
    @Test
    void getCatalog() {}
    @Test
    void getDevicesGroupedByRoom() {}
}