package factories.implement;

import factories.ValueFactory;
import values.*;

import java.time.LocalDateTime;

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
    public ValueFactoryImp() {
    }

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
     * Creates a new {@link OnOffValue} object.
     *
     * @return A new instance of {@link OnOffValue} representing an on/off state.
     */
    @Override
    public Value createOnOffValue(boolean value) {
        return new OnOffValue(value);
    }

    @Override
    public Value createScalePercentageValue() {
        return new ScalePercentageValue();
    }

    @Override
    public Value createPowerConsumptionValue() {
        return new PowerConsumptionValue();
    }

    @Override
    public Value createSunriseValue(LocalDateTime date) {
        return new SunriseValue(date);
    }

    @Override
    public Value createSunsetValue(LocalDateTime date) {
        return new SunsetValue(date);
    }

    @Override
    public Value createWindSpeedValue() {return new WindSpeedValue();}

    @Override
    public Value createWindDirectionValue() {return new WindDirectionValue();}

    @Override
    public Value createSolarIrradianceValue(double value){
        return new SolarIrradianceValue(value);
    }
}

