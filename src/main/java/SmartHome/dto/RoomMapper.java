package SmartHome.dto;

import SmartHome.domain.room.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * The RoomMapper class facilitates the conversion of Room objects to RoomDTO objects,
 * providing mapping functionality for individual rooms and lists of rooms.
 * <p>
 * It offers methods to convert a single Room object to a corresponding RoomDTO,
 * as well as converting a list of Room objects to a list of RoomDTO objects in bulk.
 * </p>
 * <p>
 * This mapper is particularly useful when dealing with data transformations
 * between the domain model (Room) and its data transfer object (RoomDTO) representation.
 * </p>
 */
public class RoomMapper {

    /**
     * Constructs a new RoomMapper.
     * Create new scratch file from selection
     */
    public RoomMapper() {
    }

    /**
     * Converts a single Room object to a RoomDTO object.
     * This method creates a new instance of RoomDTO and sets its properties based on the values
     * from the provided Room object.
     * <p>
     * @param room The Room object to be converted into a DTO.
     * @return RoomDTO A RoomDTO object representing the converted Room with properties set from the room object.
     */
    public RoomDTO roomToDTO(Room room) {
        return new RoomDTO(room.getRoomName(), room.getFloor(), room.getDimensions().getWidth(), room.getDimensions().getLength(), room.getDimensions().getHeight());
    }

    /**
     * Converts a list of Room objects to a list of RoomDTO objects.
     * This method converts a list of Room objects into a list of RoomDTO objects.
     * It iterates over a list of Room objects, converts each Room object
     * into a RoomDTO object using the roomToDTO(Room room) method, and adds them to a new list.
     * <p>
     * This is a utility method useful for converting multiple Room objects into their DTO representation at once.
     * <p>
     * @param rooms The list of Room objects to be converted into DTOs.
     * @return List<RoomDTO> A list of RoomDTO objects corresponding to the Room objects.
     */
    public List<RoomDTO> roomsToDTO(List<Room> rooms) {
        List<RoomDTO> roomsDTO = new ArrayList<>(); // Create a new list each time
        for (Room room : rooms) {
            RoomDTO myRoomDTO = roomToDTO(room);
            roomsDTO.add(myRoomDTO);
        }
        return roomsDTO;
    }
}
