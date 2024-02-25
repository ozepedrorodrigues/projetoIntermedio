package factories;

import domain.Sensor;

public interface SensorFactory {
    public Sensor createSensor(String sensorClassName);
}
