package factories;

import domain.Room;

public interface RoomFactory {
    Room createRoom(String name, int floor, double width, double length, double height);
}
