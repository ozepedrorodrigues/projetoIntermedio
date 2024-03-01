package factories;


import values.Value;

import java.time.LocalDateTime;

public interface ValueFactory {
    Value createTemperatureValue();
    Value createHumidityValue();
    Value createOnOffValue(boolean value);
    Value createScalePercentageValue();
    Value createPowerConsumptionValue();
    Value createSunriseValue(LocalDateTime date);
    Value createSunsetValue(LocalDateTime date);
    Value createWindSpeedValue();
    Value createWindDirectionValue();
    Value createSolarIrradianceValue(double value);
}
