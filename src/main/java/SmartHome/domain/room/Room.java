package SmartHome.domain.room;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.DeviceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in a Smart Home, which is a logical part of a house.
 * A room has a name, house floor, and dimensions. It can be used to manage devices that act as sensors,
 * actuators, or both.
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
     * The factory to create dimensions
     */
    private DimensionsFactory dimensionsFactory;
    /**
     * The factory to create devices
     */
    private DeviceFactory deviceFactory;
    /**
     * The list of devices in the room.
     */
    private List<Device> devices;

    /**
     * Constructor for creating a Room instance with specified name, floor, and dimensions.
     * The room is created with an empty list of devices.
     * The dimensions and device factories are used to create dimensions and devices.
     *
     * @param name the name of the room (must not be null or empty)
     * @param floor the floor of the room
     * @param width the width of the room
     * @param length the length of the room
     * @param height the height of the room
     * @param dimensionsFactory the factory to create dimensions
     * @param deviceFactory the factory to create devices
     * @throws IllegalArgumentException if input parameters are invalid
     */
    public Room(String name, int floor, double width, double length, double height, DimensionsFactory dimensionsFactory, DeviceFactory deviceFactory) throws IllegalArgumentException {
        if (!validName(name) || !validDimensonsFactory(dimensionsFactory) || !validDeviceFactory(deviceFactory)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.floor = floor;
        this.deviceFactory = deviceFactory;
        this.dimensionsFactory = dimensionsFactory;
        this.devices = new ArrayList<>();
        this.dimensions = this.dimensionsFactory.createDimensions(width, length, height);

    }

    /**
     * Getter method to retrieve the name of the room.
     *
     * @return the name of the room
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
     * Adds a new device to the room.
     *
     * @param name the name of the device
     * @param deviceType the type of the device
     * @return the created device or null if the device could not be created
     */
    public Device addNewDevice(String name, String deviceType) {
        if (!validDeviceName(name)) {
            return null;
        }
        try {
            Device device = deviceFactory.createDevice(name, deviceType);
            if (device != null) {
                this.devices.add(device);
            }
            return device;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Returns the devices in the room.
     *
     * @return a list of all devices in the room
     */
    public List<Device> getDevicesInRoom() {
        return List.copyOf(devices);
    }

    /**
     * Returns a device in the room by its name.
     *
     * @param name the name of the device
     * @return the device with the given name, or null if no such device exists
     */
    public Device getDeviceByName(String name) {
        for (Device device : devices) {
            if (device.getName().equals(name))
                return device;}
        return null;
    }

    /**
     * Checks if the given room name is invalid.
     *
     * @param name the name of the room
     * @return true if the name is valid, false otherwise
     */
    private boolean validName(String name) {
        return name != null && !name.isBlank();
    }

    /**
     * Checks if the given dimensionsFactory is invalid.
     *
     * @param dimensionsFactory the factory to create dimensions
     * @return true if the dimensionsFactory is valid, false otherwise
     */
    private boolean validDimensonsFactory(DimensionsFactory dimensionsFactory) {
        return dimensionsFactory != null;
    }

    /**
     * Checks if the given deviceFactory is invalid.
     *
     * @param deviceFactory the factory to create devices
     * @return true if the deviceFactory is valid, false otherwise
     */
    private boolean validDeviceFactory(DeviceFactory deviceFactory) {
        return deviceFactory != null;
    }

    /**
     * Method to check if the device name is valid.
     * @param deviceName the name of the device to be added
     * @return true if the device name is valid, false otherwise
     */
    private boolean validDeviceName(String deviceName) {
        for (Device devices : devices) {
            if (devices.getName().equalsIgnoreCase(deviceName)) {
                return false;}}
        return true;}
}