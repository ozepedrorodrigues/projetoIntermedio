package Factories.implement;

import Domain.Sensor;
import Domain.SensorOfTemperature;
import Factories.SensorFactory;
import Factories.ValueFactory;

public class SensorOfTemperatureFactoryImp implements SensorFactory {
    public SensorOfTemperatureFactoryImp() {
    }
    public SensorOfTemperature createSensor(String name) {
        ValueFactory valueFactory = new TemperatureValueFactoryImp();
        return new SensorOfTemperature(name,valueFactory);
    }
}
