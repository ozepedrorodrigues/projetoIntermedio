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
        String nameResult = house.getRooms().getFirst().getRoomName(); // The identifier of the room is the name
        //Assert
        assertEquals(newRoomExpected.getName(), newRoomResult.getName());
        assertEquals(roomName, nameResult);
    }

    /**
     * Test the addNewRoomToHouse method when the room name is not empty and the floor is a positive number.
     * Add multiple rooms to the house.
     * The rooms should be added to the house in the order they are added.
     * The identifier of the room is the name.
     */
    @Test
    void addRoomMultipleAdds() {
        //Arrange
        String roomName2 = "Gaming Room";
        String roomName3 = "Kitchen";
        RoomDTO newRoom1 = new RoomDTO(roomName, floor, width, length, height);
        RoomDTO newRoom2 = new RoomDTO(roomName2, floor, width, length, height);
        RoomDTO newRoom3 = new RoomDTO(roomName3, floor, width, length, height);
        int listSizeBefore = 0;
        int listSizeAfter = 3;

        //Act
        int listSizeBeforeResult = house.getRooms().size();
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoom1);
        RoomDTO newRoom2Result = addRoomController.addNewRoomToHouse(newRoom2);
        RoomDTO newRoom3Result = addRoomController.addNewRoomToHouse(newRoom3);
        String nameResult1 = house.getRooms().getFirst().getRoomName(); // The identifier of the room is the name
        String nameResult2 = house.getRooms().get(1).getRoomName();
        String nameResult3 = house.getRooms().getLast().getRoomName();
        int listSizeAfterResult2 = house.getRooms().size();

        //Assert
        assertEquals(roomName, nameResult1);
        assertEquals(roomName2, nameResult2);
        assertEquals(roomName3, nameResult3);
        assertEquals(listSizeBefore, listSizeBeforeResult);
        assertEquals(listSizeAfter, listSizeAfterResult2);
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
        String nameResult = house.getRooms().getFirst().getRoomName(); // The identifier of the room is the name
        //Assert
        assertEquals(newRoomExpected.getName(), newRoomResult.getName());
        assertEquals(roomName, nameResult);
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
        String nameResult = house.getRooms().getFirst().getRoomName(); // The identifier of the room is the name
        //Assert
        assertEquals(roomName, nameResult);
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
        String nameResult = house.getRooms().getFirst().getRoomName(); // The identifier of the room is the name
        //Assert
        assertNull(newRoom2Result);
        assertEquals(roomListSizeBefore, roomListSizeAfter);
        assertEquals(roomName, nameResult);
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
        String nameResult = house.getRooms().getFirst().getRoomName(); // The identifier of the room is the name
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




