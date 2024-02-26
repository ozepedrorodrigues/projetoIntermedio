package controllers;

import domain.Dimensions;
import domain.House;
import domain.Room;
import dto.RoomDTO;
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

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    void setUp() {
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
        getRoomListController = new GetRoomListController(house);
    }

    /**
     * Test to check if the constructor of the GetRoomListController class is not null.
     */
    @Test
    void constructor() {
       //Act
        GetRoomListController result = new GetRoomListController(house);
       //Assert
        assertNotNull(result);

    }

    /**
     * Test to check if the getRoomList method returns an empty list.
     */
    @Test
    void getRoomList_emptyList() {
        //Arrange
        House house1 = mock(House.class);
        GetRoomListController getRoomListController = new GetRoomListController(house1);
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
}