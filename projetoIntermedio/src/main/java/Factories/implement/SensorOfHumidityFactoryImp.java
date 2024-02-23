package Factories.implement;

import Domain.SensorOfHumidity;
import Factories.ValueFactory;

public class SensorOfHumidityFactoryImp {
    public SensorOfHumidityFactoryImp() {
    }
    public SensorOfHumidity createSensorOfHumidity(String name) {
        ValueFactory valueFactory = new HumidityValueFactoryImp();
        return new SensorOfHumidity(name,valueFactory);}
}
