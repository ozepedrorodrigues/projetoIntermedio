package factories.implement;

import values.ApertureValue;
import values.Value;
import factories.ValueFactory;

public class ApertureValueFactoryImp implements ValueFactory {
    public ApertureValueFactoryImp() {}
    @Override
    public Value createValue() {
        return new ApertureValue();}
}
