package domain;

import actuators.Actuator;
import factories.ActuatorFactory;
import factories.SensorFactory;
import org.junit.jupiter.api.Test;
import sensors.Sensor;
import sensors.SensorOfHumidity;
import sensors.SensorOfTemperature;
import values.Value;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This Test class represents a group of tests to the class device.
 * It has a valid name and type, and a sensor factory. It is used to test the constructor, the getName and getType methods, and the addSensor method.
 */
class DeviceTest {
    /**
     * A valid name of the device (used to save time and avoid repetition).
     */
    String validName = "Device1";
    /**
     * A valid type of the device (used to save time and avoid repetition).
     */
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
     * SensorType from type Humidity
     */
    SensorType humidity = SensorType.HUMIDITY;

    /**
     * SensorType from type Temperature
     */
    SensorType Temperature = SensorType.TEMPERATURE;

    /**
     * A mock of a Catalogue. It is a test double version of a Catalogue, meaning it is an empty object with the same methods
     * as an actual Catalogue, but all those methods are empty. When executed they will return absolutely nothing and make
     * no changes.
     */
    Catalogue catalogue = mock(Catalogue.class);

    /**
     * A mock of a SensorFactory. It is a test double version of a SensorFactory, meaning it is an empty object with the same methods
     * as an actual SensorFactory, but all those methods are empty. When executed they will return absolutely nothing and make
     * no changes.
     */
    SensorFactory sensorFactory = mock(SensorFactory.class);

