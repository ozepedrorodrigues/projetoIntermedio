package Domain;

import Factories.SensorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This Test class represents a group of tests to the class device.
 * It has a valid name and type, and a sensor factory. It is used to test the constructor, the getName and getType methods, and the addSensor method.
 */
class DeviceTest {
    /**
     * A valid name of the device (used to save time and avoid repetition).*/
    String validName = "Device1";
    /**
     * A valid type of the device (used to save time and avoid repetition).*/
    String validType = "Type1";

    /**
     * A mock of a sensor of humidity. It is a test double version of a sensor, meaning it is an empty object with the same methods
     * as an actual sensor, but all those methods are empty. When executed they will return absolutely nothing and make
     * no changes.
     */
    SensorOfHumidity sensorOfHumidity = mock(SensorOfHumidity.class);
    /**
     * A mock of a sensor of temperature. It is a test double version of a sensor, meaning it is an empty object with the same methods
     * as an actual sensor, but all those methods are empty. When executed they will return absolutely nothing and make
     * no changes.
     */
    SensorOfTemperature sensorOfTemperature = mock(SensorOfTemperature.class);

    /**
     * A valid sensor name (used to save time and avoid repetition).*/
    String validSensorName = "Sensor1";
    /**
     * 2 valid sensor types (used to save time and avoid repetition).*/
    String Humidity = "HUMIDITY";
    String Temperature = "TEMPERATURE";

    Catalogue catalogue = mock(Catalogue.class);

    /**
     * Test designed to evaluate the constructor of the Device Class under normal circumstances.
     * No Exception should be thrown, the device should be created.*/
    @Test
    void constructorValidShouldNotThrowException() throws IllegalArgumentException,NullPointerException {
        //Arrange and Act
        new Device(validName,validType);
        //As the constructor does not throw any exception, if the object is created, the test is successful
        //No Assertion needed
    }

