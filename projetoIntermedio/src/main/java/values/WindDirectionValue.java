package values;

public class WindDirectionValue implements Value {
    private  double windDirection;

    public WindDirectionValue(double windDirection) {
        this.windDirection = windDirection; // default value
    }

    @Override
    public Double getValue() {
        return windDirection;
    }

    public String valueToString() {
        return String.valueOf(windDirection);
    }
}
