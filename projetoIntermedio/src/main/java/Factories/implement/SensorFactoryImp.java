package Factories.implement;

import Domain.Sensor;
import Domain.SensorOfHumidity;
import Domain.SensorOfTemperature;
import Factories.SensorFactory;
import Factories.ValueFactory;

public class SensorFactoryImp implements SensorFactory {
    public SensorFactoryImp() {}

    // TEMPORARIO!
    @Override
    public Sensor createHumiditySensor(String name) {
        return null;
    }

    // TEMPORARIO!
    @Override
    public Sensor createTemperatureSensor(String name) {
        return null;
    }
/*    @Override
    public Sensor createHumiditySensor(String name) {
        ValueFactory valueFactory = new HumidityValueFactoryImp();
        return new SensorOfHumidity(name,valueFactory);}
    @Override
    public Sensor createTemperatureSensor(String name) {
        ValueFactory valueFactory = new TemperatureValueFactoryImp();
        return new SensorOfTemperature(name,valueFactory);}*/
}
