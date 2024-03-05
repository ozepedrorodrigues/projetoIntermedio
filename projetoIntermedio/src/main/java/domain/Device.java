package domain;

import actuators.Actuator;
import factories.ActuatorFactory;
import factories.SensorFactory;

import sensors.Sensor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Set.copyOf;

/**
 * Represents a device with various sensors and functionalities.
 * A device can have a name, type, activation status, and lists of sensors and functionalities.
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
     * The activation status of the device.
     */
    private boolean active;
    /**
     * The list of sensors attached to the device.
     */
    private List<Sensor> sensors;
    /**
     * The list of actuators attached to the device.
     */
    private List<Actuator> actuators;
    /**
     * The list of functionalities supported by the device.
     */
    private HashSet<String> functionalities;
    /**
     * The factory to be used to create actuators for the device.
     */
    private ActuatorFactory actuatorFactory;
    /**
     * The factory to be used to create sensors for the device.
     */
    private SensorFactory sensorFactory;

    /**
     * Constructs a new Device with the specified name and type.
     *
     * @param name          The name of the device.
     * @param type          The type of the device.
     * @param sensorFactory The sensor factory to be used to create sensors for the device.
     * @param actuatorFactory The actuator factory to be used to create actuators for the device.
     * @throws IllegalArgumentException if any of the parameters is invalid.
     */
    public Device(String name, String type, SensorFactory sensorFactory, ActuatorFactory actuatorFactory) throws IllegalArgumentException {
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
     * Adds a sensor to the device. Creates an Id for the sensor and adds it to the list of sensors.
     * Also adds the sensor's type to the list of functionalities.
     * @param sensorClassName The model of the sensor to be added.
     * @return The added Sensor, or null if the sensor cannot be added.
     */
    public Sensor addSensor(String sensorClassName) {
        if (!validClassName(sensorClassName)) {
            return null;}

        Sensor sensor = sensorFactory.createSensor(sensorClassName);
        if (sensor == null) {
            return null;
        }
        sensor.setId(IdGenerator.generateSensorId());
        sensors.add(sensor);
        functionalities.add(sensor.getType().getSensorType());
        return sensor;
    }

    /**
     * Adds an actuator to the device. Creates an Id for the actuator and adds it to the list of actuators.
     * @param actuatorClassName The model of the actuator to be added.
     * @return The added Actuator, or null if the actuator cannot be added.
     */
    public Actuator addActuator(String actuatorClassName) {
        if (!validClassName(actuatorClassName)) {
            return null;
        }
        Actuator actuator = actuatorFactory.createActuator(actuatorClassName);
        if (actuator == null) {
            return null;
        }
        actuator.setId(IdGenerator.generateActuatorId());
        actuators.add(actuator);
        return actuator;
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
        return List.copyOf(sensors);
    }

    public List<Actuator> getDeviceActuators() {
        return List.copyOf(actuators);
    }

    /**
     * Gets a Collection of functionalities (as an HashSet of Strings) supported by the device.
     *
     * @return an HashSet<String> of functionalities.
     */
    public HashSet<String> getFunctionalities() {
        return new HashSet<>(Set.copyOf(functionalities));}

    /**
     * Validates the class name of the device.
     *
     * @param className The className of the device.
     * @return true if the className is not valid, false otherwise.
     */
    private boolean validClassName(String className) {
        return className != null && !className.isBlank();
    }


    /**
     * Validates the name of the device.
     *
     * @param name The name of the device.
     * @return true if the name is not valid, false otherwise.
     */
    private boolean validName(String name) {
        return name != null && !name.isEmpty();
    }

    /**
     * Validates the type of the device.
     *
     * @param type The type of the device.
     * @return true if the type is valid, false otherwise.
     */
    private boolean validType(String type) {
        return type != null && !type.isEmpty();
    }

    /**
     * Validates the factories of the device.
     *
     * @param sensorFactory   The sensor factory of the device.
     * @param actuatorFactory The actuator factory of the device.
     * @return true if the factories are valid, false otherwise.
     */
    private boolean validFactories(SensorFactory sensorFactory, ActuatorFactory actuatorFactory) {
        return sensorFactory != null && actuatorFactory != null;
    }

}
