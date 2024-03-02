package controllers;

import domain.*;
import dto.DeviceDTO;
import factories.*;
import factories.implement.*;
import mappers.DeviceGroupMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sensors.Sensor;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link GetListOfDevicesByFunctionalityController}.
 * This class contains unit tests to verify the functionality of retrieving devices
 * grouped by their functionality from a house with multiple rooms and devices.
 */
class GetListOfDevicesByFunctionalityControllerTest {
    House house;
    Catalogue catalogue;
    Room room1;
    Room room2;
    String filePathName;
    GPSFactory GPSFactory;
    LocationFactory locationFactory;
    DimensionsFactory dimensionsFactory;
    DeviceGroupMapper deviceGroupMapper;
    SensorFactory sensorFactory;
    ActuatorFactory actuatorFactory;
    DeviceFactory deviceFactory;
    RoomFactory roomFactory;
    int numberOfFunctionalities;

    /**
     * Sets up the environment for the tests.
     * This includes initializing a house, rooms, factories and a catalogue with predefined properties.
     */
    @BeforeEach
    void setUp() throws Exception{
        filePathName = "config.properties";
        GPSFactory = new GPSFactoryImp();
        locationFactory = new LocationFactoryImp(GPSFactory);
        dimensionsFactory = new DimensionsFactoryImp();
        sensorFactory = new SensorFactoryImp(filePathName);
        actuatorFactory = new ActuatorFactoryImp(filePathName);
        deviceFactory = new DeviceFactoryImp(sensorFactory,actuatorFactory);
        roomFactory = new RoomFactoryImp(dimensionsFactory, deviceFactory);
        house = new House(locationFactory, roomFactory);
        catalogue = new Catalogue(filePathName);
        room1 = house.addRoom("Room1", 1, 2, 3, 4);
        room2 = house.addRoom("Room2", 5, 6, 7, 8);
        numberOfFunctionalities = SensorType.values().length;
        deviceGroupMapper = new DeviceGroupMapper();
    }

    /**
     * Test to verify the functionality of retrieving devices grouped by their functionality from a house with 1 room and 1 device with 1 functionality.
     */
    @Test
    void getDeviceByFunctionality1House2Rooms1Device1Functionality() {
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        for(String functionality : result.keySet()){
            if(functionality.equals("Temperature")){
                assertEquals(1, result.get(functionality).size());}
            else{assertEquals(0, result.get(functionality).size());}}
    }

