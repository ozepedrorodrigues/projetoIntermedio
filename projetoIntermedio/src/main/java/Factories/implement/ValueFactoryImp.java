package Factories.implement;

import Domain.HumidityValue;
import Domain.SensorType;
import Domain.TemperatureValue;
import Domain.Value;
import Factories.ValueFactory;

public class ValueFactoryImp implements ValueFactory {
    public ValueFactoryImp() {}

    @Override
    public Value createHumidityValue() {
        return new HumidityValue();
    }
    @Override
    public Value createTemperatureValue() {
        return new TemperatureValue();
    }
}
