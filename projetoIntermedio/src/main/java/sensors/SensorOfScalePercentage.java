package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

/**
 * This class represents a scale percentage sensor.
 * It implements the Sensor interface.
 * The sensor has an ID, a type, and a value.
 * The type is always SensorType.SCALE_PERCENTAGE, and the value is a ScalePercentageValue.
 */
public class SensorOfScalePercentage implements Sensor {

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
     * Constructor for the SensorOfScalePercentage class.
     *
     * @param valueFactory the ValueFactory of the sensor.
     * @throws IllegalArgumentException if the valueFactory is invalid.
     */
    public SensorOfScalePercentage(ValueFactory valueFactory) throws IllegalArgumentException {
        if(!isValidValueFactory(valueFactory)){
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.id = 0;
            this.type = SensorType.SCALE_PERCENTAGE;
            this.value = valueFactory.createScalePercentageValue();
        }
    }


    /**
     * Checks if the valueFactory is valid.
     *
     * @param valueFactory the valueFactory to be checked
     * @return true if the valueFactory is valid, false otherwise
     */
    private boolean isValidValueFactory (ValueFactory valueFactory){
        return valueFactory != null;
    }

    /**
     * Returns the type of the sensor.
     *
     * @return the type of the sensor
     */
    @Override
    public SensorType getType() {
        return type;
    }

    /**
     * Returns the current reading from the sensor.
     *
     * @return the current reading from the sensor
     */
    @Override
    public Value getValue() {
        return value;
    }

    /**
     * Alters the current Id of the sensor.
     *
     * @param newId the new ID of the sensor
     * @return the ID of the sensor
     */
    @Override
    public int setId(int newId) {
        id = newId;
        return id;
    }

    /**
     * Returns the ID of the sensor
     *
     * @return the ID of the sensor
     */
    @Override
    public int getId() {
        return id;
    }
}
