package Factories;

import Domain.Device;

/**
 * A factory interface for creating Device objects.
 * This interface provides a method to create a Device object with specified name and deviceType.
 */
public interface DeviceFactory {
    /**
     * Creates a Device object with the specified name and deviceType.
     *
     * @param name       the name of the device
     * @param deviceType the type of the device
     * @return a Device object with the specified name and deviceType*/
    Device createDevice(String name, String deviceType);
}
