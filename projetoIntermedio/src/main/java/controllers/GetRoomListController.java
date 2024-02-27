package controllers;

import domain.House;
import domain.Room;
import dto.RoomDTO;
import mappers.MapperToRoomDTO;

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
    private MapperToRoomDTO mapperToRoomDTO;

    /**
     * Constructor for ControllerGetRoomList.
     * Initializes the house and mapperToRoomDTO instance.
     */
    public GetRoomListController(House house, MapperToRoomDTO mapperToRoomDTO) throws InstantiationException {
        if(house == null){
            throw new InstantiationException("House can not be null.");
        }
        this.house = house;
        this.mapperToRoomDTO = mapperToRoomDTO;
    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
   public List<RoomDTO> getRoomList() {
        List<Room> roomList = house.getRooms();
        return mapperToRoomDTO.getRoomList(roomList);
    }
}