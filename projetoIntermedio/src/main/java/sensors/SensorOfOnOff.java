package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

public class SensorOfOnOff implements Sensor {

    private int id;
    private SensorType type;
    private Value value;

    public SensorOfOnOff(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.type = SensorType.ON_OFF;
        this.value = valueFactory.createOnOffValue();
    }

    @Override
    public SensorType getType() {
        return null;
    }

    @Override
    public Value getValue() {
        return null;
    }

    @Override
    public int setId(int newId) {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    private boolean validValueFactory (ValueFactory valueFactory){
        return valueFactory != null;
    }
}
