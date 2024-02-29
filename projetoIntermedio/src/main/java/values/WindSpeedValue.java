package values;

public class WindSpeedValue implements Value {
    private  double windSpeed;

    public WindSpeedValue(double windSpeed) {
        this.windSpeed = windSpeed; // default value
    }
    @Override
    public Double getValue() {
        return windSpeed;
    }

    public String valueToString() {
        return String.valueOf(windSpeed);
    }
}
