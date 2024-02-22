package Factories;

import Domain.Room;

public interface RoomFactory {
    Room createRoom(String name, int floor, double width, double length, double height,DimensionsFactory dimensionsFactory);
}
