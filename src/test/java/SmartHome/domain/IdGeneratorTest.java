package SmartHome.domain;

import SmartHome.domain.utilities.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the IdGenerator class.
 */
class IdGeneratorTest {

    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        IdGenerator.resetActuatorId();}

    /**
     * Test of generateSensorId method.
     * The method should return a unique id for each call.
     */
    @Test
    void generateSensorId() {
        //Act and Assert
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
        //Act and Assert
        assertEquals(1, IdGenerator.generateActuatorId());
        assertEquals(2, IdGenerator.generateActuatorId());
        assertEquals(3, IdGenerator.generateActuatorId());
    }
/**
 * Test of resetSensorId method.
 */
    @Test
    void resetSensorId() {
        //Act
        IdGenerator.resetSensorId();
        //Assert
        assertEquals(1, IdGenerator.generateSensorId());
    }

    /**
     * Test of resetActuatorId method.
     */
    @Test
    void resetActuatorId() {
        //Act
        IdGenerator.resetActuatorId();
        //Assert
        assertEquals(1, IdGenerator.generateActuatorId());
    }
}