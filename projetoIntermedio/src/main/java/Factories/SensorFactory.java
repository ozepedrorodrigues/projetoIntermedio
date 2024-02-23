package Factories;

import Domain.Sensor;

public interface SensorFactory {
    Sensor createSensor(String name);
}
