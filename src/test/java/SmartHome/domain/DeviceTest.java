package SmartHome.domain;

import SmartHome.domain.actuators.Actuator;
import SmartHome.domain.device.Device;
import SmartHome.domain.sensors.*;
import SmartHome.domain.actuators.ActuatorFactory;
import SmartHome.domain.sensors.values.Value;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

import SmartHome.domain.utilities.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This Test class represents a group of tests to the class device.
 * It has a valid name and type, and a sensor factory. It is used to test the constructor, the getName and getType methods, and the addSensor method.
 */
class DeviceTest {
    /**
     * validName attribute (String). To be later defined as a valid name for a device.
     */
    String validName;
    /**
     * validType attribute (String). To be later defined as a valid type for a device.
     */
    String validType;

    /**
     * sensorOfHumidity attribute. To be later defined as a mock SensorOfHumidity.
     */
    SensorOfHumidity sensorOfHumidity;

    /**
     * sensorOfTemperature attribute. To be later defined as a mock SensorOfTemperature.
     */
    SensorOfTemperature sensorOfTemperature;

    /**
     * Humidity attribute. To be later defined as a mock SensorType.HUMIDITY.
     */
    SensorType humidity;

    /**
     * Temperature attribute. To be later defined as a SensorType.
     */
    SensorType Temperature;

    /**
     * sensorFactory attribute. To be later defined as a mock version of SensorFactory.
     */
    SensorFactory sensorFactory;

    /**
     * actuatorFactory attribute. To be later defined as a mock version of ActuatorFactory.
     */

    ActuatorFactory actuatorFactory;

    @BeforeEach
    void setUp() {
        IdGenerator.resetSensorId();
        IdGenerator.resetActuatorId();
        actuatorFactory = mock(ActuatorFactory.class);
        sensorFactory = mock(SensorFactory.class);
        Temperature = SensorType.TEMPERATURE;
        humidity = SensorType.HUMIDITY;
        sensorOfTemperature = mock(SensorOfTemperature.class);
        sensorOfHumidity = mock(SensorOfHumidity.class);
        validType = "Type1";
        validName = "Device1";
    }

    /**
     * Test designed to evaluate the constructor of the Device Class under normal circumstances.
     * No Exception should be thrown, the device should be created.
     */
    @Test
    void constructorValidShouldNotThrowException() throws IllegalArgumentException {
        //Arrange and Act
        new Device(validName, validType, sensorFactory, actuatorFactory);
        //As the constructor does not throw any exception, if the object is created, the test is successful
        //No Assertion needed
    }

    /**
     * Test designed to assess the response of the constructor to a Null Name (should throw IllegalArgumentException).
     */
    @Test
    void constructorInvalidNullNameShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(null, validType, sensorFactory, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to a Null SensorFactory (should throw IllegalArgumentException).
     */
    @Test
    void construtorInvalidNullsensorFactoryShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(validName, validType, null, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to a Null Type (should throw IllegalArgumentException).
     */
    @Test
    void constructorInvalidNullTypeShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(validName, null, sensorFactory, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to an Emptu Name (should throw IllegalArgumentException).
     */
    @Test
    void constructorInvalidEmptyNameShouldThrowIllegalArgumentException() {
        //Arrange
        String invalidName = "";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(invalidName, validType, sensorFactory, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to a Null ActuatorFactory (should throw IllegalArgumentException).
     */
    @Test
    void constructorNullActuatorFactoryShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(validName, validType, sensorFactory, null));
    }

    /**
     * Test designed to assess the response of the constructor to an Emptu Type (should throw IllegalArgumentException).
     */
    @Test
    void constructorInvalidEmptyTypeShouldThrowIllegalArgumentException() {
        //Arrange
        String invalidType = "";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(validName, invalidType, sensorFactory, actuatorFactory));
    }

    /**
     * Test designed to evaluate the response of the getName method under normal circumstances.
     * The method should return the name of the device.
     */
    @Test
    void getName() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        //Act
        String result = device.getName();
        //Assert
        assertEquals(validName, result);
    }

    /**
     * Test designed to evaluate the response of the getType method under normal circumstances.
     * The method should return the type of the device.
     */
    @Test
    void getType() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        //Act
        String result = device.getType();
        //Assert
        assertEquals(validType, result);
    }

