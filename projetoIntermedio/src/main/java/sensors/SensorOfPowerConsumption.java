package sensors;

import domain.SensorType;
import values.PowerConsumptionValue;
import values.Value;

public class SensorOfPowerConsumption implements Sensor {

    private SensorType type;
    private int id;
    private Value DEFAULT = new PowerConsumptionValue(1);


    public SensorOfPowerConsumption() {
                   this.type = SensorType.POWER_CONSUMPTION;
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

/**
 *  INCOMPLETE METHOD - IN WORK
 */
//    public PowerConsumptionValue getAveragePower() {
//        int powerCount = 0;
//        int totalPower = 0;
//        if (powerCount == 0) {
//            return new PowerConsumptionValue(0);
//        }
//        return new PowerConsumptionValue(totalPower / powerCount);
//    }
}