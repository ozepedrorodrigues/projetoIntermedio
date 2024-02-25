package factories.implement;

import domain.Catalogue;
import domain.Sensor;
import factories.SensorFactory;
import factories.ValueFactory;

import java.lang.reflect.InvocationTargetException;

public class SensorFactoryImp implements SensorFactory {

    private Catalogue catalogue;
    public SensorFactoryImp(String filePathname) throws InstantiationException {
        catalogue = new Catalogue(filePathname);
        //filePathname = "config.properties";
    }

    public Sensor createSensor(String sensorClassName) {
        String sensorClassNamePath = "domain." + sensorClassName;
        boolean isValidSensorClassName = isValidSensorClassName(sensorClassNamePath);

        if(isValidSensorClassName) {
            try {
                Sensor sensor = (Sensor) Class.forName(sensorClassNamePath).getConstructor(ValueFactory.class).newInstance(new ValueFactoryImp());
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

        for(String catalogueSensorClass : catalogue.getCatalogue()) {
            if (sensorClassNamePath.equals(catalogueSensorClass)) {
                isValidSensorClassName = true;
                break;
            }
        }

        return isValidSensorClassName;
    }
}