    /**
     * Test to verify the functionality of retrieving devices grouped by their functionality from a house with 2 rooms and 1 device with 2 functionalities.
     */
    @Test
    void getDeviceByFunctionality1House2Rooms1Device2Functionalities() {
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        Sensor sensor2 = device1.addSensor("SensorOfHumidity",catalogue);//Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        for(String functionality : result.keySet()){
            if(functionality.equals("Temperature")||functionality.equals("Humidity")){
                assertEquals(1, result.get(functionality).size());}
            else{assertEquals(0, result.get(functionality).size());}}
        assertEquals("Device1", result.get("Temperature").getFirst().getName());
        assertEquals("Device1", result.get("Humidity").getFirst().getName());}

    /**
     * Test to verify the functionality of retrieving devices grouped by their functionality from a house with 1 room and 2 devices with 1 functionality each.
     */
    @Test
    void getDeviceByFunctionality1House1Room2Devices1FunctionalityTotal(){
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        Device device2 = room1.addNewDevice("Device2", "Sensor2");
        Sensor sensor2 = device2.addSensor("SensorOfTemperature",catalogue);
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        assertEquals(2, result.get("Temperature").size());
        assertEquals(0, result.get("Humidity").size());
        assertEquals("Device1", result.get("Temperature").getFirst().getName());
        assertEquals("Device2", result.get("Temperature").getLast().getName());}

    /**
     * Test to verify the functionality of retrieving devices grouped by their functionality from a house with 1 room and 2 devices with 2 functionalities each.
     */
    @Test
    void getDeviceByFunctionality1House1Room2Devices2FunctionalitiesEach(){
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        Sensor sensor2 = device1.addSensor("SensorOfHumidity",catalogue);
        Device device2 = room1.addNewDevice("Device2", "Sensor2");
        Sensor sensor3 = device2.addSensor("SensorOfTemperature",catalogue);
        Sensor sensor4 = device2.addSensor("SensorOfHumidity",catalogue);
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        assertEquals(2, result.get("Temperature").size());
        assertEquals(2, result.get("Humidity").size());
        assertEquals("Device1", result.get("Temperature").getFirst().getName());
        assertEquals("Device2", result.get("Temperature").getLast().getName());
        assertEquals("Device1", result.get("Humidity").getFirst().getName());
        assertEquals("Device2", result.get("Humidity").getLast().getName());}

    /**
     * Test to verify the functionality of retrieving devices grouped by their functionality from a house with 2 rooms and 3 devices with 1 functionality each.
     */
    @Test
    void getDeviceByFunctionality1House2Rooms3Devices1FunctionalityEach(){
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        Device device2 = room2.addNewDevice("Device2", "Sensor2");
        Sensor sensor2 = device2.addSensor("SensorOfHumidity",catalogue);
        Device device3 = room2.addNewDevice("Device3", "Sensor3");
        Sensor sensor3 = device3.addSensor("SensorOfScalePercentage",catalogue);
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        assertEquals(1, result.get("Temperature").size());
        assertEquals(1, result.get("Humidity").size());
        assertEquals(1,result.get("Scale Percentage").size());
        assertEquals("Device1", result.get("Temperature").getFirst().getName());
        assertEquals("Device2", result.get("Humidity").getFirst().getName());
        assertEquals("Device3", result.get("Scale Percentage").getFirst().getName());}

    /**
     * Test to verify the functionality of retrieving devices grouped by their functionality from a house with 2 rooms and 3 devices with 3 functionalities each.
     */
    @Test
    void getDeviceByFunctionality1House2Rooms1Device3FunctionalitiesInsideIt(){
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        Sensor sensor2 = device1.addSensor("SensorOfHumidity",catalogue);
        Sensor sensor3 = device1.addSensor("SensorOfScalePercentage",catalogue);
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        assertEquals(1, result.get("Temperature").size());
        assertEquals(1, result.get("Humidity").size());
        assertEquals("Device1", result.get("Temperature").getFirst().getName());
        assertEquals("Device1", result.get("Humidity").getFirst().getName());
        assertEquals("Device1", result.get("Scale Percentage").getFirst().getName());}

    @Test
    void getDeviceByFunctionality1House2Rooms2Devices4Functionalities2InEach(){
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        Sensor sensor1 = device1.addSensor("SensorOfTemperature",catalogue);
        Sensor sensor2 = device1.addSensor("SensorOfHumidity",catalogue);
        Device device2 = room2.addNewDevice("Device2", "Sensor2");
        Sensor sensor5 = device2.addSensor("SensorOfScalePercentage",catalogue);
        Sensor sensor6 = device2.addSensor("SensorOfOnOff",catalogue);
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        for(String functionality : result.keySet()){
            if(functionality.equals("Temperature")||functionality.equals("Humidity")||functionality.equals("Scale Percentage")||functionality.equals("On/Off")){
                assertEquals(1, result.get(functionality).size());}
            else{assertEquals(0, result.get(functionality).size());}}
        assertEquals("Device1", result.get("Temperature").getFirst().getName());
        assertEquals("Device1", result.get("Humidity").getFirst().getName());
        assertEquals("Device2", result.get("Scale Percentage").getFirst().getName());
        assertEquals("Device2", result.get("On/Off").getFirst().getName());}

    @Test
    void getDeviceByFunctionality1House2Rooms2DevicesWithAllFunctionalities(){
        //Arrange
        Device device1 = room1.addNewDevice("Device1", "Sensor1");
        List<String> sensorClassList = catalogue.getSensorsCatalogue();
        for(String sensorClassName : sensorClassList){
            device1.addSensor(sensorClassName,catalogue);}
        Device device2 = room2.addNewDevice("Device2", "Sensor2");
        for(String sensorClassName : sensorClassList){
            device2.addSensor(sensorClassName,catalogue);}
        //Act
        GetListOfDevicesByFunctionalityController getListOfDevicesByFunctionalityController = new GetListOfDevicesByFunctionalityController(house, deviceGroupMapper);
        Map<String, List<DeviceDTO>> result = getListOfDevicesByFunctionalityController.getDeviceByFunctionality();
        //Assert
        assertEquals(numberOfFunctionalities, result.size());
        for(String functionality : result.keySet()){
            assertEquals(2, result.get(functionality).size());}
        for(String functionality : result.keySet()){
            for(DeviceDTO deviceDTO : result.get(functionality)){
                assertTrue(deviceDTO.getName().equals("Device1")||deviceDTO.getName().equals("Device2"));}}}
}
