package sensors;

import domain.SensorType;
import factories.ValueFactory;
import values.Value;

public class SensorOfScalePercentage implements Sensor {

    private int id;
    private SensorType type;
    private Value value;


    public SensorOfScalePercentage(ValueFactory valueFactory) throws IllegalArgumentException {
        if(!isValidValueFactory(valueFactory)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.value = valueFactory.createScalePercentageValue();
    }

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
     * @param newId
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
