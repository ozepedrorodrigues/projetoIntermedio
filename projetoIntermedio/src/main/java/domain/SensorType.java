package domain;

/**
 * <p>Represents the type of a sensor along with its measuring unit. This enum
 * defines the different types of sensors, such as temperature and humidity, and
 * associates each type with a specific measuring unit.</p>
 *
 * <p></p>
 */
public enum SensorType {


    TEMPERATURE("Temperature", "ºC"),
    HUMIDITY("Humidity", "%RH"),
    SCALE_PERCENTAGE(   "Scale Percentage", "%"),
    ON_OFF("On/Off", "Boolean"),
    POWER_CONSUMPTION("Power Consumption","W"),
    SUNRISE("Sunrise", "H"),
    SUNSET("Sunset", "H"),
    WIND_SPEED("WindSpeed", "km/h"),
    WIND_DIRECTION("WindDirection", "radian"),
    SOLAR_IRRADIANCE("Solar Irradiance", "W/m²"),
    ENERGY_CONSUMPTION("Energy Consumption", "Wh");


    private final String sensorType;
    private final String measuringUnit;


    SensorType(String sensorType, String measuringUnit) {
        this.sensorType = sensorType;
        this.measuringUnit = measuringUnit;
    }

    /**
     * Gets the type of the sensor as a string.
     *
     * @return The type of the sensor.
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Gets the measuring unit of the sensor as a string.
     *
     * @return The measuring unit of the sensor.
     */
    public String getMeasuringUnit() {
        return measuringUnit;
    }

    /**
     * Gets a combined string of the type and measuring unit.
     *
     * @return A string combining the type and measuring unit.
     */
    public String getTypeAndMeasuringUnit() {
        return sensorType + " (" + measuringUnit + ")";
    }
}
