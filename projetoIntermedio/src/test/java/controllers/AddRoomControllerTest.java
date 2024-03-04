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
     * @throws InstantiationException if an error occurs during the instantiation of the objects.
     */
    @BeforeEach
    void setUp() throws InstantiationException {
        String filePathName = "config.properties";
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
        assertThrows(InstantiationException.class, () -> new AddRoomController(invalidHouse, roomMapper));
    }

    /**
     * Test the constructor of the AddRomController class with invalid MapperToRoomDTO.
     */
    @Test
    void constructorInvalidMapperToRoomDTO() {
        //Arrange
        RoomMapper invalidMapper = null;
        //Act + assert
        assertThrows(InstantiationException.class, () -> new AddRoomController(house, invalidMapper));
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
        RoomDTO newRoom = new RoomDTO (name, floor, width, length, height);
        RoomDTO newRoomSameName = new RoomDTO (name, floor, width, length, height);
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
        int floor = -1;
        RoomDTO newRoom1 = new RoomDTO (nameExpected, floor, width, length, height);
        RoomDTO newRoom2 = new RoomDTO (name2, floor, width, length, height);
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
        String name = null;
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO (name, floor, width, length, height);
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
        String name = "";
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO (name, floor, width, length, height);
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
        String name = "     ";
        int floor = -1;
        RoomDTO newRoomExpected = new RoomDTO (name, floor, width, length, height);
        RoomDTO roomExpected = null;
        //Act
        RoomDTO newRoomResult = addRoomController.addNewRoomToHouse(newRoomExpected);
        //Assert
        assertEquals(roomExpected, newRoomResult);
    }


}




