package factories;


import values.Value;

public interface ValueFactory {
    Value createTemperatureValue();
    Value createHumidityValue();
    Value createApertureValue();
    Value createOnOffValue();


}
