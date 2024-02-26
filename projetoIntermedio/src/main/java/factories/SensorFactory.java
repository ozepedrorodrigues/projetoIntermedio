package factories;

import sensors.Sensor;

public interface SensorFactory {
    Sensor createSensor(String sensorClassName);
}
