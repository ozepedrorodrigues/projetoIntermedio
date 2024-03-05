package controllers;


import domain.House;
import dto.RoomDTO;
import factories.*;
import factories.implement.*;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the GetRoomListController class.
 */
class GetRoomListControllerTest {

    /**
     * The gpsFactory attribute to be used in tests.
     */
    GPSFactory gpsFactory;
    /**
     * The locationFactory attribute to be used in tests.
     */
    LocationFactory locationFactory;
    /**
     * The filePathName attribute to be used in tests.
     */
    String filePathName;
    /**
     * The sensorFactory attribute to be used in tests.
     */
    SensorFactory sensorFactory;
    /**
     * The actuatorFactory attribute to be used in tests.
     */
    ActuatorFactory actuatorFactory;
    /**
     * The deviceFactory attribute to be used in tests.
     */
    DeviceFactory deviceFactory;
    /**
     * The dimensionsFactory attribute to be used in tests.
     */
    DimensionsFactory dimensionsFactory;
    /**
     * The roomFactory attribute to be used in tests.
     */
    RoomFactory roomFactory;
    /**
     * The house2 attribute to be used in tests.
     */
    House house2;
    /**
     * The getRoomListController1 attribute to be used in tests.
     */
    GetRoomListController getRoomListController1;
    /**
     * The roomMapper1 attribute to be used in tests.
     */
    RoomMapper roomMapper1;

    /**
     * This method sets up the testing environment before each test.
     */
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
    void constructor() {
        //Act
        GetRoomListController result = new GetRoomListController(house2, roomMapper1);
        //Assert
        assertNotNull(result);

    }

    /**
     * Test to check that when the constructor of the GetRoomListController class is null an exception is throw.
     */
    @Test
    void constructorHouseIsNull() {
        //Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetRoomListController(null, roomMapper1));

    }

    /**
     * Test to check that when the constructor of the GetRoomListController class is null an exception is throw.
     */
    @Test
    void constructorRoomMapperIsNull() {
        //Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetRoomListController(house2, null));

    }

    /**
     * Test to check if the getRooms method returns an empty list.
     */
    @Test
    void getRoomsEmptyList() {
        //Arrange
        int expected = 0;
        //Act
        List<RoomDTO> result = getRoomListController1.getRooms();
        //Assert
        assertEquals(expected, result.size());
    }

    /**
     * Test to check if the getRooms method returns a list of rooms.
     */
    @Test
    void getRooms() {
        //Arrange
        String roomName = "roomName";
        house2.addRoom(roomName, 0, 10, 10, 10);
        int expected = 1;
        //Act
        List<RoomDTO> result = getRoomListController1.getRooms();
        //Assert
        assertEquals(expected, result.size());
        assertEquals(roomName, result.getFirst().getName());

    }
}