    /**
     * Test designed to evaluate the response of the getFunctionalities method when the device has no sensors.
     * The method should return an empty list.
     */
    @Test
    void getFunctionalitiesWithNoSensorsShouldReturnZero() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        //Act
        HashSet<String> result = device.getFunctionalities();
        //Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    /**
     * Test designed to evaluate the response of the getFunctionalities method when the device has 1 sensor.
     * The method should return a list with 1 functionality (temperature).
     */
    @Test
    void getFunctionalitiesWithOneSensor() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfTemperature";
        HashSet<String> expected = new HashSet<>();
        expected.add("Temperature");
        int expectedId = 1;
        //Act
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getId()).thenReturn(1);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(sensorClassName);
        HashSet<String> functionalities = device.getFunctionalities();
        //Assert
        assertNotNull(result);
        assertEquals(expectedId, result.getId());
        assertEquals(Temperature, result.getType());
        assertEquals(expected.size(), device.getDeviceSensors().size());
        assertTrue(device.getDeviceSensors().contains(sensorOfTemperature));
        assertFalse(device.getDeviceSensors().contains(sensorOfHumidity));
        assertTrue(functionalities.containsAll(expected));
        assertEquals(expected.size(), device.getFunctionalities().size());
        assertTrue(device.getFunctionalities().contains("Temperature"));
        assertFalse(device.getFunctionalities().contains("Humidity"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of temperature to an empty device.
     * The method should return the sensor added, and the device should have 1 sensor and 1 functionality (temperature).
     */
    @Test
    void addSensorOfHumidityToAnEmptyDevice() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfHumidity";
        //Act
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getId()).thenReturn(1);
        when(sensorOfHumidity.getType()).thenReturn(humidity);
        Sensor result = device.addSensor(sensorClassName);
        //Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(humidity, result.getType());
        assertEquals(1, device.getDeviceSensors().size());
        assertTrue(device.getDeviceSensors().contains(sensorOfHumidity));
        assertFalse(device.getDeviceSensors().contains(sensorOfTemperature));
        assertEquals(1, device.getFunctionalities().size());
        assertTrue(device.getFunctionalities().contains("Humidity"));
        assertFalse(device.getFunctionalities().contains("Temperature"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of temperature to a device with a temperature sensor.
     * The method should return the sensor added, and the device should have 2 sensor and 1 functionality (temperature).
     */
    @Test
    void addSensorOfTemperatureToADeviceWhichAlreadyHasOne() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfTemperature";
        Sensor sensorOfTemperature2 = mock(SensorOfTemperature.class);
        //Act
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getId()).thenReturn(1);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        when(sensorOfTemperature.getValue()).thenReturn(mock(Value.class));
        Sensor result = device.addSensor(sensorClassName);
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature2);
        when(sensorOfTemperature2.getId()).thenReturn(2);
        when(sensorOfTemperature2.getType()).thenReturn(Temperature);
        when(sensorOfTemperature2.getValue()).thenReturn(mock(Value.class));
        Sensor result2 = device.addSensor(sensorClassName);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(1, result.getId());
        assertEquals(Temperature, result.getType());
        assertEquals(2, result2.getId());
        assertEquals(Temperature, result2.getType());
        assertEquals(2, device.getDeviceSensors().size());
        assertTrue(device.getDeviceSensors().contains(sensorOfTemperature));
        assertTrue(device.getDeviceSensors().contains(sensorOfTemperature2));
        assertEquals(1, device.getFunctionalities().size());
        assertTrue(device.getFunctionalities().contains("Temperature"));
        assertFalse(device.getFunctionalities().contains("Humidity"));
    }


    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of humidity to a device with a temperature sensor.
     * The method should return the sensor added, and the device should have 2 sensor and 2 functionality (humidity and temperature).
     */
    @Test
    void addSensorOfHumidityToADeviceWhichAlreadyHasOneTemperatureSensor() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfTemperature";
        String sensorClassName2 = "SensorOfHumidity";
        //Act
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getId()).thenReturn(1);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(sensorClassName);
        when(sensorFactory.createSensor(sensorClassName2)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getId()).thenReturn(2);
        when(sensorOfHumidity.getType()).thenReturn(humidity);
        Sensor result2 = device.addSensor(sensorClassName2);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(1, result.getId());
        assertEquals(Temperature, result.getType());
        assertEquals(2, result2.getId());
        assertEquals(humidity, result2.getType());
        assertEquals(2, device.getDeviceSensors().size());
        assertTrue(device.getDeviceSensors().contains(sensorOfTemperature));
        assertTrue(device.getDeviceSensors().contains(sensorOfHumidity));
        assertEquals(2, device.getFunctionalities().size());
        assertTrue(device.getFunctionalities().contains("Temperature"));
        assertTrue(device.getFunctionalities().contains("Humidity"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor that does not exist.
     * The method should return null and the device should have no sensors.
     */
    @Test
    void addSensorInvalidClassName() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String invalidClassName = "SensorOfSensor";
        //Act
        Sensor result = device.addSensor(invalidClassName);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
        assertFalse(device.getDeviceSensors().contains(sensorOfTemperature));
        assertFalse(device.getDeviceActuators().contains(sensorOfHumidity));
        assertFalse(device.getFunctionalities().contains("Temperature"));
        assertFalse(device.getFunctionalities().contains("Humidity"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor with an invalid (empty) class name.
     * The method should return null and the device should have no sensors.
     */
    @Test
    void addSensorInvalidClassNameIsEmpty() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String invalidClassName = "";
        //Act
        Sensor result = device.addSensor(invalidClassName);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
        assertFalse(device.getDeviceSensors().contains(sensorOfTemperature));
        assertFalse(device.getDeviceActuators().contains(sensorOfHumidity));
        assertFalse(device.getFunctionalities().contains("Temperature"));
        assertFalse(device.getFunctionalities().contains("Humidity"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor with an invalid (blank) class name.
     * The method should return null and the device should have no sensors.
     */
    @Test
    void addSensorInvalidClassNameIsBlank() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String invalidClassName = " ";
        //Act
        Sensor result = device.addSensor(invalidClassName);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
        assertFalse(device.getDeviceSensors().contains(sensorOfTemperature));
        assertFalse(device.getDeviceActuators().contains(sensorOfHumidity));
        assertFalse(device.getFunctionalities().contains("Temperature"));
        assertFalse(device.getFunctionalities().contains("Humidity"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor with a null class name.
     * The method should return null and the device should have no sensors.
     */

    @Test
    void addSensorInvalidClassNameIsNull() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        //Act
        Sensor result = device.addSensor(null);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
        assertFalse(device.getDeviceSensors().contains(sensorOfTemperature));
        assertFalse(device.getDeviceActuators().contains(sensorOfHumidity));
        assertFalse(device.getFunctionalities().contains("Temperature"));
        assertFalse(device.getFunctionalities().contains("Humidity"));
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor to an inactive device.
     * The method should return null and the device should have no sensors.
     */
    @Test
    void addSensorToInactiveDevice() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        device.deactivate();
        String sensorClassName = "SensorOfTemperature";
        //Act
        Sensor result = device.addSensor(sensorClassName);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
        assertFalse(device.getDeviceSensors().contains(sensorOfTemperature));
        assertFalse(device.getDeviceActuators().contains(sensorOfHumidity));
    }

    /**
     * Test designed to test deactivate() method on an active device.
     * device should be deactivated and returned true.
     */
    @Test
    void deactivateADeviceThatisActiveShouldReturnTrue() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        //Act
        boolean result = device.deactivate();
        //Assert
        assertTrue(result);
    }

    /**
     * Test designed to test deactivate() method on an inactive device.
     * device should remain deactivated and returned false.
     */
    @Test
    void deactivateADeviceThatisInactiveShouldReturnFalse() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        device.deactivate();
        //Act
        boolean result = device.deactivate();
        //Assert
        assertFalse(result);
    }

    /**
     * Test designed to evaluate the response of the getDeviceSensors method under normal circumstances.
     * The method should return a list of sensors of the device (size 2).
     */
    @Test
    void getDeviceSensorsReturnsAListWith2Sensors() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfTemperature";
        String sensorClassName2 = "SensorOfHumidity";
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getId()).thenReturn(1);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        device.addSensor(sensorClassName);
        when(sensorFactory.createSensor(sensorClassName2)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getId()).thenReturn(2);
        when(sensorOfHumidity.getType()).thenReturn(humidity);
        device.addSensor(sensorClassName2);
        //Act
        int size = device.getDeviceSensors().size();
        //Assert
        assertEquals(2, size);
        assertTrue(device.getDeviceSensors().contains(sensorOfTemperature));
        assertTrue(device.getDeviceSensors().contains(sensorOfHumidity));
    }

    /**
     * Test designed to evaluate the response of the getDeviceSensors method when the device has no sensors.
     * The method should return an empty list.
     */
    @Test
    void getDeviceSensorsEmptyList() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        //Act
        int size = device.getDeviceSensors().size();
        //Assert
        assertEquals(0, size);
        assertEquals(0, device.getFunctionalities().size());
    }

    /**
     * Test addActuator method with a valid actuator class name.
     * The method should return the added actuator and the device should have 1 actuator.
     */
    @Test
    void testAddSingleActuator() {
        //Arrange
        String validClassName = "ActuatorOfOnOff";
        int expectedBeforeSize = 0;
        int expectedAfterSize = 1;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorFactory.createActuator(validClassName)).thenReturn(actuatorDouble);

        //Act
        int resultBeforeSize = device.getDeviceActuators().size();
        device.addActuator(validClassName);
        Actuator result = device.getDeviceActuators().getFirst();
        int resultAfterSize = device.getDeviceActuators().size();

        //Assert
        assertEquals(actuatorDouble, result);
        assertEquals(expectedBeforeSize, resultBeforeSize);
        assertEquals(expectedAfterSize, resultAfterSize);
    }

    /**
     * Test addActuator method with a valid actuators class name.
     * The method should return the added actuators and the device should have 2 actuators.
     */
    @Test
    void testAddManyActuators() {
        //Arrange
        String validClassName = "ActuatorOfOnOff";
        String validClassName2 = "ActuatorBlinds";
        int expectedBeforeSize = 0;
        int expectedAfterSize = 2;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        Actuator actuatorDouble = mock(Actuator.class);
        Actuator actuatorDouble2 = mock(Actuator.class);
        when(actuatorFactory.createActuator(validClassName)).thenReturn(actuatorDouble);
        when(actuatorFactory.createActuator(validClassName2)).thenReturn(actuatorDouble2);

        //Act
        int resultBeforeSize = device.getDeviceActuators().size();
        device.addActuator(validClassName);
        device.addActuator(validClassName2);
        Actuator result = device.getDeviceActuators().getFirst();
        Actuator result2 = device.getDeviceActuators().getLast();
        int resultAfterSize = device.getDeviceActuators().size();

        //Assert
        assertEquals(actuatorDouble, result);
        assertEquals(actuatorDouble2, result2);
        assertEquals(expectedBeforeSize, resultBeforeSize);
        assertEquals(expectedAfterSize, resultAfterSize);
    }

    /**
     * Test addActuator method with an invalid actuator class name.
     * The class name is empty.
     * The method should return null and the device should have no actuators.
     */
    @Test
    void testAddActuatorInvalidClassNameIsEmpty() {
        //Arrange
        String invalidClassName = "";
        int expectedSize = 0;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);

        //Act
        Actuator result = device.addActuator(invalidClassName);
        int resultSize = device.getDeviceActuators().size();

        //Assert
        assertNull(result);
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test addActuator method with an invalid actuator class name.
     * The class name is blank.
     * The method should return null and the device should have no actuators.
     */
    @Test
    void testAddActuatorInvalidClassNameIsBlank() {
        //Arrange
        String invalidClassName = "     ";
        int expectedSize = 0;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);

        //Act
        Actuator result = device.addActuator(invalidClassName);
        int resultSize = device.getDeviceActuators().size();

        //Assert
        assertNull(result);
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test addActuator method with an invalid actuator class name.
     * The class name is null.
     * The method should return null and the device should have no actuators.
     */
    @Test
    void testAddActuatorInvalidClassNameIsNull() {
        //Arrange
        int expectedSize = 0;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);

        //Act
        Actuator result = device.addActuator(null);
        int resultSize = device.getDeviceActuators().size();

        //Assert
        assertNull(result);
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test addActuator method with an invalid actuator class name.
     * The class name does not exist.
     * The method should return null and the device should have no actuators.
     */
    @Test
    void testAddActuatorInvalidClassNameDoesntExist() {
        //Arrange
        String invalidClassName = "SensorOfTemperature";
        int expectedSize = 0;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);

        //Act
        Actuator result = device.addActuator(invalidClassName);
        int resultSize = device.getDeviceActuators().size();

        //Assert
        assertNull(result);
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test addActuator method to an inactive device.
     * The method should return null and the device should have no actuators.
     */
    @Test
    void testAddActuatorToInactiveDevice() {
        //Arrange
        String validClassName = "ActuatorOfOnOff";
        int expectedSize = 0;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        device.deactivate();

        //Act
        Actuator result = device.addActuator(validClassName);
        int resultSize = device.getDeviceActuators().size();

        //Assert
        assertNull(result);
        assertEquals(expectedSize, resultSize);
    }
}
