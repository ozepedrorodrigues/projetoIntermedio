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

import java.util.List;

/**
 * This class represents the controller for adding an actuator to a device.
 * It is used to add an actuator to a device and return the actuator as an ActuatorDTO.
 */
public class AddActuatorToDeviceController {

    /**
     * The house instance to which the actuator is to be added.
     */
    private final House house;

    /**
     * The catalogue instance to be used.
     */
    private final Catalogue catalogue;

    /**
     * The GetRoomListController instance to be used.
     */
    private final GetRoomListController getRoomListController;

    /**
     * The GetDeviceListController instance to be used.
     */
    private final GetDeviceListController getDeviceListController;

    /**
     * The ActuatorMapper instance to be used.
     */
    private ActuatorMapper actuatorMapper;

    /**
     * Constructs a new AddActuatorToDeviceController with the specified house, catalogue, getRoomListController,
     * getDeviceListController, and actuatorMapper.
     *
     * @param house                   the house to which the actuator is to be added
     * @param catalogue               the catalogue to be used
     * @param getRoomListController   the getRoomListController to be used
     * @param getDeviceListController the getDeviceListController to be used
     * @param actuatorMapper          the actuatorMapper to be used
     * @throws IllegalArgumentException if an error occurs during the instantiation of the objects.
     */
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

    /**
     * Gets the rooms in the house.
     *
     * @return the rooms in the house as a list of RoomDTOs
     */
    public List<RoomDTO> getRooms() {
        return this.getRoomListController.getRoomList();
    }

    /**
     * Gets the devices in the room.
     *
     * @param roomDTO the room to get the devices from
     * @return the devices in the room as a list of DeviceDTOs
     */
    public List<DeviceDTO> getDevices(RoomDTO roomDTO) {
        String roomName = roomDTO.getName();
        return this.getDeviceListController.getDevicesInRoom(roomName);
    }

    /**
     * Gets the actuator models.
     *
     * @return the actuator models as a list of strings
     */
    public List<String> getActuatorModels() {
        return catalogue.getActuatorsCatalogue();
    }

    /**
     * Adds an actuator to a device.
     *
     * @param deviceDTO     the device to add the actuator to
     * @param actuatorModel the actuator model to add
     * @return the added actuator as an ActuatorDTO, null if the actuator could not be added
     */
    public ActuatorDTO addActuatorToDevice(DeviceDTO deviceDTO, String actuatorModel) {
        String roomName = deviceDTO.getRoomName();
        String deviceName = deviceDTO.getName();

        Room room = house.getRoomByName(roomName);
        Device device = room.getDeviceByName(deviceName);
        Actuator actuator = device.addActuator(actuatorModel);
        if (actuator == null) {
            return null;
        }
        return actuatorMapper.actuatorToDTO(actuator);
    }

    /**
     * Checks if the parameters passed to the constructor are valid.
     *
     * @param house                   the house to be checked
     * @param catalogue               the catalogue to be checked
     * @param getRoomListController   the getRoomListController to be checked
     * @param getDeviceListController the getDeviceListController to be checked
     * @param actuatorMapper          the actuatorMapper to be checked
     * @return true if the parameters are not null, false otherwise
     */
    private boolean validParameters(House house, Catalogue catalogue,
                                    GetRoomListController getRoomListController,
                                    GetDeviceListController getDeviceListController, ActuatorMapper actuatorMapper) {

        return house != null && catalogue != null && getRoomListController != null &&
                getDeviceListController != null && actuatorMapper != null;
    }
}
