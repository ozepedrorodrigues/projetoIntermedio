package controllers;

import domain.House;
import dto.DeviceDTO;
import dto.RoomDTO;
import factories.*;
import factories.implement.*;
import mappers.DeviceMapper;
import mappers.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddDeviceToRoomControllerTest {
    GPSFactory gpsFactory;
    LocationFactory locationFactory;
    String filePathName;
    SensorFactory sensorFactory;
    ActuatorFactory actuatorFactory;
    DeviceFactory deviceFactory;
    DimensionsFactory dimensionsFactory;
    RoomFactory roomFactory;
    House house;
    GetRoomListController getRoomListController;
    DeviceMapper deviceMapper;
    RoomMapper roomMapper;
    AddDeviceToRoomController addDeviceToRoomController;
    AddRoomController addRoomController;

    @BeforeEach
    void setUpIntegration() throws InstantiationException {
        filePathName = "config.properties";
        gpsFactory = new GPSFactoryImp();
        locationFactory = new LocationFactoryImp(gpsFactory);
        sensorFactory = new SensorFactoryImp(filePathName);
        actuatorFactory = new ActuatorFactoryImp(filePathName);
        deviceFactory = new DeviceFactoryImp(sensorFactory,actuatorFactory);
        dimensionsFactory = new DimensionsFactoryImp();
        roomFactory = new RoomFactoryImp(dimensionsFactory, deviceFactory);
        house = new House(locationFactory, roomFactory);
        roomMapper = new RoomMapper();
        getRoomListController = new GetRoomListController(house, roomMapper);
        deviceMapper = new DeviceMapper();
        addDeviceToRoomController = new AddDeviceToRoomController(house, deviceMapper, getRoomListController);
        addRoomController = new AddRoomController(house, roomMapper);
    }

    @Test
    void constructorNotNull() {
        // Arrange
        AddDeviceToRoomController addDeviceToRoomController;
        // Act & Assert
        new AddDeviceToRoomController(house, deviceMapper, getRoomListController);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorNullHouse() {
        // Arrange
        House house = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, getRoomListController));
    }

    @Test
    void constructorNullMapper() {
        // Arrange
        DeviceMapper deviceMapper = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, getRoomListController));
    }

    @Test
    void constructorNullGetRoomListController() {
        // Arrange
        GetRoomListController getRoomListController = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, deviceMapper, getRoomListController));
    }

    @Test
    void getRoomListOneRoom() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        int sizeExpected = 1;
        // Act
        int sizeResult = addDeviceToRoomController.getRoomList().size();
        // Assert
        assertEquals(sizeExpected, sizeResult);
    }

    @Test
    void addDeviceToRoom() {
        // Arrange
        String roomName = "Room1";
        String deviceName = "Device1";
        RoomDTO roomDTO = new RoomDTO(roomName, 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, "DeviceType1", "Room1");
        int initialSize = house.getRoomByName(roomName).getDevicesInRoom().size();
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName(roomName).getDevicesInRoom().size();
        String nameResult = house.getRoomByName(roomName).getDevicesInRoom().getFirst().getName();
        // Assert
        assertEquals(initialSize + 1, finalSize);
        assertEquals(deviceName, nameResult);
    }

    @Test
    void addDeviceToRoomDeviceNameIsRepeated() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        DeviceDTO deviceDTO = new DeviceDTO("Device1", "DeviceType1", "Room1");
        // Act
        addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        // Assert
        assertNull(result);
    }

    @Test
    void addDeviceToCorrectRoom() {
        // Arrange
        RoomDTO roomDTO = new RoomDTO("Room1", 1, 1, 1, 1);
        RoomDTO roomDTO2 = new RoomDTO("Room2", 1, 1, 1, 1);
        addRoomController.addNewRoomToHouse(roomDTO);
        addRoomController.addNewRoomToHouse(roomDTO2);
        DeviceDTO deviceDTO = new DeviceDTO("Device1", "DeviceType1", "Room2");
        int initialSize = house.getRoomByName("Room2").getDevicesInRoom().size();
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName("Room2").getDevicesInRoom().size();
        // Assert
        assertEquals(initialSize + 1, finalSize);
        assertNotNull(result);
    }

    @Test
    void addDeviceToRoomNullDeviceDTO() {
        // Arrange
        DeviceDTO deviceDTO = null;
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        // Assert
        assertNull(result);
    }
}