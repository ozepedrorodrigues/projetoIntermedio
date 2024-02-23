package Factories.implement;

import Domain.TemperatureValue;
import Domain.Value;
import Factories.ValueFactory;

public class TemperatureValueFactoryImp implements ValueFactory {
    public TemperatureValueFactoryImp() {}

    @Override
    public Value createHumidityValue() {
        return new TemperatureValue();
    }
}
