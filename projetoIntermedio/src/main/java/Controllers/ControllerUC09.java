package Controllers;

import DTOs.DeviceDTO;
import Mappers.MapperToGroupDeviceDTO;
import org.domain.Device;
import org.domain.House;

import java.util.List;
import java.util.Map;

/**
 * ControllerUC09 is a controller class for User Story 09.
 * It provides functionality to get a list of all devices in a house, grouped by functionality.
 */
public class ControllerUC09 {
    /**
     * The name of the house.
     */
    private House myHouse;

    /**
     * Constructor for ControllerUC09.
     * Initializes the house instance.
     */
    public ControllerUC09() {
        this.myHouse = House.getInstance();
    }

    /**
     * Returns a list of deviceFuncDTOs (including roomName), grouped by functionality
     *
     * @return a map of deviceFuncDTOs grouped by functionality
     */
    public Map<String, List<DeviceDTO>> getDeviceByFunctionality() {
        Map<String, List<Device>> devicesByRoom = myHouse.getDevicesGroupedByRoom();
        MapperToGroupDeviceDTO mapper = new MapperToGroupDeviceDTO();
        List<String> availableFunctionalities = myHouse.getCatalog().getFunctionalities();
        return mapper.groupDevicesByFunctionality(devicesByRoom, availableFunctionalities);
    }
}