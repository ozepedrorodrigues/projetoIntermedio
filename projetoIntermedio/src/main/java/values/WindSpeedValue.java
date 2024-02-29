package values;

public class WindSpeedValue implements Value {
    private  double windSpeed;

    public WindSpeedValue() {
        this.windSpeed = 0; // default value
    }
    public double getWindSpeed() {
        return windSpeed;
    }

    public String valueToString() {
        return String.valueOf(windSpeed);
    }
}
