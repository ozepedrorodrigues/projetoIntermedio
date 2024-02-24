package Factories.implement;

import Domain.Room;
import Factories.DeviceFactory;
import Factories.DimensionsFactory;
import Factories.RoomFactory;

public class RoomFactoryImp implements RoomFactory {
    public RoomFactoryImp() {
    }

    public Room createRoom(String name, int floor, double width, double length, double height) {
        DeviceFactory deviceFactory = new DeviceFactoryImp();
        DimensionsFactory dimensionsFactory = new DimensionsFactoryImp();
        try {
            return new Room(name, floor, width, length, height, dimensionsFactory, deviceFactory);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
