package factories.implement;
import values.TemperatureValue;
import values.Value;
public class TemperatureValueFactoryImp {
    public TemperatureValueFactoryImp() {}
    public Value createValue() {
        return new TemperatureValue();}}