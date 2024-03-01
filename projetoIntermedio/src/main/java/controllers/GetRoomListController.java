package controllers;

import domain.House;
import domain.Room;
import dto.RoomDTO;
import mappers.RoomMapper;

import java.util.List;

/**
 * ControllerGetRoomList is a controller class responsible for fetching the list of rooms in a house.
 * It interacts with the House domain object to fetch the rooms.
 */
public class GetRoomListController {
    /**
     * House instance.
     */
    private House house;
    /**
     * MapperToRoomDTO instance.
     */
    private RoomMapper roomMapper;

    /**
     * Constructor for ControllerGetRoomList.
     * Initializes the house and mapperToRoomDTO instance.
     */
    public GetRoomListController(House house, RoomMapper roomMapper) throws InstantiationException {
        if(house == null){
            throw new InstantiationException("House can not be null.");
        }
        this.house = house;
        this.roomMapper = roomMapper;
    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
   public List<RoomDTO> getRoomList() {
        List<Room> roomList = house.getRooms();
        return roomMapper.getRoomList(roomList);
    }
}