package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

public class SensorOfWindDirection implements Sensor {

    private SensorType type;
    private int id;
    private Value value;
    private ValueFactory valueFactory;

    public SensorOfWindDirection(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.WIND_DIRECTION;
            this.value = valueFactory.createWindDirectionValue();
        }
    }

    public SensorType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int setId(int newId) {
        return id = newId;
    }

    public Value getValue() {
        this.value = valueFactory.createWindDirectionValue();
        return value;
    }

    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }
}

