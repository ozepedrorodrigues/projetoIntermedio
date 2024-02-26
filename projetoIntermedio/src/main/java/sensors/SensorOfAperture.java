package sensors;

import domain.SensorType;
import values.Value;
import factories.ValueFactory;

public class SensorOfAperture implements Sensor {

    private int id;

    private SensorType type;

    private Value value;

    public SensorOfAperture(ValueFactory valueFactory) {
        if (!validValueFactory(valueFactory)) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.type = SensorType.APERTURE;
            this.value = valueFactory.createApertureValue();
            this.id = 0;}}

    private boolean validValueFactory(ValueFactory valueFactory) {return valueFactory != null;}

    @Override
    public SensorType getType() {
        return type;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public int setId(int newId) {
        this.id = newId;
        return id;}

    @Override
    public int getId() {
        return id;
    }
}
