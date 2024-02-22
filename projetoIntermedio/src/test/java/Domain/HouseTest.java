package Domain;

import Factories.DimensionsFactory;
import Factories.GPSLocationFactory;
import Factories.LocationFactory;
import Factories.RoomFactory;
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
    GPSLocationFactory gpsLocationFactoy = mock(GPSLocationFactory.class);
    LocationFactory locationFactory = mock(LocationFactory.class);
    Location location = mock(Location.class);
    RoomFactory roomFactory = mock(RoomFactory.class);
    GPSLocation gpsLocation = mock(GPSLocation.class);
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
        when(roomFactory.createRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, dimensionsFactory)).thenReturn(room);
        when(dimensionsFactory.createDimensions(validRoomWidth, validRoomLength, validRoomHeight)).thenReturn(dimensions);
        when(locationFactory.createLocation(validAddress,validZipCode,validLatitude,validLongitude,gpsLocationFactoy)).thenReturn(location);
        when(location.getAddress()).thenReturn(validAddress);
        when(location.getZipCode()).thenReturn(validZipCode);
        when(location.getGpsLocation()).thenReturn(gpsLocation);
        when(gpsLocation.getLatitude()).thenReturn(validLatitude);
        when(gpsLocation.getLongitude()).thenReturn(validLongitude);
        when(room.getName()).thenReturn(validRoomname);
        validHouse = new House(validAddress, validZipCode, validLatitude, validLongitude, locationFactory, gpsLocationFactoy);
    }

    @Test
    void constructorValidParametersShouldNotThrowException() throws IllegalArgumentException{
        // Arrange Act and Assert
        new House(validAddress, validZipCode, validLatitude, validLongitude, locationFactory, gpsLocationFactoy);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorBorderParametersLatitude90ShouldNotThrowException() throws IllegalArgumentException{
        // Arrange Act and Assert
        new House(validAddress, validZipCode, 90, validLongitude, locationFactory, gpsLocationFactoy);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorBorderParametersLatitudeMinus90ShouldNotThrowException() throws IllegalArgumentException{
        // Arrange
        when(locationFactory.createLocation(validAddress,validZipCode,-90,validLongitude,gpsLocationFactoy)).thenReturn(location);
        // Act and Assert
        new House(validAddress, validZipCode, -90, validLongitude, locationFactory, gpsLocationFactoy);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorBorderParametersLongitude180ShouldNotThrowException() throws IllegalArgumentException{
        // Arrange, Act and Assert
        new House(validAddress, validZipCode, validLatitude, 180, locationFactory, gpsLocationFactoy);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorBorderParametersLongitudeMinus180ShouldNotThrowException() throws IllegalArgumentException{
        when(locationFactory.createLocation(validAddress,validZipCode,validLatitude,-180,gpsLocationFactoy)).thenReturn(location);
        // Act and Assert
        new House(validAddress, validZipCode, validLatitude, -180, locationFactory, gpsLocationFactoy);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorInvalidParametersEmptyAddressShouldThrowException() {
        // Arrange
        String address = "";
        when(locationFactory.createLocation(address,validZipCode,validLatitude,validLongitude,gpsLocationFactoy)).thenThrow(new IllegalArgumentException("Invalid Address or ZipCode"));        // Act and Assert
        //Act
        String expectedMessage = "Invalid Address or ZipCode";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(address, validZipCode, validLatitude, validLongitude, locationFactory, gpsLocationFactoy));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersEmptyZipCodeShouldThrowException() {
        // Arrange
        String zipCode = "";
        when(locationFactory.createLocation(validAddress,zipCode,validLatitude,validLongitude,gpsLocationFactoy)).thenThrow(new IllegalArgumentException("Invalid Address or ZipCode"));
        // Act
        String expectedMessage = "Invalid Address or ZipCode";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, zipCode, validLatitude, validLongitude, locationFactory, gpsLocationFactoy));
        // Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersLatitudeOver90ShouldThrowException(){
        // Arrange
        double latitude = 91.0;
        when(locationFactory.createLocation(validAddress,validZipCode,latitude,validLongitude,gpsLocationFactoy)).thenThrow(new IllegalArgumentException("Invalid GPS Location"));
        // Act and Assert
        String expectedMessage = "Invalid GPS Location";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, validZipCode, latitude, validLongitude, locationFactory, gpsLocationFactoy));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersLatitudeUnderMinus90ShouldThrowException(){
        // Arrange
        double latitude = -91.0;
        when(locationFactory.createLocation(validAddress,validZipCode,latitude,validLongitude,gpsLocationFactoy)).thenThrow(new IllegalArgumentException("Invalid GPS Location"));
        // Act and Assert
        String expectedMessage = "Invalid GPS Location";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, validZipCode, latitude, validLongitude, locationFactory, gpsLocationFactoy));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersLongitudeOver180ShouldThrowException(){
        // Arrange
        double longitude = 181.0;
        when(locationFactory.createLocation(validAddress,validZipCode,validLatitude,longitude,gpsLocationFactoy)).thenThrow(new IllegalArgumentException("Invalid GPS Location"));
        // Act and Assert
        String expectedMessage = "Invalid GPS Location";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, validZipCode, validLatitude, longitude, locationFactory, gpsLocationFactoy));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersLongitudeUnderMinus180ShouldThrowException(){
        // Arrange
        double longitude = -181.0;
        when(locationFactory.createLocation(validAddress,validZipCode,validLatitude,longitude,gpsLocationFactoy)).thenThrow(new IllegalArgumentException("Invalid GPS Location"));
        // Act and Assert
        String expectedMessage = "Invalid GPS Location";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, validZipCode, validLatitude, longitude, locationFactory, gpsLocationFactoy));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersNullvalidAddress(){
        // Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(null, validZipCode, validLatitude, validLongitude, locationFactory, gpsLocationFactoy));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersNullZipCode(){
        // Arrange
        String expectedMessage = "Invalid parameters";
        // Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress,null , validLatitude, validLongitude, locationFactory, gpsLocationFactoy));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersNullLocationFactory(){
        // Arrange
        String expectedMessage = "Invalid parameters";
        // Act and Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, validZipCode, validLatitude, validLongitude, null, gpsLocationFactoy));
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void constructorInvalidParametersNullGPSLocationFactory(){
        // Arrange
        String expectedMessage = "Invalid parameters";
        // Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new House(validAddress, validZipCode, validLatitude, validLongitude, locationFactory, null));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}


    @Test
    void addRoomValidInputShouldNotThrowException() {
        //Arrange and Assert
        assertFalse(validHouse.getRoomList().contains(room));
        //Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory);
        //Assert
        assertEquals(room1,room);
        assertTrue(validHouse.getRoomList().contains(room1));}

    @Test
    void addRoomEmptyNameShouldThrowIllegalArgumentException(){
        //Arrange
        String name = "";
        when(roomFactory.createRoom(name, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, dimensionsFactory)).thenThrow(new IllegalArgumentException("Empty name"));
        //Act
        String expectedMessage = "Empty name";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(name, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomInvalidWidthShouldThrowIllegalArgumentException(){
        //Arrange
        double width = -1.0;
        when(roomFactory.createRoom(validRoomname, validRoomfloor, width, validRoomLength, validRoomHeight, dimensionsFactory)).thenThrow(new IllegalArgumentException("Invalid dimensions"));
        //Act
        String expectedMessage = "Invalid dimensions";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, width, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomInvalidLengthShouldThrowIllegalArgumentException(){
        //Arrange
        double length = -1.0;
        when(roomFactory.createRoom(validRoomname, validRoomfloor, validRoomWidth, length, validRoomHeight, dimensionsFactory)).thenThrow(new IllegalArgumentException("Invalid dimensions"));
        //Act
        String expectedMessage = "Invalid dimensions";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, length, validRoomHeight, roomFactory, dimensionsFactory));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomInvaLidHeightShouldThrowIllegalArgumentException(){
        //Arrange
        double height = -1.0;
        when(roomFactory.createRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, height, dimensionsFactory)).thenThrow(new IllegalArgumentException("Invalid dimensions"));
        //Act
        String expectedMessage = "Invalid dimensions";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, height, roomFactory, dimensionsFactory));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomNullRoomFactoryShouldThrowIllegalArgumentException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, null, dimensionsFactory));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomNullDimensionsFactoryShouldThrowIllegalArgumentException(){
        //Arrange
        String expectedMessage = "Invalid parameters";
        //Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, null));
        //Assert
        assertEquals(expectedMessage, e.getMessage());}

    @Test
    void addRoomARoomWithTheSameNameAlreadyExistsShouldReturnNull() {
        //Arrange and Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory);
        Room room2 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory);
        //Assert
        assertNotEquals(room1, room2);
        assertNull(room2);
        assertFalse(validHouse.getRoomList().contains(room2));}

    @Test
    void getRoomByNameValidShouldReturnRoom() {
        //Arrange
        when(room.getName()).thenReturn(validRoomname);
        //Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory);
        Room room2 = validHouse.getRoomByName(validRoomname);
        //Assert
        assertEquals(room1, room2);}

    @Test
    void getRoomByNameDoesNotExistShouldReturnNull(){
        //Arrange
        when(room.getName()).thenReturn(validRoomname);
        //Act
        Room room1 = validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory);
        Room room2 = validHouse.getRoomByName("Cozinha");
        //Assert
        assertNull(room2);}

    @Test
    void getRoomListEmptyList() {
        //Arrange
        int expectedSize = 0;
        //Act
        int result = validHouse.getRoomList().size();
        //Assert
        assertEquals(expectedSize, result);}

    @Test
    void getRoomListOneRoomShouldReturn1(){
        //Arrange and Act
        validHouse.addRoom(validRoomname, validRoomfloor, validRoomWidth, validRoomLength, validRoomHeight, roomFactory, dimensionsFactory);
        List<Room> result = validHouse.getRoomList();
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