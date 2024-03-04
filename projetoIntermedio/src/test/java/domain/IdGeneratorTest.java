package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the IdGenerator class.
 */
class IdGeneratorTest {

    /**
     * Test of generateActuatorId method.
     * The method should return a unique id for each call.
     */
    @Test
    void generateSensorId() {
        assertEquals(1, IdGenerator.generateSensorId());
        assertEquals(2, IdGenerator.generateSensorId());
        assertEquals(3, IdGenerator.generateSensorId());
    }

    /**
     * Test of generateActuatorId method.
     * The method should return a unique id for each call.
     */
    @Test
    void generateActuatorId() {
        assertEquals(1, IdGenerator.generateActuatorId());
        assertEquals(2, IdGenerator.generateActuatorId());
        assertEquals(3, IdGenerator.generateActuatorId());
    }
}