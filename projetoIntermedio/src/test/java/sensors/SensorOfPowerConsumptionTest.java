package sensors;

import domain.SensorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import values.PowerConsumptionValue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

/**
 * Test class for the SensorOfPowerConsumption class.
 */
class SensorOfPowerConsumptionTest {

    /**
     * Test for the constructor of the SensorOfPowerConsumption class.
     * The constructor should set the type of the sensor to POWER_CONSUMPTION.
     * The type of the sensor is then checked to confirm that it was set correctly.
     */
    @Test
    void constructorTestValid() {
        //Arrange and Act
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Assert
        assertEquals(SensorType.POWER_CONSUMPTION, sensor.getType());}

    /**
     * Test for the getId method of the SensorOfPowerConsumption class.
     * The method should return the ID of the sensor.
     */
    @Test
    void getId() {
        //Arrange
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Act
        int id = sensor.getId();
        //Assert
        assertEquals(0, id);}

    /**
     * Test for the setId method of the SensorOfPowerConsumption class.
     * The method should set the ID of the sensor to the given value.
     * The ID of the sensor is then checked to confirm that it was set correctly.
     */
    @Test
    void setId() {
        //Arrange
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Act
        int id = sensor.setId(1);
        //Assert
        assertEquals(1, id);}

    /**
     * Test for the getType method of the SensorOfPowerConsumption class.
     * The method should return the type of the sensor.
     * The type of the sensor is then checked to confirm that it was set correctly.
     */
    @Test
    void getType() {
        //Arrange
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Act
        SensorType type = sensor.getType();
        //Assert
        assertEquals(SensorType.POWER_CONSUMPTION, type);}

    /**
     * Test for the getValue method of the SensorOfPowerConsumption class.
     * The method should return the value of the sensor.
     */
    @Test
    void getValue() {
        //Arrange
        MockedConstruction<PowerConsumptionValue> powerConsumptionValueMockedConstruction = mockConstruction(PowerConsumptionValue.class, (mock, context) -> when(mock.valueToString()).thenReturn("1.0"));
        SensorOfPowerConsumption sensor = new SensorOfPowerConsumption();
        //Act
        List<PowerConsumptionValue> values = powerConsumptionValueMockedConstruction.constructed();
        //Assert
        assertEquals("1.0", values.getFirst().valueToString());
    }
}