package Factories.implement;

import Domain.Room;
import Factories.DeviceFactory;
import Factories.DimensionsFactory;

public class RoomFactoryImp {
    public RoomFactoryImp() {
    }

    public Room createRoom(String name, int floor, double width, double length, double height, DimensionsFactory dimensionsFactory) {
        DeviceFactory deviceFactory = new DeviceFactoryImp();
        try{return new Room(name, floor, width,length,height,dimensionsFactory);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
    }
}
