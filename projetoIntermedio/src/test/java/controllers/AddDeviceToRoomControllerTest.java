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

import static org.junit.jupiter.api.Assertions.*;


public class AddDeviceToRoomControllerTest {
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
    MapperToDeviceDTO mapperToDeviceDTO;
    AddDeviceToRoomController addDeviceToRoomController;
    AddRoomController addRoomController;

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
        getRoomListController = new GetRoomListController(house, new MapperToRoomDTO());
        mapperToDeviceDTO = new MapperToDeviceDTO();
        addDeviceToRoomController = new AddDeviceToRoomController(house, mapperToDeviceDTO, getRoomListController);
        addRoomController = new AddRoomController(house, new MapperToRoomDTO());
    }

    @Test
    void constructorNotNull() {
        // Arrange
        AddDeviceToRoomController addDeviceToRoomController;
        // Act & Assert
        new AddDeviceToRoomController(house, mapperToDeviceDTO, getRoomListController);
        // No exception is thrown, so the test passes
    }

    @Test
    void constructorNullHouse() {
        // Arrange
        House house = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, mapperToDeviceDTO, getRoomListController));
    }

    @Test
    void constructorNullMapper() {
        // Arrange
        MapperToDeviceDTO mapperToDeviceDTO = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, mapperToDeviceDTO, getRoomListController));
    }

    @Test
    void constructorNullGetRoomListController() {
        // Arrange
        GetRoomListController getRoomListController = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new AddDeviceToRoomController(house, mapperToDeviceDTO, getRoomListController));
    }

    @Test
    void getRoomListOneRoom() throws InstantiationException {
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
        int initialSize = house.getRoomByName(roomName).getDeviceList().size();
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName(roomName).getDeviceList().size();
        String nameResult = house.getRoomByName(roomName).getDeviceList().get(0).getName();
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
        int initialSize = house.getRoomByName("Room2").getDeviceList().size();
        // Act
        DeviceDTO result = addDeviceToRoomController.addDeviceToRoom(deviceDTO);
        int finalSize = house.getRoomByName("Room2").getDeviceList().size();
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