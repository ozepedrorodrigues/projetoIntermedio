package values;

/**
 * This class represents a solar irradiance value.
 */
public class SolarIrradianceValue implements Value {

    /**
     * The solar irradiance value.
     */
    private double solarIrradianceValue;

    /**
     * Constructor for the SolarIrradianceValue class.
     * @param solarIrradianceValue The solar irradiance value.
     */
    public SolarIrradianceValue(double solarIrradianceValue) {
        this.solarIrradianceValue = solarIrradianceValue;
    }

    /**
     * @return the solar irradiance value.
     */
    public double getValue() {
        return solarIrradianceValue;
    }

    /**
     * @return the solar irradiance value as a string.
     */
    @Override
    public String valueToString() {
        return String.valueOf(solarIrradianceValue);
    }

}

