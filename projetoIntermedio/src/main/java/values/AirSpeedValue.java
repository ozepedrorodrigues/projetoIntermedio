package values;

import values.Value;

public class AirSpeedValue implements Value {
    private double airSpeedValue;

    public AirSpeedValue() {
        this.airSpeedValue = airSpeedValue;
    }

    public double getAirSpeedValue() {
        return airSpeedValue;
    }

    @Override
    public String toValue() {
        return "AirSpeedValue{" +
                "value=" + airSpeedValue +
                "km/h}";}
    }
