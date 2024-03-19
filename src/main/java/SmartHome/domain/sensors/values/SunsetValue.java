package SmartHome.domain.sensors.values;

import java.time.LocalDateTime;
/**
 * Represents a specific value for sunset readings. Instances of this class
 * encapsulate a sunset value and provide methods to retrieve and represent
 * the sunset.
 */
public class SunsetValue implements Value {

    /**
     * The value of the sensor.
     */
    private LocalDateTime sunsetValue;

    /**
     * Constructor for the SunsetValue class.
     *
     * @param sunsetValue the LocalDateTime of the sunset.
     */
    public SunsetValue(LocalDateTime sunsetValue) {
        this.sunsetValue = sunsetValue;
    }

    /**
     * Converts the sunset value to a string representation.
     *
     * @return a string representation of the sunset value.
     */
    @Override
    public String valueToString() {
        return String.valueOf(sunsetValue);
    }
}
