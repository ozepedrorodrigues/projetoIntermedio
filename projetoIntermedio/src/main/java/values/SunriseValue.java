package values;

import java.time.LocalDateTime;

public class SunriseValue implements Value{

    private LocalDateTime sunriseValue;

    public SunriseValue(LocalDateTime sunriseValue) {
        this.sunriseValue = sunriseValue;
    }

    public LocalDateTime getValue() {
        return sunriseValue;
    }

    @Override
    public String valueToString() {
        return String.valueOf(sunriseValue);
    }

}
