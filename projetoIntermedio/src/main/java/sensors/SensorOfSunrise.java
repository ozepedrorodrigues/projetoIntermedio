package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.SunriseValue;
import values.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SensorOfSunrise implements Sensor{

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
    private Value<LocalDateTime> value;


    /**
     * Constructor
     *
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


    public Value<LocalDateTime> getValue() {
        LocalDate dateDate = LocalDate.now();
        return getValue(dateDate);
    }

    /**
     * Returns the value from the sensor.
     *
     * @return the value from the sensor
     */
    public Value<LocalDateTime> getValue(LocalDate date) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(10,0));
        this.value = new SunriseValue(dateTime);
        return this.value;
    }

}
