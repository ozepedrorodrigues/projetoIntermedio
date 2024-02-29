package factories;


import values.Value;

import java.time.LocalDateTime;

public interface ValueFactory {
    Value createTemperatureValue();
    Value createHumidityValue();
    Value createApertureValue();
    Value createOnOffValue(boolean value);
    Value createAveragePowerValue();
    Value createScalePercentageValue();
    Value createEnergyConsumptionValue();
    Value createPowerConsumptionValue();
    Value createSunriseValue(LocalDateTime date);
    Value createSunsetValue(LocalDateTime date);
    Value createWindSpeedValue();
    Value createWindDirectionValue();
    Value createSolarIrradianceValue();
}
