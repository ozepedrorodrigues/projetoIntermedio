package Domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a device that contains sensors and actuators.
 * This class models a device by its name, and lists of sensors and actuators.
 * It includes functionality to create deep copies of these lists to ensure
 * that changes in the copies do not affect the original lists.
 */
public class Device {
    /**
     * The name of the device.
     */
    private String name;

    /**
     * The type of the device.
     */
    private String type;

    /**
     * Device is active by default.
     */
    private boolean active;

    /**
     * The list of sensors of the device.
     */
    private List<Sensor> sensorList = new ArrayList<>();

    /**
     * The list of actuators of the device.
     */
    private List<Actuator> actuatorList = new ArrayList<>();

    /**
     * The list of functionalities of the device.
     */
    private HashSet<String> functionalityList = new HashSet<>();

    /**
     * Constructs a new Device with the given name and type.
     * The device is active by default.
     *
     * @param name the name of the device
     * @param type the type of the device
     * @throws IllegalArgumentException if name or type is empty
     */
    public Device(String name, String type) {
        if (name.isEmpty() || type.isEmpty())
            throw new IllegalArgumentException("Empty parameter(s)");
        this.name = name;
        this.type = type;
        this.active = true;
    }

    /**
     * Getter method to retrieve the name of the device.
     *
     * @return the name of the device
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method to retrieve the type of the device.
     *
     * @return the type of the device
     */
    public String getType() {
        return this.type;
    }

    /**
     * Method that adds Sensor to an Existing Device.
     *
     * @param sensorName   name of the sensor
     * @param typeOfSensor type of the sensor
     * @return false if add invalid (empty name, empty type of Sensor, typeOfSensor not valid)
     */
    public boolean addSensor(String sensorName, String typeOfSensor) {
        if (sensorName.isEmpty() || typeOfSensor.isEmpty())
            return false;
        if (typeOfSensor.strip().equalsIgnoreCase("TEMPERATURE")) {
            Sensor temperatureSensor = new SensorOfTemperature(sensorName);
            sensorList.add(temperatureSensor);
            functionalityList.add("TEMPERATURE");
            return true;
        }
        if (typeOfSensor.equalsIgnoreCase("HUMIDITY")) {
            Sensor humidity = new SensorOfHumidity(sensorName);
            sensorList.add(humidity);
            functionalityList.add("HUMIDITY");
            return true;
        }
        return false;
    }

    /**
     * Setter method to deactivate the device.
     * Active by default.
     * (true = active, false = inactive)
     */
    public boolean deactivate() {
        if (this.active) {
            this.active = false;
            return true;
        }
        return false;
    }

    /**
     * Getter method to retrieve the list of sensors of the device.
     */
    public List<Sensor> getDeviceSensors() {
        List<Sensor> sensorListCopy = new ArrayList<>();
        for (Sensor sensor : this.sensorList)
            sensorListCopy.add(sensor);
        return sensorListCopy;
    }

    /**
     * getter for functionality list
     */
    public HashSet<String> getFunctionalities() {
        return functionalityList;
    }
}