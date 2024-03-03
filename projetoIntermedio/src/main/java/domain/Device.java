package domain;

import actuators.Actuator;
import factories.ActuatorFactory;
import factories.SensorFactory;

import sensors.Sensor;

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
    private List<Sensor> sensors;
    private List<Actuator> actuators;
    private HashSet<String> functionalities;
    private ActuatorFactory actuatorFactory;
    private SensorFactory sensorFactory;

    /**
     * Constructs a new Device with the specified name and type.
     *
     * @param name          The name of the device.
     * @param type          The type of the device.
     * @param sensorFactory The sensor factory to be used to create sensors for the device.
     * @throws NullPointerException     if name or type or sensorFactory is null.
     * @throws IllegalArgumentException if name or type is empty.
     */
    public Device(String name, String type, SensorFactory sensorFactory, ActuatorFactory actuatorFactory) {
        if (!validName(name) || !validType(type) || !validFactories(sensorFactory, actuatorFactory))
            throw new IllegalArgumentException();
        this.name = name;
        this.type = type;
        this.active = true;
        this.sensors = new ArrayList<>();
        this.actuators = new ArrayList<>();
        this.functionalities = new HashSet<>();
        this.sensorFactory = sensorFactory;
        this.actuatorFactory = actuatorFactory;
    }

    /**
     * Validates the name of the device.
     *
     * @param name The name of the device.
     * @return true if the name is not null and not empty, false otherwise.
     */
    private boolean validName(String name) {
        return name != null && !name.isEmpty();
    }

    /**
     * Validates the type of the device.
     *
     * @param type The type of the device.
     * @return true if the type is not null and not empty, false otherwise.
     */
    private boolean validType(String type) {
        return type != null && !type.isEmpty();
    }

    /**
     * Validates the factories of the device.
     *
     * @param sensorFactory   The sensor factory of the device.
     * @param actuatorFactory The actuator factory of the device.
     * @return true if the factories are not null, false otherwise.
     */
    private boolean validFactories(SensorFactory sensorFactory, ActuatorFactory actuatorFactory) {
        return sensorFactory != null && actuatorFactory != null;
    }

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
     * @param sensorClassName The type of the sensor to be added.
     * @param catalogue       The catalogue from which to retrieve the sensor.
     * @return The added Sensor, or null if the sensor cannot be added.
     */
    public Sensor addSensor(String sensorClassName, Catalogue catalogue) {
        if (!validClassName(sensorClassName) || !validCatalogue(catalogue)) {
            return null;
        }

        Sensor sensor = sensorFactory.createSensor(sensorClassName);
        if (sensor == null) {
            return null;
        }
        sensor.setId(IdGenerator.generateSensorId());
        sensors.add(sensor);
        functionalities.add(sensor.getType().getSensorType());
        return sensor;
    }

    public Actuator addActuator(String actuatorClassName, Catalogue catalogue) {
        if (!validClassName(actuatorClassName) || !validCatalogue(catalogue)) {
            return null;
        }

        Actuator actuator = actuatorFactory.createActuator(actuatorClassName);
        if (actuator == null) {
            return null;
        }
        //actuator.setId(IdGenerator.generateActuatorId());
        actuators.add(actuator);
        return actuator;
    }

    private boolean validClassName(String type) {
        return type != null && !type.isEmpty();
    }

    private boolean validCatalogue(Catalogue catalogue) {
        return catalogue != null;
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
        return new ArrayList<>(sensors);
    }

    /**
     * Gets a list of functionalities supported by the device.
     *
     * @return A list of functionalities.
     */
    public HashSet<String> getFunctionalities() {
        return new HashSet<>(functionalities);
    }
}
