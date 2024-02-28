package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

public class SensorOfPowerConsumption implements Sensor {

    private SensorType type;
    private int id;
    private Value value;

    public SensorOfPowerConsumption(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.POWER_CONSUMPTION;
            this.value = valueFactory.createPowerConsumptionValue();
            this.id = 0;
        }
    }

    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int setId(int newId) {
        return id = newId;
    }

    @Override
    public SensorType getType() {
        return type;
    }

    @Override
    public Value getValue() {
        return value;
    }
}
