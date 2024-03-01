package domain;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Catalog class is used to manage a list of sensors and available sensor types.
 */
public class Catalogue {
    /**
     * A list of sensor types.
     */
    private List<SensorType> sensorTypes = new ArrayList<>();

    private List<ActuatorType> actuatorTypes = new ArrayList<>();

    /**
     * The list of configured sensors in config.properties file.
     * This is the list of sensors available to be used in runtime.
     */
    private List<String> sensorClassList;

    private List<String> actuatorClassList;

    /**
     * Catalogue constructor.
     * Loads the configurations from a given properties file.
     * @param filePathname the path for the properties file where the available sensors and actuators are configured
     * @throws InstantiationException When the configuration file isn't found, an exception is thrown
     */
    public Catalogue(String filePathname) throws InstantiationException {

        Configurations configurations = new Configurations();

        try {
            Configuration configuration = configurations.properties(new File(filePathname));

            String[] arrayStringClassesSensors = configuration.getStringArray("sensor");
            this.sensorClassList = List.of(arrayStringClassesSensors);

            String[] arrayStringClassesActuators = configuration.getStringArray("actuator");
            this.actuatorClassList = List.of(arrayStringClassesSensors);

        } catch (ConfigurationException e) {
            throw new InstantiationException("Wrong file path name.");
        }

    }

    /**
     * Method to add sensor type to the catalogue
     * @param sensorType The sensor type to add
     * @return The added sensor type. Null if none is added.
     */
    public SensorType addSensorType(SensorType sensorType) {
        for(SensorType sensorType1 : sensorTypes){
            if(sensorType.equals(sensorType1)){
                return null;
            }
        }
        sensorTypes.add(sensorType);
        return sensorType;
    }

    public ActuatorType addActuatorType(ActuatorType actuatorType) {
        for(ActuatorType actuatorType1 : actuatorTypes){
            if(actuatorType.equals(actuatorType1)){
                return null;
            }
        }
        actuatorTypes.add(actuatorType);
        return actuatorType;
    }


    /**
     * Returns the list of sensors in the catalogue.
     *
     * @return immutable list of sensors.
     */
    public List<String> getSensorCatalogue() {
        return List.copyOf(sensorClassList);
    }

    public List<String> getActuatorsCatalogue() {
        return List.copyOf(actuatorClassList);
    }

    /**
     * Returns the available sensor types in the catalogue.
     *
     * @return immutable list of available sensor types in the catalogue.
     */
    public List<SensorType> getSensorTypes() {
        return List.copyOf(sensorTypes);
    }

    public List<ActuatorType> getActuatorTypes() {
        return List.copyOf(actuatorTypes);
    }

}