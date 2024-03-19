package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.SolarIrradianceValue;
import SmartHome.domain.sensors.values.Value;

/**
 * This class represents a sensor of solar irradiance.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.SOLAR_IRRADIANCE, and the value is a SolarIrradianceValue.
 * The ID can be set using the setID method.
 */
public class SensorOfSolarIrradiance implements Sensor {

    /**
     * The ID of the sensor.
     * The ID is unique for each sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     * The type is always SensorType.SOLAR_IRRADIANCE.
     */
    private SensorType type;

    /**
     * The default value of the sensor.
     */
    private Value DEFAULT = new SolarIrradianceValue(1200.0);

    /**
     * Constructor for the SensorOfSolarIrradiance class.
     */
    public SensorOfSolarIrradiance() {
        this.type = SensorType.SOLAR_IRRADIANCE;
    }

    /**
     * @return the ID of the sensor.
     */
    public int getId() {
        return id;
    }

    /**
     * Generates a new ID for this sensor.
     *
     * @return the updated identifier for this sensor.
     */
    @Override
    public int generateId() {
        return id = IdGenerator.generateSensorId();
    }

    /**
     * @return the type of the sensor.
     */
    public SensorType getType() {
        return type;
    }


    /**
     * @return the value of the sensor.
     */
    public Value getValue() {
        return this.DEFAULT;
    }


}
