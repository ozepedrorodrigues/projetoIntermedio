package Factories;

import Domain.SensorType;
import Domain.Value;

public interface ValueFactory {
    Value createHumidityValue();
    Value createTemperatureValue();

}
