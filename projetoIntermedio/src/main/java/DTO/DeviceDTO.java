package DTO;

/**
 * This class is a Data Transfer Object (DTO) for Device.
 * It is used to transfer data about a device between processes or layers in an application.
 * The DeviceDTO class includes fields for the name, type and location of a device.
 */
public class DeviceDTO {
    /**
     * The name of the device.
     */
    private String name;
    /**
     * The type of the device.
     */
    private String type;
    /**
     * The location of the device.
     */
    private String roomName;

    /**
     * Constructs a new DeviceDTO with the given name, type and location.
     *
     * @param name     the name of the device
     * @param type     the type of the device
     * @param roomName the location of the device
     */
    public DeviceDTO(String name, String type, String roomName) {
        this.name = name;
        this.type = type;
        this.roomName = roomName;
    }

    /**
     * Returns the name of the device.
     *
     * @return the name of the device
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the device.
     *
     * @return the type of the device
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the location of the device.
     *
     * @return the location of the device
     */
    public String getRoomName() {
        return roomName;
    }
}
