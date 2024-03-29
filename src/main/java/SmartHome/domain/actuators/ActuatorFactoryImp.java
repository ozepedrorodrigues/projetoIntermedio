package SmartHome.domain.actuators;

import SmartHome.domain.utilities.Catalogue;
import java.lang.reflect.InvocationTargetException;

/**
 * An implementation of the ActuatorFactory interface.
 * This class provides a concrete implementation of the createActuator method.
 */
public class ActuatorFactoryImp implements ActuatorFactory {

    /**
     * Catalogue (contains the information about the available actuators and actuatorTypes).
     */
    private Catalogue catalogue;


    /**
     * Constructs a new ActuatorFactoryImp with the specified filePathname.
     *
     * @param filePathname The path of the file containing the available actuators' path.
     * @throws InstantiationException if the file containing the catalogue of actuators is not found.
     */
    public ActuatorFactoryImp(String filePathname) throws InstantiationException {
        catalogue = new Catalogue(filePathname);
        //filePathname = "config.properties";
    }

    /**
     * Creates a Actuator object with the specified actuatorClassName.
     *
     * @param actuatorClassName The name of the actuator class.
     * @return a Actuator object with the specified actuatorClassName, null if any Exception is thrown
     * during the creation of the actuator and catalogue consultation.
     */
    public Actuator createActuator(String actuatorClassName) {
        String actuatorClassNamePath = "SmartHome.domain.actuators." + actuatorClassName;
        //Tests if there exists an implementation of the Actuator interface with the actuatorClassNamePath
        boolean isValidActuatorClassName = isValidActuatorClassName(actuatorClassNamePath);
        if(isValidActuatorClassName) {
            try {
                //Returns an instantiation of the implementation of The Actuator interface
                return (Actuator) Class.forName(actuatorClassNamePath).getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException | ClassNotFoundException e) {return null;}}
        return null;
    }

    /**
     * Validates the actuatorClassName.
     * @param actuatorClassNamePath The name of the actuator class.
     * @return true if the actuatorClassName is valid, false otherwise.
     */
    private boolean isValidActuatorClassName(String actuatorClassNamePath){
        boolean isValidActuatorClassName = false;
        //Searches through all the Actuator Models (implementations) available in the Catalogue
        for(String catalogueActuatorClass : catalogue.getActuatorsCatalogue()) {
            //If one of them matches the name of the given Actuator returns true
            if (actuatorClassNamePath.equals(catalogueActuatorClass)) {
                isValidActuatorClassName = true;
                break;
            }
        }
        //No matches returns false
        return isValidActuatorClassName;
    }
}
