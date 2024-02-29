package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;
import values.WindSpeedValue;

public class SensorOfWindSpeed implements Sensor {

    private SensorType type;
    private int id;
    private Value DEFAULT = new WindSpeedValue(25.0);

    public SensorOfWindSpeed(ValueFactory valueFactory) {
        this.type = SensorType.WIND_SPEED;
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
        ;
        return this.DEFAULT;
    }

}

