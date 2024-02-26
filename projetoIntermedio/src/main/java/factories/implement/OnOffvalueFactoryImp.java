package factories.implement;

import values.OnOffValue;
import values.Value;

public class OnOffvalueFactoryImp {
    public OnOffvalueFactoryImp() {}
    public Value createValue() {
        return new OnOffValue();}
}
