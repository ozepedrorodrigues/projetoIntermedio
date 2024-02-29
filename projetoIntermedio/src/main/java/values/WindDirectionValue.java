package values;

public class WindDirectionValue implements Value {
    private  double windDirection;

    public WindDirectionValue() {
        this.windDirection = 0; // default value
    }
    public double getWindDirection() {
        return windDirection;
    }

    public String valueToString() {
        return String.valueOf(windDirection);
    }
}
