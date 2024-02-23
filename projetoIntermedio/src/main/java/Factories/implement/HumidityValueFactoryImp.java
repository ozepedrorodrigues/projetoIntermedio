package Factories.implement;

import Domain.HumidityValue;
import Factories.ValueFactory;

public class HumidityValueFactoryImp  implements ValueFactory {
    public HumidityValueFactoryImp() {
    }
    public HumidityValue createHumidityValue() {
        return new HumidityValue();}
}
