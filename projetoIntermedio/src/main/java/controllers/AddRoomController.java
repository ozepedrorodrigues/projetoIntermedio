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
     */
    public AddRoomController(House house, RoomMapper roomMapper) throws InstantiationException {
        if(!isValidConstructorsArguments(house, roomMapper)) {
            throw new InstantiationException("House can not be null.");
        }
        this.house = house;
        this.roomMapper = roomMapper;
    }

    /**
     * Checks if the arguments passed to the constructor are valid.
     *
     * @param house the house to be checked
     * @return true if the house is not null, false otherwise
     */
    private boolean isValidConstructorsArguments(House house, RoomMapper roomMapper) {
        if (house == null || roomMapper == null) {
            return false;
        }
        return true;
    }

    /**
     * Adds a new room to the house with the specified name, floor, and dimensions.
     *
     * @param roomDTO the room to be added
     * @return the added room as a RoomDTO
     */
    public RoomDTO addNewRoomToHouse(RoomDTO roomDTO) {
        Room newRoom = house.addRoom(
                roomDTO.getName(), roomDTO.getFloor(),
                roomDTO.getWidth(), roomDTO.getLength(), roomDTO.getHeight());

        RoomDTO newRoomDTO;
        if (newRoom == null) {
            newRoomDTO = null;
            return newRoomDTO;
        } else {
            newRoomDTO = roomMapper.roomToDTO(newRoom);
            return newRoomDTO;
        }
    }


}
