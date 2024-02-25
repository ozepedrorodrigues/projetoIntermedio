package factories;

import domain.Sensor;

public interface SensorFactory {
    Sensor createHumiditySensor(String name);
    Sensor createTemperatureSensor(String name);
}
