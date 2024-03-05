package factories.implement;

import domain.Room;
import factories.DeviceFactory;
import factories.DimensionsFactory;
import factories.RoomFactory;

/**
 * This class represents an implementation of the RoomFactory interface.
 * It is used to create instances of the Room class.
 */
public class RoomFactoryImp implements RoomFactory {
    /**
     * dimensionsFactory attirbute to be defined in the constructor as a DimensionsFactory and later
     * used to create Dimensions objects
     */
    private DimensionsFactory dimensionsFactory;
    /**
     * deviceFactory attribute to be defined in the constructor as a DeviceFactory and later
     * used to create Device objects
     */
    private DeviceFactory deviceFactory;

    /**
     * Constructor for the RoomFactoryImp
     * @param dimensionsFactory the DimensionsFactory to be later used to instantiate Dimenions objects
     * @param deviceFactory the DeviceFactory to be later used to instantiate Device objects
     */
    public RoomFactoryImp(DimensionsFactory dimensionsFactory, DeviceFactory deviceFactory) {
        this.dimensionsFactory = dimensionsFactory;
        this.deviceFactory = deviceFactory;
    }

    /**
     * Implementation of the interface method createRoom
     * @param name the name of the Room
     * @param floor the floor of the Room
     * @param width the width of the Dimensions object inside the Room
     * @param length the length of the Dimensions object inside the Room
     * @param height the height of the Dimensions object inside the Room
     * @return new Room if the Room object was sucessfully created
     * @throws IllegalArgumentException if some part of the Room creation process was unsuccessful
     */
    public Room createRoom(String name, int floor, double width, double length, double height) {
            return new Room(name, floor, width, length, height, dimensionsFactory, deviceFactory);}
}