    /**
     * A mock of an ActuatorFactory. It is a test double version of an ActuatorFactory, meaning it is an empty object with the same methods
     * as an actual ActuatorFactory, but all those methods are empty. When executed they will return absolutely nothing and make
     * no changes.
     */

    ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);

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
     * The Exception thrown should send a message in a String "Invalid Parameters".
     */
    @Test
    void constructorInvalidNullNameShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(null, validType, sensorFactory, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to a Null SensorFactory (should throw IllegalArgumentException).
     * The Exception thrown should send a message in a String "Invalid Parameters".
     */
    @Test
    void construtorInvalidNullsensorFactoryShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(validName, validType, null, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to a Null Type (should throw IllegalArgumentException).
     * The Exception thrown should send a message in a String "Invalid Parameters".
     */
    @Test
    void constructorInvalidNullTypeShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class, () -> new Device(validName, null, sensorFactory, actuatorFactory));
    }

    /**
     * Test designed to assess the response of the constructor to an Emptu Name (should throw IllegalArgumentException).
     * The Exception thrown should send a message in a String "Invalid Parameters".
     */
    @Test
    void constructorInvalidEmptyNameShouldThrowIllegalArgumentException() {
        //Arrange
        String invalidName = "";
        //Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(invalidName, validType, sensorFactory, actuatorFactory));
    }

    @Test
    void constructorNullActuatorFactoryShouldThrowIllegalArgumentException() {
        //Arrange and Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new Device(validName, validType, sensorFactory, null));
    }

    /**
     * Test designed to assess the response of the constructor to an Emptu Type (should throw IllegalArgumentException).
     * The Exception thrown should send a message in a String "Invalid Parameters".
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
        Sensor result = device.addSensor(sensorClassName, catalogue);
        //Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(humidity, result.getType());
        assertEquals(1, device.getDeviceSensors().size());
        assertEquals(1, device.getFunctionalities().size());
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of temperature to an empty device.
     * The method should return the sensor added, and the device should have 1 sensor and 1 functionality (temperature).
     */
    @Test
    void addSensorOfTemperatureToAnEmptyDevice() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfTemperature";
        //Act
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature);
        when(sensorOfTemperature.getId()).thenReturn(1);
        when(sensorOfTemperature.getType()).thenReturn(Temperature);
        Sensor result = device.addSensor(sensorClassName, catalogue);
        //Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(Temperature, result.getType());
        assertEquals(1, device.getDeviceSensors().size());
        assertEquals(1, device.getFunctionalities().size());
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
        Sensor result = device.addSensor(sensorClassName, catalogue);
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfTemperature2);
        when(sensorOfTemperature2.getId()).thenReturn(2);
        when(sensorOfTemperature2.getType()).thenReturn(Temperature);
        when(sensorOfTemperature2.getValue()).thenReturn(mock(Value.class));
        Sensor result2 = device.addSensor(sensorClassName, catalogue);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(1, result.getId());
        assertEquals(Temperature, result.getType());
        assertEquals(2, result2.getId());
        assertEquals(Temperature, result2.getType());
        assertEquals(2, device.getDeviceSensors().size());
        assertEquals(1, device.getFunctionalities().size());
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor of humidity to a device that already has one.
     * The method should return the sensor added, and the device should have 2 sensor and 1 functionality (humidity).
     */
    @Test
    void addSensorOfHumidityToADeviceWhichAlreadyHasOne() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String sensorClassName = "SensorOfHumidity";
        Sensor sensor2 = mock(SensorOfHumidity.class);
        //Act
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getId()).thenReturn(1);
        when(sensorOfHumidity.getType()).thenReturn(humidity);
        Sensor result = device.addSensor(sensorClassName, catalogue);
        when(sensorFactory.createSensor(sensorClassName)).thenReturn(sensor2);
        when(sensor2.getId()).thenReturn(2);
        when(sensor2.getType()).thenReturn(humidity);
        Sensor result2 = device.addSensor(sensorClassName, catalogue);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(1, result.getId());
        assertEquals(humidity, result.getType());
        assertEquals(2, result2.getId());
        assertEquals(humidity, result2.getType());
        assertEquals(2, device.getDeviceSensors().size());
        assertEquals(1, device.getFunctionalities().size());
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
        Sensor result = device.addSensor(sensorClassName, catalogue);
        when(sensorFactory.createSensor(sensorClassName2)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getId()).thenReturn(2);
        when(sensorOfHumidity.getType()).thenReturn(humidity);
        Sensor result2 = device.addSensor(sensorClassName2, catalogue);
        //Assert
        assertNotNull(result);
        assertNotNull(result2);
        assertEquals(1, result.getId());
        assertEquals(Temperature, result.getType());
        assertEquals(2, result2.getId());
        assertEquals(humidity, result2.getType());
        assertEquals(2, device.getDeviceSensors().size());
        assertEquals(2, device.getFunctionalities().size());
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
        Sensor result = device.addSensor(invalidClassName, catalogue);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
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
        Sensor result = device.addSensor(invalidClassName, catalogue);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
    }

    /**
     * Test designed to evaluate the response of the addSensor method when adding a sensor with a null class name.
     * The method should return null and the device should have no sensors.
     */

    @Test
    void addSensorInvalidClassNameIsNull() {
        //Arrange
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        String invalidClassName = null;
        //Act
        Sensor result = device.addSensor(invalidClassName, catalogue);
        //Assert
        assertNull(result);
        assertEquals(0, device.getDeviceSensors().size());
        assertEquals(0, device.getFunctionalities().size());
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
        Sensor result = device.addSensor(sensorClassName, catalogue);
        when(sensorFactory.createSensor(sensorClassName2)).thenReturn(sensorOfHumidity);
        when(sensorOfHumidity.getId()).thenReturn(2);
        when(sensorOfHumidity.getType()).thenReturn(humidity);
        Sensor result2 = device.addSensor(sensorClassName2, catalogue);
        //Act
        int size = device.getDeviceSensors().size();
        //Assert
        assertEquals(2, size);
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
     * Test designed to evaluate the response of the getDeviceActuators method under normal circumstances.
     * The method should return a list of actuators of the device (size 1).
     */
    @Test
    void testAddActuatorValid() {
        //Arrange
        String validClassName = "ActuatorOfOnOff";
        int expectedSize = 1;
        Device device = new Device(validName, validType, sensorFactory, actuatorFactory);
        Actuator actuatorDouble = mock(Actuator.class);
        when(actuatorFactory.createActuator(validClassName)).thenReturn(actuatorDouble);

        //Act
        Actuator result = device.addActuator(validClassName);
        int resultSize = device.getDeviceActuators().size();

        //Assert
        assertEquals(actuatorDouble, result);
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test designed to evaluate the addActuator method when adding an actuator with an invalid (empty) class name.
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
     * Test designed to evaluate the addActuator method when adding an actuator with an invalid (blank) class name.
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
     * Test designed to evaluate the addActuator method when adding an actuator with a null class name.
     * The method should return null and the device should have no actuators.
     */
    @Test
    void testAddActuatorInvalidClassNameIsNull() {
        //Arrange
        String invalidClassName = null;
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
     * Test designed to evaluate the addActuator method when adding an actuator that does not exist.
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
}