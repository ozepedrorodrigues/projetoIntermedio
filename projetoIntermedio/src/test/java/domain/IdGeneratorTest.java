package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void generateSensorId() {
        assertEquals(1, IdGenerator.generateSensorId());
        assertEquals(2, IdGenerator.generateSensorId());
        assertEquals(3, IdGenerator.generateSensorId());
    }
}