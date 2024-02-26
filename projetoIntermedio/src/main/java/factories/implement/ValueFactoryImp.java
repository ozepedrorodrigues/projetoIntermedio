package factories.implement;

import domain.HumidityValue;
import domain.TemperatureValue;
import values.OnOffValue;
import values.Value;
import factories.ValueFactory;
import values.ApertureValue;

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
    @Override
    public Value createApertureValue() { return new ApertureValue();}
    @Override
    public Value createOnOffValue() { return new OnOffValue();}

}
