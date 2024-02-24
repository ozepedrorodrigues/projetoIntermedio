package Factories;

import Domain.SensorType;
import Domain.Value;

public interface ValueFactory {
    Value createHumidityValue(SensorType type);
    Value createTemperatureValue(SensorType type);

}
