package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

/**
 * Represents a sensor that measures average power consumption. This sensor is
 * designed to work with a specific {@link ValueFactory} to generate the
 * appropriate {@link Value} type for average power consumption measurements.
 */
public class SensorOfAveragePowerConsumption implements Sensor{
    /** The sensor's unique identifier. */
    private int id;

    /** The type of sensor, set to AVERAGE_POWER_CONSUMPTION. */
    private SensorType type;

    /** The factory used to create value objects for this sensor. */
    private ValueFactory valueFactory;

    /** The current value of average power consumption. */
    private Value value;

    /**
     * Constructs a SensorOfAveragePowerConsumption with the specified
     * {@link ValueFactory}. Initializes the sensor's type to
     * AVERAGE_POWER_CONSUMPTION and generates its initial value using the factory.
     *
     * @param valueFactory The factory to create average power consumption values.
     * @throws IllegalArgumentException if the valueFactory is null.
     */
    public SensorOfAveragePowerConsumption(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.AVERAGE_POWER_CONSUMPTION;
            this.valueFactory = valueFactory;
            this.value = valueFactory.createAveragePowerValue();
            this.id = 0;
        }
    }

    /**
     * Validates the provided {@link ValueFactory}.
     *
     * @param valueFactory The factory to validate.
     * @return true if the factory is not null; false otherwise.
     */
    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return The sensor type, AVERAGE_POWER_CONSUMPTION.
     */
    @Override
    public SensorType getType() {
        return SensorType.AVERAGE_POWER_CONSUMPTION;
    }

    /**
     * Returns the current value of the sensor.
     *
     * @return The current average power consumption value.
     */
    @Override
    public Value getValue() {
        return value;
    }

    /**
     * Sets the sensor's unique identifier.
     *
     * @param newId The new identifier for the sensor.
     * @return The updated identifier.
     */
    @Override
    public int setId(int newId) {
        this.id = newId;
        return id;
    }

    /**
     * Returns the sensor's unique identifier.
     *
     * @return The sensor's identifier.
     */
    @Override
    public int getId() {
        return id;
    }
}
