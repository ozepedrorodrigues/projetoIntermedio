package values;

import java.time.LocalDateTime;

public class SunsetValue implements Value {
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
     * Retrieves the LocalDateTime of the sunset.
     *
     * @return the LocalDateTime of the sunset.
     */
    public LocalDateTime getSunsetValue() {
        return sunsetValue;
    }

    /**
     * Converts the sunset value to a string.
     *
     * @return the sunset value as a string.
     */
    @Override
    public String valueToString() {
        return String.valueOf(sunsetValue);
    }
}
