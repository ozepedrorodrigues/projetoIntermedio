package factories.implement;

import values.HumidityValue;
import values.Value;
import factories.ValueFactory;

public class HumidityValueFactoryImp implements ValueFactory {
    public HumidityValueFactoryImp() {}
    @Override
    public Value createValue() {
        return new HumidityValue();}
}
