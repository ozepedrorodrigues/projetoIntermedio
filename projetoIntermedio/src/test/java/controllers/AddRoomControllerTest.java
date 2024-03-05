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

    /**
     * AddRoomController instance to be used in the tests.
     */
    AddRoomController addRoomController;

    /**
     * The house instance to which the room is to be added.
     */
    House house;

    /**
     * The mapperToRoomDTO instance to be used.
     */
    RoomMapper roomMapper;

    /**
     * Room name.
     */
    String roomName;

    /**
     * Room floor.
     */
    int floor;

    /**
     * Width of the room.
     */
    double width;

    /**
     * Length of the room.
     */
    double length;

    /**
     * Height of the room.
     */
    double height;

    /**
     * Set up the necessary objects for the tests.
     *
     * @throws IllegalArgumentException if an error occurs during the instantiation of the objects.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        String filePathName = "config.properties";
        this.roomName = "Living Room";
        this.floor = 0;
        this.width = 14.3;
        this.length = 15.2;
        this.height = 4.5;
        RoomFactory roomFactory = new RoomFactoryImp(new DimensionsFactoryImp(),
                new DeviceFactoryImp(new SensorFactoryImp(filePathName), new ActuatorFactoryImp(filePathName)));
        this.house = new House(new LocationFactoryImp(new GPSFactoryImp()), roomFactory);

        this.addRoomController = new AddRoomController(house, new RoomMapper());
    }

    /**
     * Test the constructor of the AddRomController class with invalid House.
     */
    @Test
    void constructorInvalidHouse() {
        //Arrange
        House invalidHouse = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddRoomController(invalidHouse, roomMapper));
    }

    /**
     * Test the constructor of the AddRomController class with invalid MapperToRoomDTO.
     */
    @Test
    void constructorInvalidMapperToRoomDTO() {
        //Arrange
        RoomMapper invalidMapper = null;
        //Act + assert
        assertThrows(IllegalArgumentException.class, () -> new AddRoomController(house, invalidMapper));
    }

    /**
     * Test the addNewRoomToHouse when room name is not empty and the floor is a positive number.
     */
    @Test
    void addRoomWithNotEmptyNameAndPositiveNumbersForFloorLocation() {
        //Arrange
        RoomDTO newRoomExpected = new RoomDTO(roomName, floor, width, length, height);
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(newRoomExpected.getName(), newRoomResult.getName());
    }

    /**
     * Test the addNewRoomToHouse method when the room name is not empty and the floor is a positive number.
     */
    @Test
    void addRoomWithFloorEqualsToZero() {
        //Arrange
        int floorZero = 0;
        RoomDTO newRoomExpected = new RoomDTO(roomName, floorZero, width, length, height);
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(newRoomExpected.getName(), newRoomResult.getName());
    }

    /**
     * Test the addNewRoomToHouse method when the room name is not empty and the floor is a positive number.
     */
    @Test
    void addRoomWithFloorNegative() {
        //Arrange
        int floorNegative = -1;
        RoomDTO newRoomExpected = new RoomDTO(roomName, floorNegative, width, length, height);
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
        RoomDTO newRoom = new RoomDTO(roomName, floor, width, length, height);
        RoomDTO newRoomSameName = new RoomDTO(roomName, floor, width, length, height);
        int roomListSizeBefore = 1;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoom);
        RoomDTO newRoom2Result = addRoomController.addNewRoomToHouse(newRoomSameName);
        int roomListSizeAfter = house.getRooms().size();
        //Assert
        assertNull(newRoom2Result);
        assertEquals(roomListSizeBefore, roomListSizeAfter);
    }

    /**
     * Test the addNewRoomToHouse method when the room name already exists, ignoring case.
     */
    @Test
    void addRoomSameNameIgnoreCase() {
        //Arrange
        String nameExpected = "Bathroom";
        String name2 = "baThroOm";
        RoomDTO newRoom1 = new RoomDTO(nameExpected, floor, width, length, height);
        RoomDTO newRoom2 = new RoomDTO(name2, floor, width, length, height);
        RoomDTO newRoom2Expected = null;
        int roomListSizeBefore = 0;
        int roomListSizeAfter = 1;
        //Act
        RoomDTO newRoom1Result = addRoomController.addNewRoomToHouse(newRoom1);
        RoomDTO newRoom2Result = addRoomController.addNewRoomToHouse(newRoom2);
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
        String invalidName = null;
        RoomDTO newRoomExpected = new RoomDTO(invalidName, floor, width, length, height);
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertNull(newRoomResult);
    }

    /**
     * Test the addNewRoomToHouse method when the room name is empty.
     */
    @Test
    void addRoomEmptyName() {
        //Arrange
        String invalidName = "";
        RoomDTO newRoomExpected = new RoomDTO(invalidName, floor, width, length, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }

    /**
     * Test the addNewRoomToHouse method when the room name is blank.
     */
    @Test
    void addRoomBlankName() {
        //Arrange
        String invalidName = "     ";
        RoomDTO newRoomExpected = new RoomDTO(invalidName, floor, width, length, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }

    /**
     * Test the addNewRoomToHouse method when the width is a negative number.
     */
    @Test
    void addRoomNegativeWidth() {
        //Arrange
        double invalidWidth = -1;
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO(roomName, floor, invalidWidth, length, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }

    /**
     * Test the addNewRoomToHouse method when the length is a negative number.
     */
    @Test
    void addRoomNegativeLength() {
        //Arrange
        double negativeLength = -1;
        RoomDTO newRoomExpected = new RoomDTO(roomName, floor, width, negativeLength, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }

    /**
     * Test the addNewRoomToHouse method when the height is a negative number.
     */
    @Test
    void addRoomNegativeHeight() {
        //Arrange
        double negativeHeight = -1;
        RoomDTO newRoomExpected = new RoomDTO(roomName, floor, width, length, negativeHeight);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }


}




