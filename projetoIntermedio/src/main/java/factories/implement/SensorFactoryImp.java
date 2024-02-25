package factories.implement;

import domain.Sensor;
import factories.SensorFactory;

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
