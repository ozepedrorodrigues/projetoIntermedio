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
     * @param house the house to be used
     * @param roomMapper the mapper to be used
     * @throws IllegalArgumentException if the house or mapper are invalid.
     */
    public GetRoomListController(House house, RoomMapper roomMapper) throws IllegalArgumentException {
        if(!validParameters(house,roomMapper)){
            throw new IllegalArgumentException();
        }
        this.house = house;
        this.roomMapper = roomMapper;
    }

    /**
     * Gets the list of rooms in the house.
     *
     * @return the list of rooms in the house.
     */
   public List<RoomDTO> getRooms() {
        List<Room> rooms = house.getRooms();
        return roomMapper.getRooms(rooms);}

    /**
     * Checks if the house and mapperToRoomDTO are valid.
     * @param house the house to be checked
     * @param roomMapper the mapper to be checked
     * @return true if the house and mapperToRoomDTO are valid, false otherwise
     */
    private boolean validParameters(House house, RoomMapper roomMapper) {
        return house != null && roomMapper != null;
    }
}