package dto;

/**
 * This class is a Data Transfer Object (dto) for Sensor.
 * It is used to transfer data about a sensor between processes or layers in an application.
 * The SensorDTO class includes fields for the sensor name and type of sensor.
 */
public class SensorDTO {
    /**
     * The name of the sensor.
     */
    private String sensorName;

    /**
     * The type of the sensor.
     */
    private String sensorType;

    /**
     * Constructs a new SensorDTO with the given sensor name and type of sensor.
     *
     * @param sensorName   the name of the sensor
     * @param typeOfSensor the type of the sensor
     */
    public SensorDTO(String sensorName, String typeOfSensor) {
        this.sensorName = sensorName;
        this.sensorType = typeOfSensor;
    }

    /**
     * Returns the name of the sensor.
     *
     * @return the name of the sensor
     */
    public String getSensorName() {
        return sensorName;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    public String getTypeOfSensor() {
        return sensorType;
    }
}
