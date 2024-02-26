package factories.implement;


import domain.Catalogue;
import sensors.Sensor;
import factories.SensorFactory;
import factories.ValueFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * An implementation of the SensorFactory interface.
 * This class provides a concrete implementation of the createSensor method.
 */
public class SensorFactoryImp implements SensorFactory {

    /**
     * Catalogue (contains the information about the available sensors and sensorTypes).
     */
    private Catalogue catalogue;

    /**
     * ValueFactory - creates Value instances (parameter of Sensors).
     */
    private ValueFactory valueFactory;

    /**
     * Constructs a new SensorFactoryImp with the specified filePathname and valueFactory.
     *
     * @param filePathname The path of the file containing the available sensors' path.
     * @param valueFactory The value factory to be used to create Value instances for the sensors.
     * @throws InstantiationException if the file containing the catalogue of sensors is not found.
     */
    public SensorFactoryImp(String filePathname,ValueFactory valueFactory) throws InstantiationException {
        catalogue = new Catalogue(filePathname);
        this.valueFactory = valueFactory;
        //filePathname = "config.properties";
    }

    /**
     * Creates a Sensor object with the specified sensorClassName.
     *
     * @param sensorClassName The name of the sensor class.
     * @return a Sensor object with the specified sensorClassName, null if any Exception is thrown
     * during the creation of the sensor and catalogue consultation.
     */
    public Sensor createSensor(String sensorClassName) {
        String sensorClassNamePath = "domain." + sensorClassName;
        boolean isValidSensorClassName = isValidSensorClassName(sensorClassNamePath);

        if(isValidSensorClassName) {
            try {
                return (Sensor) Class.forName(sensorClassNamePath).getConstructor(ValueFactory.class).newInstance(valueFactory);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Validates the sensorClassName.
     * @param sensorClassNamePath The name of the sensor class.
     * @return true if the sensorClassName is valid, false otherwise.
     */
    private boolean isValidSensorClassName(String sensorClassNamePath){
        boolean isValidSensorClassName = false;

        for(String catalogueSensorClass : catalogue.getCatalogue()) {
            if (sensorClassNamePath.equals(catalogueSensorClass)) {
                isValidSensorClassName = true;
                break;
            }
        }

        return isValidSensorClassName;
    }
}
