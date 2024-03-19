package SmartHome.domain.sensors.values;

/**
 * This class represents a solar irradiance value.
 * It implements the Value interface.
 * The value is a double.
 */
public class SolarIrradianceValue implements Value {

    /**
     * The solar irradiance value.
     */
    private double solarIrradianceValue;

    /**
     * Constructor for the SolarIrradianceValue class.
     * with the solar irradiance value initialized to an undefined state.
     *
     * @param solarIrradianceValue The solar irradiance value.
     */
    public SolarIrradianceValue(double solarIrradianceValue) {

        this.solarIrradianceValue = solarIrradianceValue;
    }

    /**
     * Converts the solar irradiance value to a string representation.
     *
     * @return the solar irradiance value as a string.
     */
    @Override
    public String valueToString() {
        return String.valueOf(solarIrradianceValue);
    }

}

