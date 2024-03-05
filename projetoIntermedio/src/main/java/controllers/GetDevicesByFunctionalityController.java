package controllers;
import domain.Device;
import domain.House;
import domain.SensorType;
import dto.DeviceDTO;
import mappers.DeviceGroupMapper;
import java.util.List;
import java.util.Map;

/**
 * Controller class responsible for fetching a list of devices grouped by their functionality.
 * It interacts with the House domain to retrieve devices and utilizes a mapper to organize these devices into a DTO format.
 */
public class GetDevicesByFunctionalityController {

    /**
     * The House instance this controller will operate on to fetch devices.
     */

    private House myHouse;
    /**
     * The mapper that will be used to group devices by functionality.
     */
    private DeviceGroupMapper mapper;

    /**
     * Constructs a new GetDevicesByFunctionalityController with a specified House.
     *
     * @param house The House instance this controller will operate on to fetch devices.
     * @param deviceGroupMapper The mapper that will be used to group devices by functionality.
     * @throws IllegalArgumentException if the house or mapper are invalid.
     */
    public GetDevicesByFunctionalityController(House house, DeviceGroupMapper deviceGroupMapper) {
        if(!validHouse(house)||!validMapper(deviceGroupMapper)) throw new IllegalArgumentException();
        this.myHouse = house;
        this.mapper = deviceGroupMapper;}

    /**
     * Retrieves devices grouped by their functionality.
     * This method first groups devices by room, then maps these groupings into a DTO format based on device functionality.
     *
     * @return A Map where each key is a functionality (String) and each value is a list of DeviceDTOs that share that functionality.
     */
    public Map<String, List<DeviceDTO>> getDeviceByFunctionality() {
        // Group devices by room using a method provided by the House domain model.
        Map<String, List<Device>> devicesByRoom = myHouse.getDevicesGroupedByRoom();
        // Retrieve all possible sensor types to be used for grouping functionalities.
        SensorType[]  sensorTypes = SensorType.values();
        // Use the mapper to group devices by functionality and return the result.
        return mapper.groupDevicesByFunctionality(devicesByRoom, sensorTypes);
    }

    /**
     * Checks if the house given as constructor parameter is valid.
     * @param house The house to be checked.
     * @return True if the house is not null, false otherwise.
     */
    private boolean validHouse(House house) {
        return house != null;}

    /**
     * Checks if the mapper given as constructor parameter is valid.
     * @param mapper The mapper to be checked.
     * @return True if the mapper is not null, false otherwise.
     */
    private boolean validMapper(DeviceGroupMapper mapper) {
        return mapper != null;}
}
