package Domain;

import Factories.DeviceFactory;
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
     * The factory to create devices
     */
    private DeviceFactory deviceFactory;

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
     * @param dimensionsFactory   the factory to create dimensions
     * @param deviceFactory       the factory to create devices
     * @throws IllegalArgumentException if input parameters are invalid
     */
    public Room(String name, int floor, double width, double length, double height, DimensionsFactory dimensionsFactory, DeviceFactory deviceFactory) throws IllegalArgumentException {
        if (!validName(name) || !validDimensonsFactory(dimensionsFactory) || !validDeviceFactory(deviceFactory)) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.dimensions = dimensionsFactory.createDimensions(width, length, height);
        this.name = name;
        this.floor = floor;
        this.deviceFactory = deviceFactory;
    }

    /**
     * Getter method to retrieve the name of the room.
     */
    public String getRoomName() {
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
     * Creates a new device and adds it to the room.
     *
     * @param name       the name of the device
     * @param deviceType the type of the device
     * @return the created device
     * @throws IllegalArgumentException if the device is invalid
     */
    public Device createDevice(String name, String deviceType) {
        try {
            Device device = deviceFactory.createDevice(name, deviceType);
            addDevice(device);
            return device;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Method to add a device to room
     *
     * @param device the device to be added
     * @throws IllegalArgumentException if a device with the same name already exists
     */
    private void addDevice(Device device) {
        for (Device devices : deviceList) {
            if (devices.getName().equals(device.getName())) {
                throw new IllegalArgumentException("Device with the same name already exists");
            }
        }
        deviceList.add(device);
    }

    /**
     * Returns a list of all devices in the room.
     *
     * @return a list of all devices in the room
     */
    public List<Device> getDeviceList() {
        List<Device> deviceList = new ArrayList<>();
        for (Device device : this.deviceList)
            deviceList.add(device);
        return deviceList;
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

    /**
     * Checks if the given room name is non-null and not blank.
     *
     * @param name
     * @return true if the name is valid, false otherwise
     */
    private boolean validName(String name) {
        return name != null && !name.isBlank();
    }

    /**
     * Checks if the given dimensionsFactory is non-null.
     *
     * @param dimensionsFactory
     * @return true if the dimensionsFactory is valid, false otherwise
     */
    private boolean validDimensonsFactory(DimensionsFactory dimensionsFactory) {
        return dimensionsFactory != null;
    }

    /**
     * Checks if the given deviceFactory is non-null.
     *
     * @param deviceFactory
     * @return true if the deviceFactory is valid, false otherwise
     */
    private boolean validDeviceFactory(DeviceFactory deviceFactory) {
        return deviceFactory != null;
    }
}