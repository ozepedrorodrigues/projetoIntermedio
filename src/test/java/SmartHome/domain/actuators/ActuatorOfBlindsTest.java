package SmartHome.domain.actuators;

import SmartHome.domain.utilities.IdGenerator;
import SmartHome.domain.sensors.values.ScalePercentageValue;
import SmartHome.domain.sensors.values.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The ActuatorOfBlindsManagementTest class is responsible for testing the methods of the ActuatorOfBlindsManagement class.
 */
public class ActuatorOfBlindsTest {

    Value value;
    Value invalidValue;

    @BeforeEach
    void setUp() {
        String expected = "50.0";
        String invalidExpected = "150";
        value = mock(ScalePercentageValue.class);
        invalidValue = mock(ScalePercentageValue.class);
        when(invalidValue.valueToString()).thenReturn(invalidExpected);
        when(value.valueToString()).thenReturn(expected);
        IdGenerator.resetActuatorId();
        IdGenerator.resetSensorId();
    }

    /**
     * Test of constructor of ActuatorOfBlinds class, of class ActuatorOfBlinds.
     */
    @Test
    void constructorOfActuatorNoExceptionisThrown() {
        //Act and Assert
        assertDoesNotThrow(ActuatorOfBlinds::new);}

    /**
     * Test of operate method, of class ActuatorOfBlinds.
     * This test checks if the method operate returns a value.
     * The method operate should return a value, not a null, as the value is valid.
     */
    @Test
    void testOperate() {
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        String expected = "50.0";
        //Act
        Value result = instance.operate(value);
        String resultString = result.valueToString();
        //Assert
        assertNotNull(result);
        assertInstanceOf(ScalePercentageValue.class, result);
        assertEquals(expected, resultString);}

    /**
     * Test of operate method, of class ActuatorOfBlinds.
     * This test checks if the method operate returns a value.
     * The method operate should return a null, as the value is not valid.
     */
    @Test
    void testOperateInvalidValue() {
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        //Act
        Value result = instance.operate(invalidValue);
        //Assert
        assertFalse(result instanceof ScalePercentageValue);
        assertNull(result);}

    /**
     * Test of operate method, of class ActuatorOfBlinds.
     * This test checks if the method operate returns the correct value.
     * The method operate should return the value of the ActuatorOfBlinds.
     * The value of the ActuatorOfBlinds is 100.0 (border).
     */
    @Test
    void testOperateBorderValue100shouldReturnValue100(){
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        String expected = "100.0";
        Value value = mock(ScalePercentageValue.class);
        when(value.valueToString()).thenReturn("100.0");
        //Act
        Value result = instance.operate(value);
        String resultString = result.valueToString();
        //Assert
        assertEquals(expected, resultString);}

    /**
     * Test of operate method, of class ActuatorOfBlinds.
     * This test checks if the method operate returns the correct value.
     * The method operate should return the value of the ActuatorOfBlinds.
     * The value of the ActuatorOfBlinds is 0.0 (border).
     */
    @Test
    void testOperateBorderValue0shouldReturnValue0(){
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        String expected = "0.0";
        Value value = mock(ScalePercentageValue.class);
        when(value.valueToString()).thenReturn("0.0");
        //Act
        Value result = instance.operate(value);
        String resultString = result.valueToString();
        //Assert
        assertEquals(expected, resultString);}

    /**
     * Test of getType method, of class ActuatorOfBlinds.
     * This test checks if the method getType returns the correct type of the actuator.
     * The method getType should return ActuatorType.BLINDSMANAGER.
     */
    @Test
    void testGetType() {
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        //Act
        ActuatorType expected = ActuatorType.BLINDSMANAGER;
        ActuatorType result = instance.getType();
        //Assert
        assertEquals(expected, result);}


    /**
     * Test of setId method, of class ActuatorOfBlinds.
     * This test checks if the method setId sets the correct id of the actuator.
     * The method setId should set the id of the actuator to 1.
     */
    @Test
    void testGenerateId() {
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        int expected = 1;
        //Act
        int result = instance.generateId();
        //Assert
        assertEquals(expected, result);}

    /**
     * Test of getId method, of class ActuatorOfBlinds.
     * This test checks if the method getId returns the correct id of the actuator.
     * The method getId should return 0 (default value).
     */
    @Test
    void testGetId() {
        //Arrange
        ActuatorOfBlinds instance = new ActuatorOfBlinds();
        //Act
        int expected = 0;
        int result = instance.getId();
        //Assert
        assertEquals(expected, result);}

    /**
     * Test of generateId method, of class ActuatorOfBlinds.
     * Test to verify the generated Id is positive.
     */
    @Test
    public void testGenerateIdShouldGenerateValidId() {
        // Arrange
        ActuatorOfBlinds actuatorOfBlinds = new ActuatorOfBlinds();
        // Act
        int generatedId = actuatorOfBlinds.generateId();
        // Assert
        assertTrue(generatedId > 0);}

    /**
     * Test of generateId method, of class ActuatorOfBlinds.
     * Test to verify the Uniqueness of the generated Ids.
     */
    @Test
    public void testGenerateIdProducesUniqueAndValidId() {
        //Arrange
        ActuatorOfBlinds actuator1 = new ActuatorOfBlinds();
        int expected = 5;
        //Act
        for(int i = 0; i < 5; i++)
                actuator1.generateId();
        int result = actuator1.getId();
        ActuatorOfBlinds actuator2 = new ActuatorOfBlinds();
        int result2 = actuator2.generateId();
        //Assert
        assertEquals(expected, result);
        assertEquals(6, result2);}

}