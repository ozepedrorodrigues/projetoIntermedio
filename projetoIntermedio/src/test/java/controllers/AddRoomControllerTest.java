package controllers;

import domain.House;
import dto.RoomDTO;
import factories.*;
import factories.implement.*;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the AddRomController class.
 */
class AddRoomControllerTest {

    AddRoomController addRoomController;
    AddRoomController addRoomController2;
    House house;
    RoomMapper roomMapper;
    LocationFactory locationFactory;
    GPSFactory gpsFactory;
    RoomFactory roomFactory;
    double width;
    double length;
    double height;
    DimensionsFactory dimensionsFactory;
    DeviceFactory deviceFactory;
    SensorFactory sensorFactory;
    String filePathName;

    /**
     * Set up the necessary objects for the tests.
     * @throws InstantiationException
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        this.gpsFactory = new GPSFactoryImp();
        this.locationFactory = new LocationFactoryImp(gpsFactory);
        this.dimensionsFactory = new DimensionsFactoryImp();
        this.filePathName = "config.properties";
        this.sensorFactory = new SensorFactoryImp(filePathName);
        deviceFactory = new DeviceFactoryImp(sensorFactory);
        this.roomFactory = new RoomFactoryImp(dimensionsFactory, deviceFactory);
        this.roomMapper = new RoomMapper();
        this.width = 14.3;
        this.length = 15.2;
        this.height = 4.5;
        this.house = new House(locationFactory, roomFactory);
        this.addRoomController = new AddRoomController(house, roomMapper);
        this.addRoomController2 = new AddRoomController(house, roomMapper);
    }

    /**
     * Test the constructor of the AddRomController class with invalid House.
     */
    @Test
    void constructorInvalidHouse() {
        //Arrange
        House invalidHouse = null;
        String expected = "House can not be null.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new AddRoomController(invalidHouse, roomMapper));
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test the constructor of the AddRomController class with invalid MapperToRoomDTO.
     */
    @Test
    void constructorInvalidMapperToRoomDTO() {
        //Arrange
        RoomMapper invalidMapper = null;
        String expected = "House can not be null.";
        //Act + assert
        Exception exception = assertThrows(InstantiationException.class, () -> new AddRoomController(house, invalidMapper));
        assertEquals(expected, exception.getMessage());
    }


    /**
     * Test the addNewRoomToHouse method.
     * Test if the room is added to the house.
     */
    @Test
    void addRoom() {
        //Arrange
        String name = "Gaming Room";
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO (name, floor, width, length, height);
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(newRoomExpected.getName(), newRoomResult.getName());
    }

    /**
     * Test the addNewRoomToHouse method when the room name already exists.
     */
    @Test
    void addRoomSameName() {
        //Arrange
        String name = "Gaming Room";
        int floor = 1;
        RoomDTO newRoom1 = new RoomDTO (name, floor, width, length, height);
        RoomDTO newRoom2 = new RoomDTO (name, floor, width, length, height);
        RoomDTO newRoom2Expected = null;
        int roomListSizeBefore = 0;
        int roomListSizeAfter = 1;
        //Act
        RoomDTO newRoom1Result = addRoomController.addNewRoomToHouse(newRoom1);
        RoomDTO newRoom2Result = addRoomController2.addNewRoomToHouse(newRoom2);
        //Assert
        assertEquals(newRoom2Expected, newRoom2Result);
    }

    /**
     * Test the addNewRoomToHouse method when the room name already exists, ignoring case.
     */
    @Test
    void addRoomSameNameIgnoreCase() {
        //Arrange
        String nameExpected = "Bathroom";
        String name2 = "baThroOm";
        int floor = -1;
        RoomDTO newRoom1 = new RoomDTO (nameExpected, floor, width, length, height);
        RoomDTO newRoom2 = new RoomDTO (name2, floor, width, length, height);
        RoomDTO newRoom2Expected = null;
        int roomListSizeBefore = 0;
        int roomListSizeAfter = 1;
        //Act
        RoomDTO newRoom1Result = addRoomController.addNewRoomToHouse(newRoom1);
        RoomDTO newRoom2Result = addRoomController2.addNewRoomToHouse(newRoom2);
        String nameResult = newRoom1Result.getName();
        //Assert
        assertEquals(newRoom2Expected, newRoom2Result);
        assertEquals(nameExpected, nameResult);
    }

    /**
     * Test the addNewRoomToHouse method when the room name is null.
     */
    @Test
    void addRoomNameNull() {
        //Arrange
        String name = null;
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO (name, floor, width, length, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }

    /**
     * Test the addNewRoomToHouse method when the room name is empty.
     */
    @Test
    void addRoomEmptyName() {
        //Arrange
        String name = "";
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO (name, floor, width, length, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }


}




