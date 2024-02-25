package factories.implement;

import domain.Device;
import factories.DeviceFactory;
import factories.SensorFactory;

/**
 * An implementation of the DeviceFactory interface.
 * This class provides a concrete implementation of the createDevice method.
 */

public class DeviceFactoryImp implements DeviceFactory {
    SensorFactory sensorFactory;
    /**
     * Default constructor for DeviceFactoryImp.
     */
    public DeviceFactoryImp(SensorFactory sensorFactory) {
        this.sensorFactory = sensorFactory;
    }

    /**
     * Creates a Device object with the specified name and deviceType.
     *
     * @param name       the name of the device
     * @param deviceType the type of the device
     * @return a Device object with the specified name and deviceType
     * @throws IllegalArgumentException if the name or deviceType is empty
     * @throws NullPointerException     if the name or deviceType is null
     */
    public Device createDevice(String name, String deviceType) {
        try{return new Device(name, deviceType,sensorFactory);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
        catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());}}
}
