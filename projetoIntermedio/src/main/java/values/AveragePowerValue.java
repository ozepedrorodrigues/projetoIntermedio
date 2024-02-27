package values;

public class AveragePowerValue implements Value{
    private double averagePowerValue;
    public AveragePowerValue() {
        this.averagePowerValue = averagePowerValue;}
    public double getAveragePowerValue() {
        return averagePowerValue;}
    @Override
    public String valueToString() {
        return "AveragePowerValue{" + "value=" + averagePowerValue +"W}";}

}
