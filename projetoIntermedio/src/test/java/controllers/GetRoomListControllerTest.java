package controllers;

import domain.Dimensions;
import domain.House;
import domain.Room;
import dto.RoomDTO;
import factories.*;
import factories.implement.*;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetRoomListControllerTest {

    House house;
    Room room1;
    List<Room> rooms;
    Dimensions dimensions;
    GetRoomListController getRoomListController;
    RoomMapper roomMapper;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        roomMapper = new RoomMapper();
        house = mock(House.class);
        room1 = mock(Room.class);
        rooms = new ArrayList<>();
        rooms.add(room1);
        when(house.getRooms()).thenReturn(rooms);
        dimensions = mock(Dimensions.class);
        when(dimensions.getWidth()).thenReturn(10.9);
        when(dimensions.getLength()).thenReturn(10.9);
        when(dimensions.getLength()).thenReturn(10.9);
        when(room1.getDimensions()).thenReturn(dimensions);
        getRoomListController = new GetRoomListController(house, roomMapper);
    }

    /**
     * Test to check if the constructor of the GetRoomListController class is not null.
     */
    @Test
    void constructor() throws InstantiationException {
       //Act
        GetRoomListController result = new GetRoomListController(house, roomMapper);
       //Assert
        assertNotNull(result);

    }

    /**
     * Test to check if the getRoomList method returns an empty list.
     */
    @Test
    void getRoomList_emptyList() throws InstantiationException {
        //Arrange
        House house1 = mock(House.class);
        GetRoomListController getRoomListController = new GetRoomListController(house1, roomMapper);
        int expected = 0;
        //Act
        List<RoomDTO> result = getRoomListController.getRoomList();
        //Assert
        assertEquals(expected, result.size());
    }

    /**
     * Test to check if the getRoomList method returns a list of rooms.
     */
    @Test
    void getRoomList() {
        //Arrange
        int expected = 1;
        //Act
        List<RoomDTO> result = getRoomListController.getRoomList();
        //Assert
        assertEquals(expected, result.size());

    }

    // --------------------Integration----------------------

    GPSFactory gpsFactory;
    LocationFactory locationFactory;
    String filePathName;
    SensorFactory sensorFactory;
    ActuatorFactory actuatorFactory;
    DeviceFactory deviceFactory;
    DimensionsFactory dimensionsFactory;
    RoomFactory roomFactory;
    House house2;
    GetRoomListController getRoomListController1;
    RoomMapper roomMapper1;

    @BeforeEach
    void setUpIntegration() throws InstantiationException {
        roomMapper1 = new RoomMapper();
        gpsFactory = new GPSFactoryImp();
        locationFactory = new LocationFactoryImp(gpsFactory);
        filePathName = "config.properties";
        sensorFactory = new SensorFactoryImp(filePathName);
        actuatorFactory = new ActuatorFactoryImp(filePathName);
        deviceFactory = new DeviceFactoryImp(sensorFactory,actuatorFactory);
        dimensionsFactory = new DimensionsFactoryImp();
        roomFactory = new RoomFactoryImp(dimensionsFactory, deviceFactory);
        house2 = new House(locationFactory, roomFactory);
        getRoomListController1 = new GetRoomListController(house2, roomMapper1);
    }

    /**
     * Test to check if the constructor of the GetRoomListController class is not null.
     */
    @Test
    void constructor_integration() throws InstantiationException {
        //Act
        GetRoomListController result = new GetRoomListController(house2, roomMapper1);
        //Assert
        assertNotNull(result);

    }

    /**
     * Test to check that when the constructor of the GetRoomListController class is null an exception is throw.
     */
    @Test
    void constructor_houseIsNull_integration() {
        //Arrange
        String expected = "House can not be null.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new GetRoomListController(null, roomMapper1));
        assertEquals(expected, exception.getMessage());

    }

    /**
     * Test to check if the getRoomList method returns an empty list.
     */
    @Test
    void getRoomList_emptyList_integration() {
        //Arrange
        int expected = 0;
        //Act
        List<RoomDTO> result = getRoomListController1.getRoomList();
        //Assert
        assertEquals(expected, result.size());
    }

    /**
     * Test to check if the getRoomList method returns a list of rooms.
     */
    @Test
    void getRoomList_integration() {
        //Arrange
        house2.addRoom("roomName", 0, 10, 10, 10);
        int expected = 1;
        //Act
        List<RoomDTO> result = getRoomListController.getRoomList();
        //Assert
        assertEquals(expected, result.size());

    }
}