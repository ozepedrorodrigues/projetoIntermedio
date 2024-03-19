package SmartHome.domain.room;


public interface RoomFactory {
    Room createRoom(String name, int floor, double width, double length, double height);
}
