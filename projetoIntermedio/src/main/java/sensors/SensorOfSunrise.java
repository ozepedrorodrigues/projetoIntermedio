package sensors;

import domain.SensorType;
import factories.ValueFactory;
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
    private Value value;
    private ValueFactory valueFactory;


    /**
     * Constructor
     *
     * @param valueFactory the ValueFactory of the sensor.
     * @throws IllegalArgumentException if the valueFactory is invalid.
     */
    public SensorOfSunrise(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.SUNRISE;
            this.valueFactory = valueFactory;
            this.id = 0;
        }
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


    public Value getValue() {
        LocalDate dateDate = LocalDate.now();
        return getValue(dateDate);
    }

    /**
     * Returns the value from the sensor.
     *
     * @return the value from the sensor
     */
    public Value getValue(LocalDate date) {
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(10,0));
        this.value = valueFactory.createSunriseValue(dateTime);
        return this.value;
    }

    private boolean validValueFactory(ValueFactory valueFactory) {
        return valueFactory != null;
    }
}
