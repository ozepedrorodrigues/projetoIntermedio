package sensors;

import domain.SensorType;
import values.Value;

public class SensorOfAveragePowerConsumption implements Sensor{
    private int id;
    private Value value;

    public SensorOfAveragePowerConsumption(int id, Value value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public SensorType getType() {
        return SensorType.AVERAGE_POWER_CONSUMPTION;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public int setId(int newId) {
        this.id = newId;
        return id;
    }

    @Override
    public int getId() {
        return id;
    }
}
