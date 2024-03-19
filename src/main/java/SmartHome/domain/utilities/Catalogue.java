package SmartHome.domain.utilities;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.List;

/**
 * The Catalog class is used to manage a list of sensors and actuators models.
 */
public class Catalogue {

    /**
     * The list of configured sensors in config.properties file.
     * This is the list of sensor models (as Strings) available to be used in runtime.
     */
    private List<String> sensorClassList;

    /**
     * The list of configured actuators in config.properties file.
     * This is the list of actuator models (as Strings) available to be used in runtime.
     */
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
            this.actuatorClassList = List.of(arrayStringClassesActuators);
        } catch (ConfigurationException e) {
            throw new InstantiationException();
        }

    }

    /**
     * Returns the list of sensors in the catalogue.
     *
     * @return immutable list of sensors.
     */
    public List<String> getSensorsCatalogue() {
        return List.copyOf(sensorClassList);
    }

    /**
     * Returns the list of actuators in the catalogue.
     *
     * @return immutable list of actuators.
     */
    public List<String> getActuatorsCatalogue() {
        return List.copyOf(actuatorClassList);}
}