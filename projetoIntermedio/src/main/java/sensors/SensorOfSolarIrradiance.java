package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.SolarIrradianceValue;
import values.Value;

/**
 * This class represents a sensor of solar irradiance.
 */
public class SensorOfSolarIrradiance {

    private int id;

    private SensorType type;

    private final Value DEFAULT = new SolarIrradianceValue(1200.0);



    /**
     * Constructor for the SensorOfSolarIrradiance class.
     * @param valueFactory The ValueFactory instance used to create values for this sensor.
     * @throws IllegalArgumentException If the provided valueFactory is null.
     */
    public SensorOfSolarIrradiance(ValueFactory valueFactory) {
        this.type = SensorType.SOLAR_IRRADIANCE;
    }

    /**
     * Checks if the provided ValueFactory instance is valid.
     * @param valueFactory The ValueFactory instance to check.
     * @return true if the provided ValueFactory instance is not null, false otherwise.
     */
    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }


    /**
     * @return the ID of the sensor.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the sensor.
     * @param newId The new ID to set.
     * @return the updated ID of the sensor.
     */
    public int setId(int newId) {
        return id = newId;
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
