package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

public class SensorOfSolarIrradiance {

    private int id;

    private SensorType type;

    private Value value;

    private ValueFactory valueFactory;


    public SensorOfSolarIrradiance(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.SOLAR_IRRADIANCE;
            this.valueFactory = valueFactory;
            this.id = 0;
        }
    }

    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }

    public int getId() {
        return id;
    }

    public int setId (int newId) {
        return id = newId;
    }

    public SensorType getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

}
