package controllers;

import domain.House;
import domain.Room;
import dto.RoomDTO;
import mappers.RoomMapper;

/**
 * This class represents the controller for adding a room to a house.
 * It is used to add a room to a house and return the room as a RoomDTO.
 */
public class AddRoomController {

    /**
     * The house instance to which the room is to be added.
     */
    private House house;

    /**
     * The mapperToRoomDTO instance.
     */
    private RoomMapper roomMapper;


    /**
     * Constructs a new AddRomController with the specified house.
     *
     * @param house the house to which the room is to be added
     * @param roomMapper the roomMapper to be used
     * @throws InstantiationException if an error occurs during the instantiation of the objects.
     */
    public AddRoomController(House house, RoomMapper roomMapper) throws InstantiationException {
        if(!isValidConstructorsArguments(house, roomMapper)) {
            throw new InstantiationException();
        }
        this.house = house;
        this.roomMapper = roomMapper;
    }

    /**
     * Adds a new room to the house with the specified name, floor, and dimensions.
     *
     * @param roomDTO the room to be added to the house
     * @return the added room as a RoomDTO, null if the room could not be added
     */
    public RoomDTO addNewRoomToHouse(RoomDTO roomDTO) {
        Room newRoom = house.addRoom(
                roomDTO.getName(), roomDTO.getFloor(),
                roomDTO.getWidth(), roomDTO.getLength(), roomDTO.getHeight()
        );
        if (newRoom == null) {
            return null;
        }
        RoomDTO newRoomDTO = roomMapper.roomToDTO(newRoom);
        return newRoomDTO;
    }

    /**
     * Checks if the arguments passed to the constructor are valid.
     *
     * @param house the house to be checked
     * @return true if the house is not null, false otherwise
     */
    private boolean isValidConstructorsArguments(House house, RoomMapper roomMapper) {
       return house != null && roomMapper != null;
    }


}
