package controllers;

import domain.Device;
import domain.House;
import domain.SensorType;
import dto.DeviceDTO;
import mappers.MapperToGroupDeviceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ControllerUC09 is a controller class for User Story 09.
 * It provides functionality to get a list of all devices in a house, grouped by functionality.
 */
public class GetListOfDevicesByFunctionalityController {
    /**
     * The name of the house.
     */
    private House myHouse;

    /**
     * Constructor for ControllerUC09.
     * Initializes the house instance.
     */
    public GetListOfDevicesByFunctionalityController(House house) {
        this.myHouse = house;
    }

    /**
     * Returns a list of deviceFuncDTOs (including roomName), grouped by functionality
     *
     * @return a map of deviceFuncDTOs grouped by functionality
     */
   public Map<String, List<DeviceDTO>> getDeviceByFunctionality() {
        Map<String, List<Device>> devicesByRoom = myHouse.getDevicesGroupedByRoom();
        MapperToGroupDeviceDTO mapper = new MapperToGroupDeviceDTO();
        SensorType[]  sensorTypes = SensorType.values();
        List<String>  functionalities = new ArrayList<>();
        for (SensorType sensorType : sensorTypes) {
            functionalities.add(sensorType.getSensorType());}
        return mapper.groupDevicesByFunctionality(devicesByRoom, functionalities);
    }


}