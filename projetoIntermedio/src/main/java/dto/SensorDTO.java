package dto;

import domain.SensorType;

/**
 * This class is a Data Transfer Object (dto) for Sensor.
 * It is used to transfer data about a sensor between processes or layers in an application.
 * The SensorDTO class includes fields for the sensor name and type of sensor.
 */
public class SensorDTO {
    /**
     * The id of the sensor.
     */
    private int sensorId;

    /**
     * The type of the sensor.
     */
    private SensorType sensorType;


    /**
     * Constructs a new SensorDTO with the given sensor name and type of sensor.
     *
     * @param sensorId   the id of the sensor
     * @param typeOfSensor the type of the sensor
     */
    public SensorDTO(int sensorId, SensorType typeOfSensor) {
        this.sensorId = sensorId;
        this.sensorType = typeOfSensor;
    }

    /**
     * Returns the id of the sensor.
     *
     * @return the id of the sensor
     */
    public int getSensorId() {
        return sensorId;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    public SensorType getTypeOfSensor() {
        return sensorType;
    }
}
