package values;

public class EnergyConsumptionValue implements Value {


    private double energyConsumptionValue;

    public EnergyConsumptionValue() {
        this.energyConsumptionValue = energyConsumptionValue;
    }

    public double getEnergyConsumptionValue() {
        return energyConsumptionValue;
    }

    @Override
    public String valueToString() {
        return "EnergyConsumptionValue{" + "value=" + energyConsumptionValue + "kWh}";
    }
}
