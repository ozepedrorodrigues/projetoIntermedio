package SmartHome.domain.sensors.values;

import java.time.LocalDateTime;

/**
 * Represents a specific value for sunrise readings. Instances of this class
 * encapsulate a sunrise value and provide methods to retrieve and represent
 * the sunrise.
 */
public class SunriseValue implements Value{

    /**
     * The value of the sensor.
     */
    private LocalDateTime sunriseValue;

    /**
     * Constructs a new instance of the SunriseValue class
     * with the sunrise value initialized to an undefined state.
     *
     * @param sunriseValue the LocalDateTime of the sunrise.
     */
    public SunriseValue(LocalDateTime sunriseValue) {
        this.sunriseValue = sunriseValue;
    }

    /**
     * Converts the sunrise value to a string representation.
     *
     * @return a string representation of the sunrise value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(sunriseValue);
    }
}
