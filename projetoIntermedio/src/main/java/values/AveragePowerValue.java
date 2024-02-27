package values;

public class AveragePowerValue implements Value{
    private double averagePowerValue;
    public AveragePowerValue() {
        this.averagePowerValue = averagePowerValue;}
    public double getAveragePowerValue() {
        return averagePowerValue;}
    @Override
    public String toValue() {
        return "AveragePowerValue{" + "value=" + averagePowerValue +"W}";}

}
