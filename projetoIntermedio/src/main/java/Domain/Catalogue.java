package Domain;

import java.lang.reflect.InvocationTargetException;
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
    private List<SensorType> sensorTypeList = new ArrayList<>();

    /**
     * A list of sensors.
     */
    private List<String> sensorClassList = new ArrayList<>(Arrays.asList("sensor.SensorOfTemperature", "sensor.SensorOfHumidity"));

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
     * @param sensorType The name of the sensor to add.
     * @return true if the sensor was added successfully, false otherwise.
     */
    public boolean addSensorType(SensorType sensorType) {
        return sensorTypeList.add(sensorType);
    }

    /**
     * Returns the list of sensors in the catalog.
     *
     * @return A list of sensors.
     */
    public List<String> getCatalog() {
        return sensorClassList;
    }

    /**
     * Returns the list of functionalities provided by the sensors in the catalog.
     *
     * @return A list of functionalities.
     */
    public List<SensorType> getSensorTypeList() {
        return sensorTypeList;
    }

    public Sensor getSensor(String sensorClassName) {

        boolean isValidSensorClassName = false;

        for(String catalogSensorClass : sensorClassList) {
            if (sensorClassName.equals(catalogSensorClass)) {
                isValidSensorClassName = true;
                break;
            }
        }

        if(isValidSensorClassName) {
            try {
                Sensor sensor = (Sensor) Class.forName(sensorClassName).getConstructor().newInstance();
                return sensor;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}