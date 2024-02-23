package Factories;

import Domain.Sensor;

public interface SensorFactory {
    Sensor createHumiditySensor(String name);
    Sensor createTemperatureSensor(String name);
}
