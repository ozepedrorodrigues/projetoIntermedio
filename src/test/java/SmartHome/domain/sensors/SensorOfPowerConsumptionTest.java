package SmartHome.domain.sensors;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.PowerConsumptionValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

/**
 * Test suite for the {@link SensorOfPowerConsumption} class.
 * Ensures that sensor initialization and its basic functionality work as expected.
 */
class SensorOfPowerConsumptionTest {

    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        IdGenerator.resetActuatorId();}

    /**
     * Tests that creating a new instance of SensorOfPowerConsumption does not throw any exceptions.
     * This validates that the sensor can be instantiated successfully.
     */
    @Test
    void testConstructorValidShouldNotThrowException() {
        //Act and Assert
        assertDoesNotThrow(SensorOfPowerConsumption::new);
    }

    /**
     * Tests that the getType method of SensorOfPowerConsumption returns the correct sensor type.
     * This ensures that the sensor correctly identifies itself as a POWER_CONSUMPTION type sensor.
     */
    @Test
    void getTypeValidShouldReturnSensorType() {
        //Arrange
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Act
        SensorType result = sensor.getType();
        SensorType expected = SensorType.POWER_CONSUMPTION;
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests that the getValue method of SensorOfPowerConsumption returns the expected default value.
     * This ensures that the sensor correctly reports its initial power consumption value.
     */
    @Test
    void getValue() {
        //Arrange
        double expectedValue = 15.0;
        String expectedString = "15.0";
        try (MockedConstruction<PowerConsumptionValue> valueMockedConstruction = mockConstruction(PowerConsumptionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedString);
        })) {
            SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
            //Act
            Value result = sensor.getValue();
            List<PowerConsumptionValue> values = valueMockedConstruction.constructed();
            double resultValue = Double.parseDouble(result.valueToString());
            //Assert
            assertEquals(expectedValue, resultValue);
            assertEquals(expectedString, result.valueToString());
            assertEquals(1, values.size());
        }}

    @Test
    void getValueWithValueToString() {
        //Arrange
        int expectedSize = 1;
        String expectedString = "15.0";
        try (MockedConstruction<PowerConsumptionValue> valueMockedConstruction = mockConstruction(PowerConsumptionValue.class, (mock, context) -> {
            when(mock.valueToString()).thenReturn(expectedString);
        })) {
            SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
            //Act
            Value result = sensor.getValue();
            List<PowerConsumptionValue> values = valueMockedConstruction.constructed();
            String resultString = result.valueToString();
            int resultSize = values.size();
            //Assert
            assertEquals(expectedSize, resultSize);
            assertEquals(expectedString,resultString);}}

    /**
     * Tests that the setId method correctly updates the sensor's ID.
     * This ensures that the sensor's ID can be set and retrieved accurately.
     * This test assumes that the initial ID is 0, and increments by 1.
     */
    @Test
    void testGenerateId() {
        //Arrange
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        int resultInitial = sensor.getId();
        int expectedInitial = 0;
        //Act
        int resultFinal = sensor.generateId(); //assumes initial value of Id 0, and increments by 1
        int expectedFinal = 1;
        //Assert
        assertEquals(expectedInitial, resultInitial);
        assertEquals(expectedFinal, resultFinal);
    }

    /**
     * Tests that the getId method returns the correct initial ID of a new sensor instance.
     * This ensures that the default ID, before any assignment, is as expected.
     */
    @Test
    void getId() {
        //Arrange
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Act
        int resultInitial = sensor.getId();
        int expectedInitial = 0;
        sensor.generateId();
        int resultFinal = sensor.getId();
        int expectedFinal = 1;
        //Assert
        assertEquals(expectedFinal, resultFinal);
        assertEquals(expectedInitial, resultInitial);
        assertNotEquals(resultInitial, resultFinal);}
}
