package Factories.implement;

import Domain.Device;
import Factories.DeviceFactory;
import Factories.SensorFactory;

public class DeviceFactoryImp implements DeviceFactory {
    public DeviceFactoryImp() {
    }

    public Device createDevice(String name, String deviceType) {
        try{return new Device(name, deviceType);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
    }
}
