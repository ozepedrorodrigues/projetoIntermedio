package mappers;

import dto.SensorDTO;
import sensors.Sensor;

import java.util.List;

/**
 * This class is a Mapper for SensorDTO.
 * It is used to map a list of sensors to a SensorDTO object.
 */
public class MapperSensorDTO {

    /**
     * Constructs a new MapperSensorDTO.
     * The constructor is empty because it is a Mapper.
     */
    public MapperSensorDTO() {
    }


    /**
     * This method maps a Sensor object to a SensorDTO object.
     * It retrieves the id and type of the sensor and uses them to create a new SensorDTO object.
     * @param sensor a Sensor object to be mapped to a SensorDTO object.
     * @return a SensorDTO object that contains the id and type of the sensor.
     */
    public SensorDTO getSensorDTO(Sensor sensor) {
        return new SensorDTO(
                sensor.getId(),
                sensor.getType());

    }
}
