package factories.implement;

import values.*;
import factories.ValueFactory;

/**
 * Implements the {@link ValueFactory} interface, providing concrete implementations
 * for creating different types of {@link Value} objects. This factory is responsible
 * for instantiating various value types such as HumidityValue, TemperatureValue,
 * ApertureValue, OnOffValue, and AveragePowerValue.
 */
public class ValueFactoryImp implements ValueFactory {

    /**
     * Constructs a new instance of {@code ValueFactoryImp}.
     */
    public ValueFactoryImp() {}

    /**
     * Creates a new {@link HumidityValue} object.
     *
     * @return A new instance of {@link HumidityValue} representing a humidity measurement.
     */
    @Override
    public Value createHumidityValue() {
        return new HumidityValue();
    }

    /**
     * Creates a new {@link TemperatureValue} object.
     *
     * @return A new instance of {@link TemperatureValue} representing a temperature measurement.
     */
    @Override
    public Value createTemperatureValue() {
        return new TemperatureValue();
    }

    /**
     * Creates a new {@link ApertureValue} object.
     *
     * @return A new instance of {@link ApertureValue} representing an aperture setting.
     */
    @Override
    public Value createApertureValue() {
        return new ApertureValue();
    }

    /**
     * Creates a new {@link OnOffValue} object.
     *
     * @return A new instance of {@link OnOffValue} representing an on/off state.
     */
    @Override
    public Value createOnOffValue() {
        return new OnOffValue();
    }

    /**
     * Creates a new {@link AveragePowerValue} object.
     *
     * @return A new instance of {@link AveragePowerValue} representing an average power measurement.
     */
    @Override
    public Value createAveragePowerValue() {
        return new AveragePowerValue();
    }
}
