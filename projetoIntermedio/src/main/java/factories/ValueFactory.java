package factories;

import domain.Value;

public interface ValueFactory {
    Value createHumidityValue();
    Value createTemperatureValue();

}
