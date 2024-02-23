package Factories;

import Domain.Device;

public interface DeviceFactory {
    Device createDevice(String name, String deviceType);
}
