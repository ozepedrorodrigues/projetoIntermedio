package domain;

import factories.SensorFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Represents a device with various sensors and functionalities.
 * A device can have a name, type, activation status, and lists of sensors and functionalities.
 */
public class Device {
    private String name;
    private String type;
    private boolean active;
    private List<Sensor> sensorList = new ArrayList<>();
    private HashSet<String> functionalityList = new HashSet<>();
    private SensorFactory sensorFactory;

    /**
     * Constructs a new Device with the specified name and type.
     *
     * @param name The name of the device.
     * @param type The type of the device.
     * @param sensorFactory The sensor factory to be used to create sensors for the device.
     * @throws NullPointerException if name or type or sensorFactory is null.
     * @throws IllegalArgumentException if name or type is empty.
     */
    public Device(String name, String type, SensorFactory sensorFactory) {
        if (!validName(name)||!validType(type)||sensorFactory==null) throw new IllegalArgumentException("Invalid Parameter(s)");
        this.name = name;
        this.type = type;
        this.active = true;
        this.sensorFactory = sensorFactory;
    }

    /**
     * Validates the name of the device.
     * @param name The name of the device.
     * @return true if the name is not null and not empty, false otherwise.
     */
    private boolean validName(String name) {
        return name != null && !name.isEmpty();}

    /**
     * Validates the type of the device.
     * @param type The type of the device.
     * @return true if the type is not null and not empty, false otherwise.
     */
    private boolean validType(String type) {
        return type != null && !type.isEmpty();}

    /**
     * Gets the name of the device.
     *
     * @return The name of the device.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the type of the device.
     *
     * @return The type of the device.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Adds a sensor to the device.
     *
     * @param sensorId The Id of the sensor to be added.
     * @param sensorClassName The type of the sensor to be added.
     * @param catalogue The catalogue from which to retrieve the sensor.
     * @return The added Sensor, or null if the sensor cannot be added.
     */
    public Sensor addSensor(int sensorId, String sensorClassName, Catalogue catalogue) {
        if(!validType(sensorClassName)||catalogue==null) return null;
        Sensor sensor = sensorFactory.createSensor(sensorClassName);
        if(sensor == null) return null;
        int id = sensor.setId(sensorId);
        sensorList.add(sensor);
        if(sensor instanceof SensorOfTemperature)
            functionalityList.add("TEMPERATURE");
        if(sensor instanceof SensorOfHumidity)
            functionalityList.add("HUMIDITY");
        return sensor;
    }

    /**
     * Deactivates the device.
     *
     * @return true if the device was active and has been deactivated, false otherwise.
     */
    public boolean deactivate() {
        if (this.active) {
            this.active = false;
            return true;
        }
        return false;
    }

    /**
     * Gets a list of sensors attached to the device.
     *
     * @return A list of sensors.
     */
    public List<Sensor> getDeviceSensors() {
        return new ArrayList<>(sensorList);
    }

    /**
     * Gets a list of functionalities supported by the device.
     *
     * @return A list of functionalities.
     */
    public List<String> getFunctionalities() {
        return new ArrayList<>(functionalityList);
    }
}
