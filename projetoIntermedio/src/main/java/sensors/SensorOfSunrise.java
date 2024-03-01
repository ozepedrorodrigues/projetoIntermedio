package sensors;

import domain.SensorType;
import values.SunriseValue;
import values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class represents a sunrise sensor.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.SUNRISE, and the value is a SunriseValue.
 * The ID can be set using the setID method.
 */
public class SensorOfSunrise implements Sensor {

    /**
     * The ID of the sensor.
     */
    private int id;

    /**
     * The type of the sensor.
     */
    private SensorType type;

    /**
     * The value of the sensor.
     */
    private Value value;


    /**
     * Constructor
     */
    public SensorOfSunrise() {
        this.type = SensorType.SUNRISE;
    }

    /**
     * Returns the ID of the sensor.
     *
     * @return the ID of the sensor
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the sensor.
     *
     * @param newId the new ID of the sensor
     */
    public int setId (int newId) {
        this.id = newId;
        return id;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the value from the sensor for today.
     *
     * @return the value from the sensor
     */
    public Value getValue() {
        LocalDate dateDate = LocalDate.now();
        return getValue(dateDate);
    }

    /**
     * Returns the value from the sensor for a specified date.
     *
     * @return the value from the sensor
     */
    public Value getValue(LocalDate date) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(10,0));
        this.value = new SunriseValue(dateTime);
        return this.value;
    }

}
