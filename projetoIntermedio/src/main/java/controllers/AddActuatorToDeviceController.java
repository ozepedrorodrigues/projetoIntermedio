package controllers;

import actuators.Actuator;
import domain.Catalogue;
import domain.Device;
import domain.House;
import domain.Room;
import dto.ActuatorDTO;
import dto.DeviceDTO;
import dto.RoomDTO;
import mappers.ActuatorMapper;
import sensors.Sensor;

import java.util.List;

public class AddActuatorToDeviceController {

    private final House house;
    private final Catalogue catalogue;
    private final GetRoomListController getRoomListController;
    private final GetDeviceListController getDeviceListController;

    private ActuatorMapper actuatorMapper;

    public AddActuatorToDeviceController(House house, Catalogue catalogue,
                                         GetRoomListController getRoomListController,
                                         GetDeviceListController getDeviceListController,
                                         ActuatorMapper actuatorMapper) throws IllegalArgumentException {

        if (!validParameters(house, catalogue, getRoomListController, getDeviceListController, actuatorMapper)) {
            throw new IllegalArgumentException();
        }

        this.house = house;
        this.catalogue = catalogue;
        this.getRoomListController = getRoomListController;
        this.getDeviceListController = getDeviceListController;
        this.actuatorMapper = actuatorMapper;
    }

    public List<RoomDTO> getRooms() {
        return this.getRoomListController.getRoomList();
    }

    public List<DeviceDTO> getDevices(RoomDTO roomDTO) {
        String roomName = roomDTO.getName();
        return this.getDeviceListController.getDevicesInRoom(roomName);
    }

    public List<String> getActuatorModels() {
        return catalogue.getActuatorsCatalogue();
    }

    public ActuatorDTO addActuatorToDevice(DeviceDTO deviceDTO, String actuatorModel) {
        String roomName = deviceDTO.getRoomName();
        String deviceName = deviceDTO.getName();

        Room room = house.getRoomByName(roomName);
        Device device = room.getDeviceByName(deviceName);
        Actuator actuator = device.addActuator(actuatorModel);
        return actuatorMapper.actuatorToDTO(actuator);
    }

    private boolean validParameters(House house, Catalogue catalogue,
                                    GetRoomListController getRoomListController,
                                    GetDeviceListController getDeviceListController, ActuatorMapper actuatorMapper) {

        return house != null && catalogue != null && getRoomListController != null &&
                getDeviceListController != null && actuatorMapper != null;
    }
}
