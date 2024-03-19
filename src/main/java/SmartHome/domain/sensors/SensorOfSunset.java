package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.SunsetValue;
import SmartHome.domain.sensors.values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The SensorOfSunset class represents a sensor that detects the sunset time for a given date.
 */
public class SensorOfSunset implements Sensor {

    /**
     * The ID of the sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     */
    private final SensorType type;

    /**
     * The value of the sensor.
     */
    private Value value;

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
     * Generates a new ID for this sensor.
     *
     * @return the updated identifier for this sensor.
     */
    @Override
    public int generateId() {
        return id = IdGenerator.generateSensorId();
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
     * By default, if no date is specified, the current date is used.
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
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(18, 0)); // the default time used for sunset
        this.value = new SunsetValue(dateTime);
        return this.value;
    }
}
