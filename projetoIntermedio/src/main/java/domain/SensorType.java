package domain;

/**
 * The SensorType enum represents different types of sensors, each associated with a specific measuring unit.
 * It provides methods to retrieve the sensor type, measuring unit, and a combination of both.
 */
public enum SensorType {

    TEMPERATURE("Temperature", "ºC"),
    HUMIDITY("Humidity", "%RH"),
    ON_OFF("On/Off", "Boolean"),
    SCALE_PERCENTAGE(   "Scale Percentage", "%"),
    WIND_SPEED("WindSpeed", "km/h"),
    WIND_DIRECTION("WindDirection", "radian"),
    SUNRISE("Sunrise", "H"),
    SUNSET("Sunset", "H"),
    DEW_POINT("DewPoint", "ºC"),
    SOLAR_IRRADIANCE("Solar Irradiance", "W/m²"),
    POWER_CONSUMPTION("Power Consumption","W"),
    ENERGY_CONSUMPTION("Energy Consumption", "Wh");

    /**
     * This attribute represents the type of the sensor.
     * It holds a string value indicating the category or purpose of the sensor.
     * This attribute is set in the constructor when creating each enum constant.
     * It is used to store the specific type associated with each sensor category.
     */
    private final String sensorType;

    /**
     * This attribute represents the measuring unit associated with the sensor type.
     * It holds a string value indicating the unit of measurement for the sensor's data.
     * This attribute is set in the constructor when creating each enum constant.
     * It is used to store the specific measuring unit corresponding to the sensor type.
     */
    private final String measuringUnit;

    /**
     * Constructs a SensorType enum constant with the specified sensor type and measuring unit.
     *
     * @param sensorType    The type of the sensor.
     * @param measuringUnit The measuring unit associated with the sensor type.
     */
    SensorType(String sensorType, String measuringUnit) {
        this.sensorType = sensorType;
        this.measuringUnit = measuringUnit;
    }

    /**
     * Retrieves the sensor type.
     *
     * @return The sensor type.
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Retrieves the measuring unit associated with the sensor type.
     *
     * @return The measuring unit.
     */
    public String getMeasuringUnit() {
        return measuringUnit;
    }

    /**
     * Retrieves a combined string of the type and measuring unit.
     *
     * @return A string combining the type and measuring unit.
     */
    public String getTypeAndMeasuringUnit() {
        return sensorType + " (" + measuringUnit + ")";
    }
}
