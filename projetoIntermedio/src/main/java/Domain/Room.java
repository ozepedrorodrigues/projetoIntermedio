package Domain;

import Factories.DimensionsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in a Smart Home, which is a logical part of a house with specific functionalities.
 * A room has a name, house floor, and dimensions. It can be used to manage devices that act as sensors,
 * actuators, or both. Devices in a room have the same set of permissions, and the room may have a set of owners.
 */
public class Room {
    /**
     * The name of the room
     */
    private String name;

    /**
     * The floor of the house where the room is located.
     */
    private int floor;

    /**
     * The dimensions of the room
     */
    private Dimensions dimensions;

    /**
     * The list of devices in the room.
     */
    private List<Device> deviceList = new ArrayList<>();

    /**
     * Constructor for creating a Room instance with specified name, floor, and dimensions.
     *
     * @param name                the name of the room (must not be null or empty)
     * @param floor               the floor of the room
     * @param width,length,height the dimensions of the room
     * @throws IllegalArgumentException if input parameters are invalid
     */
    public Room(String name, int floor, double width, double length, double height, DimensionsFactory dimensionsFactory) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Empty name");
        try {
            this.dimensions = dimensionsFactory.createDimensions(width, length, height);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        this.name = name;
        this.floor = floor;
    }

    /**
     * Getter method to retrieve the name of the room.
     */
    public String getName() {

        return this.name;
    }

    /**
     * Getter method to retrieve the floor of the room.
     *
     * @return the floor of the room
     */
    public int getFloor() {

        return this.floor;
    }

    /**
     * Getter method to retrieve the dimensions of the room.
     *
     * @return the dimensions of the room
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Method to add a device to room
     *
     * @return false if a Device with the same name already exists
     */
    private boolean addDevice(Device device) {
        for (Device devices : deviceList)
            if (devices.getName().equals(device.getName()))
                return false;
        deviceList.add(device);
        return true;
    }

    /**
     * Creates a new device and adds it to the room.
     *
     * @param name       the name of the device
     * @param deviceType the type of the device
     * @return true if the device was successfully added, false otherwise
     */
    public boolean createDevice(String name, String deviceType) {
        try {
            Device device = new Device(name, deviceType);
            return addDevice(device);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns a list of all devices in the room.
     *
     * @return a list of all devices in the room
     */
    public List<Device> getDeviceList() {
        List<Device> deviceList1 = new ArrayList<>();
        for (Device device : this.deviceList)
            deviceList1.add(device);
        return deviceList1;
    }

    /**
     * Returns a device in the room by its name.
     *
     * @param name the name of the device
     * @return the device with the given name, or null if no such device exists
     */
    public Device getDeviceByName(String name) {
        for (Device device : deviceList) {
            if (device.getName().equals(name))
                return device;
        }
        return null;
    }
}