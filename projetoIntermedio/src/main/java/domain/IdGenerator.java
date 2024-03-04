package domain;

public class IdGenerator {
    private static int sensorId = 1;
    private static int actuatorId = 1;

    public static int generateSensorId() {
        return sensorId++;
    }
    public static int generateActuatorId() {
        return actuatorId++;
    }

    public static void resetActuatorId() {
        actuatorId = 1;
    }
    public static void resetSensorId() {
        sensorId = 1;
    }
}