    /**
     * Test designed to assess the response of the constructor to a Null Name (should throw NullPointerException).
     * The Exception thrown should send a message in a String "Invalid Parameters".*/
    @Test
    void constructorInvalidNullNameShouldThrowNullPointerException() {
        //Arrange and Act
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new Device(null,validType));
        //Assert
        assertEquals("Invalid Parameters", exc.getMessage());}

    /**
     * Test designed to assess the response of the constructor to a Null Type (should throw NullPointerException).
     * The Exception thrown should send a message in a String "Invalid Parameters".*/
    @Test
    void constructorInvalidNullTypeShouldThrowNullPointerException() {
        //Arrange and Act
        NullPointerException exc = assertThrows(NullPointerException.class, () -> new Device(validName,null));
        //Assert
        assertEquals("Invalid Parameters", exc.getMessage());}
    /**
     * Test designed to assess the response of the constructor to an Emptu Name (should throw IllegalArgumentException).
     * The Exception thrown should send a message in a String "Empty parameter(s)".*/
    @Test
    void constructorInvalidEmptyNameShouldThrowIllegalArgumentException() {
        //Arrange
        String invalidName = "";
        //Act
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> new Device(invalidName,validType));
        //Assert
        assertEquals("Empty parameter(s)", exc.getMessage());}

    /**
     * Test designed to assess the response of the constructor to an Emptu Type (should throw IllegalArgumentException).
     * The Exception thrown should send a message in a String "Empty parameter(s)".
     */
    @Test
    void constructorInvalidEmptyTypeShouldThrowIllegalArgumentException() {
        //Arrange
        String invalidType = "";
        //Act
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> new Device(validName,invalidType));
        //Assert
        assertEquals("Empty parameter(s)", exc.getMessage());}

    /**
     * Test designed to evaluate the response of the getName method under normal circumstances.
     * The method should return the name of the device.*/
    @Test
    void getName() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        String result = device.getName();
        //Assert
        assertEquals(validName,result);}

    /**
     * Test designed to evaluate the response of the getType method under normal circumstances.
     * The method should return the type of the device.*/
    @Test
    void getType() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        String result = device.getType();
        //Assert
        assertEquals(validType,result);}

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of temperature to an empty device.
     * The method should return the sensor added, and the device should have 1 sensor and 1 functionality (temperature).*/
    @Test
    void addSensorOfHumidityToAnEmptyDevice() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getName()).thenReturn(validSensorName);
        when(sensorOfHumidity.getType()).thenReturn(Humidity);
        Sensor result = device.addSensor(validSensorName,Humidity,catalogue);
        //Assert
        assertNotNull(result);
        assertEquals(validSensorName,result.getName());
        assertEquals(Humidity,result.getType());
        assertEquals(1,device.getDeviceSensors().size());
        assertEquals(1,device.getFunctionalities().size());}

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of temperature to an empty device.
     * The method should return the sensor added, and the device should have 1 sensor and 1 functionality (temperature).*/
     @Test
    void addSensorOfTemperatureToAnEmptyDevice() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
         when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfTemperature);
         when(sensorOfTemperature.getName()).thenReturn(validSensorName);
         when(sensorOfTemperature.getType()).thenReturn(Temperature);
         Sensor result = device.addSensor(validSensorName,Temperature,catalogue);
        //Assert
        assertNotNull(result);
        assertEquals(validSensorName,result.getName());
        assertEquals(Temperature,result.getType());
        assertEquals(1,device.getDeviceSensors().size());
        assertEquals(1,device.getFunctionalities().size());}

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of temperature to a device with a temperature sensor.
     * The method should return the sensor added, and the device should have 2 sensor and 1 functionality (temperature).
     */
    @Test
    void addSensorOfTemperatureToADeviceWhichAlreadyHasOne() {
        //Arrange
        Device device = new Device(validName,validType);
        Sensor sensorOfTemperature2 = mock(SensorOfTemperature.class);
        //Act
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getName()).thenReturn(validSensorName);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(validSensorName,Temperature,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensorOfTemperature2);
        when(sensorOfTemperature2.getName()).thenReturn("Sensor2");
        when(sensorOfTemperature2.getType()).thenReturn(Temperature);
        Sensor result2 = device.addSensor("Sensor2",Temperature,catalogue);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(validSensorName,result.getName());
        assertEquals(Temperature,result.getType());
        assertEquals("Sensor2",result2.getName());
        assertEquals(Temperature,result2.getType());
        assertEquals(2,device.getDeviceSensors().size());
        assertEquals(1,device.getFunctionalities().size());}

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of humidity to a device that already has one.
     * The method should return the sensor added, and the device should have 2 sensor and 1 functionality (humidity).*/
    @Test
    void addSensorOfHumidityToADeviceWhichAlreadyHasOne() {
        //Arrange
        Device device = new Device(validName,validType);
        Sensor sensor2 = mock(SensorOfHumidity.class);
        //Act
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getName()).thenReturn(validSensorName);
        when(sensorOfHumidity.getType()).thenReturn(Humidity);
        Sensor result = device.addSensor(validSensorName,Humidity,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensor2);
        when(sensor2.getName()).thenReturn("Sensor2");
        when(sensor2.getType()).thenReturn(Humidity);
        Sensor result2 = device.addSensor("Sensor2",Humidity,catalogue);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(validSensorName,result.getName());
        assertEquals(Humidity,result.getType());
        assertEquals("Sensor2",result2.getName());
        assertEquals(Humidity,result2.getType());
        assertEquals(2,device.getDeviceSensors().size());
        assertEquals(1,device.getFunctionalities().size());}

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of humidity to a device with a temperature sensor.
     * The method should return the sensor added, and the device should have 2 sensor and 2 functionality (humidity).*/
    @Test
    void addSensorOfHumidityToADeviceWhichAlreadyHasOneTemperatureSensor() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getName()).thenReturn(validSensorName);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(validSensorName,Temperature,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getName()).thenReturn("Sensor2");
        when(sensorOfHumidity.getType()).thenReturn(Humidity);
        Sensor result2 = device.addSensor("Sensor2",Humidity,catalogue);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(validSensorName,result.getName());
        assertEquals(Temperature,result.getType());
        assertEquals("Sensor2",result2.getName());
        assertEquals(Humidity,result2.getType());
        assertEquals(2,device.getDeviceSensors().size());
        assertEquals(2,device.getFunctionalities().size());}

    /**
     * Test designed to test deactivate() method on an active device.
     * device should be deactivated and returned true.*/
    @Test
    void deactivateADeviceThatisActiveShouldReturnTrue() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        boolean result = device.deactivate();
        //Assert
        assertTrue(result);}

    /**
     * Test designed to test deactivate() method on an inactive device.
     * device should remain deactivated and returned false.*/

    @Test
    void deactivateADeviceThatisInactiveShouldReturnFalse() {
        //Arrange
        Device device = new Device(validName,validType);
        device.deactivate();
        //Act
        boolean result = device.deactivate();
        //Assert
        assertFalse(result);}

    /**
     * Test designed to evaluate the response of the getDeviceSensors method under normal circumstances.
     * The method should return a list of sensors of the device (size 2).*/
    @Test
    void getDeviceSensorsReturnsAListWith2Sensors() {
        //Arrange
        Device device = new Device(validName,validType);
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getName()).thenReturn(validSensorName);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(validSensorName,Temperature,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getName()).thenReturn("Sensor2");
        when(sensorOfHumidity.getType()).thenReturn(Humidity);
        Sensor result2 = device.addSensor("Sensor2",Humidity,catalogue);
        //Act
        int size = device.getDeviceSensors().size();
        //Assert
        assertEquals(2,size);}

    /**
     * Test designed to evaluate the response of the getDeviceSensors method when the device has no sensors.
     * The method should return an empty list.*/
    @Test
    void getDeviceSensorsEmptyList() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        int size = device.getDeviceSensors().size();
        //Assert
        assertEquals(0,size);}

    /**
     * Test designed to evaluate the response of the getFunctionalities method under normal circumstances.
     * The method should return a list of functionalities of the device (size 1).*/
    @Test
    void getFunctionalitiesinADeviceWith2TemperatureSensorsShouldReturn1Functionality() {
        //Arrange
        Device device = new Device(validName,validType);
        Sensor sensorOfTemperature2 = mock(SensorOfTemperature.class);
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getName()).thenReturn(validSensorName);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(validSensorName,Temperature,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensorOfTemperature2);
        when(sensorOfTemperature2.getName()).thenReturn("Sensor2");
        when(sensorOfTemperature2.getType()).thenReturn(Temperature);
        Sensor result2 = device.addSensor("Sensor2",Temperature,catalogue);
        //Act
        int size = device.getFunctionalities().size();
        //Assert
        assertEquals(1,size);}

    /**
     * Test designed to evaluate the response of the getFunctionalities method under normal circumstances.
     * The method should return a list of functionalities of the device (size 1).*/
    @Test
    void getFunctionalitiesinADeviceWith2HumiditySensorsShouldReturn1Functionality() {
        //Arrange
        Device device = new Device(validName,validType);
        Sensor sensorOfHumidity2 = mock(SensorOfHumidity.class);
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getName()).thenReturn(validSensorName);
        when(sensorOfHumidity.getType()).thenReturn(Humidity);
        Sensor result = device.addSensor(validSensorName,Humidity,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensorOfHumidity2);
        when(sensorOfHumidity2.getName()).thenReturn("Sensor2");
        when(sensorOfHumidity2.getType()).thenReturn(Humidity);
        Sensor result2 = device.addSensor("Sensor2",Humidity,catalogue);
        //Act
        int size = device.getFunctionalities().size();
        //Assert
        assertEquals(1,size);
        assertTrue(device.getFunctionalities().contains(Humidity));
        assertFalse(device.getFunctionalities().contains(Temperature));}

    /**
     * Test designed to evaluate the response of the getFunctionalities method under normal circumstances.
     * The method should return a list of functionalities of the device (size 2).
     */
    @Test
    void getFunctionalitiesinADeviceWith1HumidityAnd1TemperatureSensorsShouldReturn2Functionalities() {
        //Arrange
        Device device = new Device(validName,validType);
        when(catalogue.getSensor(validSensorName)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getName()).thenReturn(validSensorName);
        when(sensorOfHumidity.getType()).thenReturn(Humidity);
        Sensor result = device.addSensor(validSensorName,Humidity,catalogue);
        when(catalogue.getSensor("Sensor2")).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getName()).thenReturn("Sensor2");
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result2 = device.addSensor("Sensor2",Temperature,catalogue);
        //Act
        int size = device.getFunctionalities().size();
        //Assert
        assertEquals(2,size);
        assertTrue(device.getFunctionalities().contains(Humidity));
        assertTrue(device.getFunctionalities().contains(Temperature));}

    /**
     * Test designed to evaluate the response of the getFunctionalities method when the device has no sensors.
     * The method should return an empty list.
     */
    @Test
    void getFunctionalitiesinADeviceWithNoSensorsShouldReturn0Functionalities() {
        //Arrange
        Device device = new Device(validName,validType);
        //Act
        int size = device.getFunctionalities().size();
        //Assert
        assertEquals(0,size);}
}