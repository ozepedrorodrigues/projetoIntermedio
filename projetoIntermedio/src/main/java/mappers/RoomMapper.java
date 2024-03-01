package mappers;

import dto.RoomDTO;
import domain.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Mapper for RoomDTO.
 * It is used to map a list of rooms to a list of RoomDTO objects.
 */
public class RoomMapper {
    /**
     * Constructs a new MapperToRoomDTO.
     * Create new scratch file from selection
     */
    public RoomMapper() {
    }

    /**
     * Converts a list of rooms to a list of RoomDTO objects.
     *
     * @param roomList the list of rooms to be converted.
     * @return the list of RoomDTOs.
     */
    public List<RoomDTO> getRoomList(List<Room> roomList) {
        List<RoomDTO> roomsDTO = new ArrayList<>(); // Create a new list each time
        for (Room room : roomList) {
            RoomDTO myRoomDTO = roomToDTO(room);
            roomsDTO.add(myRoomDTO);
        }
        return roomsDTO;
    }

    /**
     * Converts a room to a RoomDTO object.
     *
     * @param room the room to be converted.
     * @return the RoomDTO.
     */
    public RoomDTO roomToDTO(Room room) {
        return new RoomDTO(room.getRoomName(), room.getFloor(), room.getDimensions().getWidth(), room.getDimensions().getLength(), room.getDimensions().getHeight());
    }
}
