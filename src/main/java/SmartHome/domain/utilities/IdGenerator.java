package SmartHome.domain.utilities;

/**
 * This class is responsible for generating unique IDs for sensors and actuators.
 */
public class IdGenerator {

    /**
     * The current sensor and actuator ID.
     */
    private static int sensorId = 1;
    /**
     * The current actuator ID.
     */
    private static int actuatorId = 1;

    /**
     * Generates a unique sensor ID.
     *
     * @return A unique sensor ID.
     */
    public static int generateSensorId() {
        return sensorId++;
    }

    /**
     * Generates a unique actuator ID.
     *
     * @return A unique actuator ID.
     */
    public static int generateActuatorId() {
        return actuatorId++;
    }

    /**
     * Resets the sensor ID to 1.
     */
    public static void resetActuatorId() {
        actuatorId = 1;
    }

    /*
     * Resets the actuator ID to 1.
     */
    public static void resetSensorId() {
        sensorId = 1;
    }
}
