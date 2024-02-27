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
 * Controller class responsible for fetching a list of devices grouped by their functionality.
 * It interacts with the House domain to retrieve devices and utilizes a mapper to organize these devices into a DTO format.
 */
public class GetListOfDevicesByFunctionalityController {

    private House myHouse;

    /**
     * Constructs a new GetListOfDevicesByFunctionalityController with a specified House.
     *
     * @param house The House instance this controller will operate on to fetch devices.
     */
    public GetListOfDevicesByFunctionalityController(House house) {
        this.myHouse = house;
    }

    /**
     * Retrieves devices grouped by their functionality.
     * This method first groups devices by room, then maps these groupings into a DTO format based on device functionality.
     *
     * @return A Map where each key is a functionality (String) and each value is a list of DeviceDTOs that share that functionality.
     */
    public Map<String, List<DeviceDTO>> getDeviceByFunctionality() {
        // Group devices by room using a method provided by the House domain model.
        Map<String, List<Device>> devicesByRoom = myHouse.getDevicesGroupedByRoom();
        // Initialize the mapper used to translate between domain models and DTOs.
        MapperToGroupDeviceDTO mapper = new MapperToGroupDeviceDTO();
        // Retrieve all possible sensor types to be used for grouping functionalities.
        SensorType[]  sensorTypes = SensorType.values();
        // Prepare a list to hold all functionalities as strings.
        List<String>  functionalities = new ArrayList<>();
        // Populate the functionalities list with the sensor types available.
        for (SensorType sensorType : sensorTypes) {functionalities.add(sensorType.getSensorType());}
        // Use the mapper to group devices by functionality and return the result.
        return mapper.groupDevicesByFunctionality(devicesByRoom, functionalities);
    }
}
