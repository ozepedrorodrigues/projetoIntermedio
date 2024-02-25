package controllers;

import domain.House;
import factories.DimensionsFactory;
import factories.RoomFactory;
import factories.implement.DimensionsFactoryImp;

/**
 * ControllerUS02 is responsible for adding a new room to a house.
 * It validates the input parameters and creates a new Room object.
 * The Room object is then added to the House object.
 */
public class AddRomController {
    /**
     * The house instance to which the room is to be added.
     */
    private House house;
    private DimensionsFactoryImp dimensions;
    private RoomFactory roomFactory;

    private DimensionsFactory dimensionsFactory;


    /**
     * Constructor for ControllerUC02.
     */
    public AddRomController(House house, RoomFactory roomFactory, DimensionsFactory dimensionsFactory) {
        this.house = house;
        this.roomFactory = roomFactory;
        this.dimensionsFactory = dimensionsFactory;
    }

    /**
     * Adds a new room to a house.
     *
     * @param roomDTO the data transfer object containing the details of the room to be added
     * @return true if the room is successfully added to the house, false otherwise
     */
/*    public RoomDTO addNewRoomToHouse(RoomDTO roomDTO) {
        Room room = house.addRoom(roomDTO.getName(), roomDTO.getFloor(), roomDTO.getWidth(), roomDTO.getLength(), roomDTO.getHeight(),roomFactory,dimensionsFactory);
        return new MapperToRoomDTO().roomToDTO(room);
    }*/
}
