package controllers;

import domain.House;
import dto.DeviceDTO;
import dto.RoomDTO;
import factories.*;
import factories.implement.*;
import mappers.MapperToDeviceDTO;
import mappers.MapperToRoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases for the ControllerUC08 class.
 * Each test case corresponds to a different scenario of deactivating a device in a room.
 */
class DeactivateDeviceControllerTest {

    GPSFactory gpsFactory;
    LocationFactory locationFactory;
    ValueFactory valueFactory;
    String filePathName;
    SensorFactory sensorFactory;
    DeviceFactory deviceFactory;
    DimensionsFactory dimensionsFactory;
    RoomFactory roomFactory;
    House house;
    GetRoomListController getRoomListController;
    MapperToRoomDTO mapperToRoomDTO;
    GetDeviceListController getDeviceListController;
    MapperToDeviceDTO mapperToDeviceDTO;
    AddDeviceToRoomController addDeviceToRoomController;
    AddRoomController addRoomController;
    DeactivateDeviceController deactivateDeviceController;
    DeviceDTO myDeviceDTO;

    @BeforeEach
    void setUpIntegration() throws InstantiationException {
        filePathName = "config.properties";
        gpsFactory = new GPSFactoryImp();
        locationFactory = new LocationFactoryImp(gpsFactory);
        valueFactory = new ValueFactoryImp();
        sensorFactory = new SensorFactoryImp(filePathName, valueFactory);
        deviceFactory = new DeviceFactoryImp(sensorFactory);
        dimensionsFactory = new DimensionsFactoryImp();
        roomFactory = new RoomFactoryImp(dimensionsFactory, deviceFactory);
        house = new House(locationFactory, roomFactory);
        mapperToRoomDTO = new MapperToRoomDTO();
        mapperToDeviceDTO = new MapperToDeviceDTO();
        getRoomListController = new GetRoomListController(house, mapperToRoomDTO);
        getDeviceListController = new GetDeviceListController(house, mapperToDeviceDTO);
        addDeviceToRoomController = new AddDeviceToRoomController(house, mapperToDeviceDTO, getRoomListController);
        addRoomController = new AddRoomController(house, mapperToRoomDTO);
        deactivateDeviceController = new DeactivateDeviceController(house, getRoomListController, getDeviceListController, mapperToDeviceDTO);

        RoomDTO roomDTO = new RoomDTO("Room", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        myDeviceDTO = new DeviceDTO("Device", "Type", "Room");
        addDeviceToRoomController.addDeviceToRoom(myDeviceDTO);
    }

    @Test
    void testGetRoomList() {
        // Arrange
        int expected = 1;
        // Act
        List<RoomDTO> roomList = deactivateDeviceController.getRoomList();
        int result = roomList.size();
        // Assert
        assertEquals(expected, result);
    }


    @Test
    void testGetDeviceList() {
        // Arrange
        int expected = 1;
        //  Act
        List<DeviceDTO> deviceList = deactivateDeviceController.getDeviceList("Room");
        int result = deviceList.size();
        // Assert
        assertEquals(expected, result);
    }


    @Test
    void testDeactivateDevice() {
        // Arrange & Act
        boolean result = deactivateDeviceController.deactivateDevice(myDeviceDTO);
        // Assert
        assertTrue(result);
    }


    @Test
    void testDeactivateDeviceAgain() {
        // Arrange & Act
        deactivateDeviceController.deactivateDevice(myDeviceDTO);
        boolean result = deactivateDeviceController.deactivateDevice(myDeviceDTO);
        // Assert
        assertFalse(result);
    }


    @Test
    void testDeactivateDeviceDTONull() {
        // Arrange & Act
        boolean result = deactivateDeviceController.deactivateDevice(null);
        // Assert
        assertFalse(result);
    }
}