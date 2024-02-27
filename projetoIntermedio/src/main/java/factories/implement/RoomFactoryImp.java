package factories.implement;

import domain.Room;
import factories.DeviceFactory;
import factories.DimensionsFactory;
import factories.RoomFactory;

public class RoomFactoryImp implements RoomFactory {
    private DimensionsFactory dimensionsFactory;
    private DeviceFactory deviceFactory;

    public RoomFactoryImp(DimensionsFactory dimensionsFactory, DeviceFactory deviceFactory) {
        this.dimensionsFactory = dimensionsFactory;
        this.deviceFactory = deviceFactory;
    }

    public Room createRoom(String name, int floor, double width, double length, double height) {
        try {
            return new Room(name, floor, width, length, height, dimensionsFactory, deviceFactory);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}