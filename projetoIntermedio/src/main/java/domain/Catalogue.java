package domain;

import factories.implement.ValueFactoryImp;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    private List<String> sensorClassList;

    /**
     * Constructs a new Catalog object.
     */
    public Catalogue() throws InstantiationException {
        String filePathname = "config.properties";

        Configurations configurations = new Configurations();

        try {
            Configuration configuration = configurations.properties(new File(filePathname));

            String[] arrayStringClassesSensors = configuration.getStringArray("sensor");
            this.sensorClassList = List.of(arrayStringClassesSensors);

        } catch (ConfigurationException e) {
            throw new InstantiationException("something went wrong in reading the configuration: " + e.getMessage());
        }

    }

    /**
     * Adds a new sensor to the catalog.
     * The sensor name must start with "SensorOf" and must not already exist in the catalog.
     *
     * @param sensorType The name of the sensor to add.
     * @return true if the sensor was added successfully, false otherwise.
     */
    public SensorType addSensorType(SensorType sensorType) {
        for(SensorType sensorType1 : sensorTypeList){
            if(sensorType.equals(sensorType1)){
                return null;
            }
        }
        sensorTypeList.add(sensorType);
        return sensorType;
    }


    /**
     * Returns the list of sensors in the catalog.
     *
     * @return A list of sensors.
     */
    public List<String> getCatalogue() {
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
        String sensorClassNamePath = "domain." + sensorClassName;
        boolean isValidSensorClassName = isValidSensorClassName(sensorClassNamePath);

        if(isValidSensorClassName) {
            try {
                Sensor sensor = (Sensor) Class.forName(sensorClassNamePath).getConstructor(ValueFactoryImp.class).newInstance(new ValueFactoryImp());
                return sensor;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                return null;
            }
        }

        return null;
    }

    private boolean isValidSensorClassName(String sensorClassNamePath){
        boolean isValidSensorClassName = false;

        for(String catalogueSensorClass : sensorClassList) {
            if (sensorClassNamePath.equals(catalogueSensorClass)) {
                isValidSensorClassName = true;
                break;
            }
        }

        return isValidSensorClassName;
    }

}