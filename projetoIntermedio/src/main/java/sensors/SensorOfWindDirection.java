package sensors;

import domain.SensorType;
import values.Value;
import values.WindDirectionValue;

public class SensorOfWindDirection implements Sensor {

    private SensorType type;
    private int id;
    private Value DEFAULT = new WindDirectionValue(50.0);

    public SensorOfWindDirection() {
            this.type = SensorType.WIND_DIRECTION;
        }
    public SensorType getType() {
        this.type = SensorType.WIND_DIRECTION;}

    public int getId() {
        return id;
    }

    public int setId(int newId) {
        return id = newId;
    }

    public Value getValue() {
       return this.DEFAULT; // for now is a default value.
    }
}

