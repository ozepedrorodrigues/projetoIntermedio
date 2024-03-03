package sensors;

import domain.SensorType;
import values.EnergyConsumptionValue;
import values.Value;

public class SensorOfEnergyConsumption implements Sensor{
    private SensorType type;
    private int id;
    private Value DEFAULT = new EnergyConsumptionValue(1);

    public SensorOfEnergyConsumption() {
        this.type = SensorType.ENERGY_CONSUMPTION;
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
        return this.DEFAULT;
    }
}
