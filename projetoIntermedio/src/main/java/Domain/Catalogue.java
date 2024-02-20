package Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Catalog class is used to manage a list of sensors and their functionalities.
 * It provides methods to add new sensors and retrieve the list of sensors and functionalities.
 */
public class Catalogue {
    /**
     * A list of functionalities provided by the sensors.
     */
    private List<String> functionalities = new ArrayList<>(Arrays.asList("TEMPERATURE", "HUMIDITY"));

    /**
     * A list of sensors.
     */
    private List<String> sensorTypeList = new ArrayList<>(Arrays.asList("sensor.SensorOfTemperature", "sensor.SensorOfHumidity"));

    /**
     * Constructs a new Catalog object.
     */
    public Catalogue() {
        // Catalog of sensors, Empty constructor
    }

    /**
     * Adds a new sensor to the catalog.
     * The sensor name must start with "SensorOf" and must not already exist in the catalog.
     *
     * @param sensor The name of the sensor to add.
     * @return true if the sensor was added successfully, false otherwise.
     */
    public boolean addSensorType(String sensor) {
        if (!sensor.startsWith("SensorOf")) return false;
        for (String s : sensorTypeList)
            if (s.equalsIgnoreCase(sensor) || s.contains(sensor)) return false;
        sensorTypeList.add("sensor." + sensor);
        functionalities.add(sensor.substring("SensorOf".length()).toUpperCase());
        return true;
    }

    /**
     * Returns the list of sensors in the catalog.
     *
     * @return A list of sensors.
     */
    public List<String> getCatalog() {
        return sensorTypeList;
    }

    /**
     * Returns the list of functionalities provided by the sensors in the catalog.
     *
     * @return A list of functionalities.
     */
    public List<String> getFunctionalities() {
        return functionalities;
    }
}