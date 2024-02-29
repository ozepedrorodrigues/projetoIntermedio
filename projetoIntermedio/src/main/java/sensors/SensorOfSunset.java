package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SensorOfSunset implements Sensor {
    private int id;
    private SensorType type;
    private Value value;
    private ValueFactory valueFactory;

    /**
     * Constructor for the SensorOfSunset class.
     *
     * @param valueFactory the valueFactory of the sensor.
     * @throws IllegalArgumentException if the valueFactory is invalid.
     */
    public SensorOfSunset(ValueFactory valueFactory) throws IllegalArgumentException {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.type = SensorType.SUNSET;
        this.valueFactory = valueFactory;
    }

    /**
     * Retrieves the type of the sensor, which is Sunset.
     *
     * @return The SensorType.SUNSET enumeration representing the sensor type.
     */
    @Override
    public SensorType getType() {
        return this.type;
    }

    /**
     * Retrieves the Sunset value of the current day.
     *
     * @return The Sunset value as a Value object.
     */
    @Override
    public Value getValue() {
        LocalDate dateDate = LocalDate.now();
        return getValue(dateDate);
    }

    /**
     * Retrieves the Sunset value of the specified date.
     *
     * @param date The date to retrieve the Sunset value from.
     * @return The Sunset value as a Value object.
     */
    public Value getValue(LocalDate date) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(18,0));
        this.value = valueFactory.createSunsetValue(dateTime);
        return this.value;
    }

    /**
     * Sets the ID of the sensor to the specified value.
     *
     * @param newId The new ID to set for the sensor.
     * @return The updated ID after the operation.
     */
    @Override
    public int setId(int newId) {
        this.id = newId;
        return this.id;
    }

    /**
     * Retrieves the ID of the sensor.
     *
     * @return the ID of the sensor.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Checks if the provided ValueFactory is not null.
     *
     * @param valueFactory The ValueFactory to validate.
     * @return true if the ValueFactory is not null, false otherwise.
     */
    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }
}
