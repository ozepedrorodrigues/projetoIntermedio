package sensors;

import domain.SensorType;
import values.SolarIrradianceValue;
import values.Value;

/**
 * This class represents a sensor of solar irradiance.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.SOLAR_IRRADIANCE, and the value is a SolarIrradianceValue.
 * The ID can be set using the setID method.
 */
public class SensorOfSolarIrradiance implements Sensor{

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
     * Sets the ID of the sensor.
     *
     * @param newId The new ID to set.
     * @return the ID of the sensor.
     */
    public int setId(int newId) {
        this.id = newId;
        return id;
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
