package factories.implement;

import domain.Device;
import factories.ActuatorFactory;
import factories.DeviceFactory;
import factories.SensorFactory;

/**
 * An implementation of the DeviceFactory interface.
 * This class provides a concrete implementation of the createDevice method.
 */

public class DeviceFactoryImp implements DeviceFactory {
    /**
     * The sensorFactory attribute to be instantiated in the constructor and used to create sensors for the device.
     */
    SensorFactory sensorFactory;
    /**
     * The actuatorFactory attribute to be instantiated in the constructor and used to create actuators for the device.
     */
    ActuatorFactory actuatorFactory;
    /**
     * Default constructor for DeviceFactoryImp.
     * @param sensorFactory the factory required to instantiate Sensors
     * @param actuatorFactory the factory required to instantiate Actuators
     */
    public DeviceFactoryImp(SensorFactory sensorFactory, ActuatorFactory actuatorFactory) {
        this.sensorFactory = sensorFactory;
        this.actuatorFactory = actuatorFactory;
    }

    /**
     * Creates a Device object with the specified name and deviceType.
     *
     * @param name       the name of the device
     * @param deviceType the type of the device
     * @return a Device object with the specified name and deviceType
     * @throws IllegalArgumentException if name or deviceType or name is null or empty or any of the factories is null.
     */
    public Device createDevice(String name, String deviceType) {
        return new Device(name, deviceType, sensorFactory, actuatorFactory);}
}
