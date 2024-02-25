package factories.implement;

import domain.HumidityValue;
import domain.TemperatureValue;
import domain.Value;
import factories.ValueFactory;

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
