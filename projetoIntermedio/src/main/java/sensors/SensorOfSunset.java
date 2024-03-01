package sensors;

import domain.SensorType;
import values.SunsetValue;
import values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SensorOfSunset implements Sensor {
    /**
     * The ID of the sensor.
     */
    private int id;
    /**
     * The type of the sensor.
     */
    private SensorType type;
    /**
     * The default value of the sensor.
     */
    private Value<LocalDateTime> value;
    /**
     * Constructor for the SensorOfSunset class.
     */
    public SensorOfSunset() {
        this.type = SensorType.SUNSET;
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
     * Retrieves the type of the sensor.
     *
     * @return the type of the sensor.
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
    public Value<LocalDateTime> getValue(LocalDate date) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(10, 0));
        this.value = new SunsetValue(dateTime);
        return this.value;
    }
}
