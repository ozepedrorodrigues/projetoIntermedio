package Domain;

import Factories.GPSLocationFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A house may include gardens and other outbuildings that are part of the house (e.g. garage, garden
 * shed, etc.). It may also have more than one floor. A house has an address, including ZIP code, and a
 * GPS location.
 */
public class House {

    /**
     * Location of the house
     */
    private Location location;

    private LocationFactory locationFactory;

    private GPSLocationFactory gpsLocationFactory;

    /**
     * List of rooms in the house
     */
    private List<Room> roomList = new ArrayList<>();

    /**
     * List of availableSensorTypes in the house
     */
    private Catalogue catalogue = new Catalogue();

    /**
     * Private constructor for the house
     */
    private House(String address, String zipCode, double latitude, double longitude, LocationFactory locationFactory, GPSLocationFactory gpsLocationFactory) {
            this.location = locationFactory.createLocation(address, zipCode, latitude, longitude, gpsLocationFactory);
            this.locationFactory = locationFactory;
    }

    /**
     * Method to add a room to the house if it doesn't exist already
     *
     * @return true if the room was successfully added, false otherwise
     */
    public boolean addRoom(String name, int floor, double width, double length, double height) {
        for (Room r : this.roomList)
            if (r.getName().equalsIgnoreCase(name)) return false;
        Room room = new Room(name, floor, width, length, height);
        this.roomList.add(room);
        return true;
    }

    /**
     * Method to get room in the house by its name
     *
     * @param name of the room to be returned
     * @return the room with the given name
     */
    public Room getRoomByName(String name) {
        for (Room room : roomList) {
            if (room.getName().equals(name))
                return room;
        }
        return null;
    }

    /**
     * Method to get a list of all rooms in the house
     *
     * @return a list of all rooms in the house
     */
    public List<Room> getRoomList() {
        List<Room> roomListCopy = new ArrayList<>();
        for (Room room : this.roomList)
            roomListCopy.add(room);
        return roomListCopy;
    }

    /**
     * Getter for the location of the house
     *
     * @return the location of the house
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Configures the location of the house
     */
    public boolean configLocation(String address, String zipCode, double latitude, double longitude) {
        try {
            this.location = this.locationFactory.createLocation(address, zipCode,latitude, longitude, gpsLocationFactory);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Getter for the catalog of the house
     *
     * @return the catalog of the house
     */
    public Catalogue getCatalog() {
        return catalogue;
    }

    /**
     * Method to get devices in the house grouped by room
     *
     * @return a map of devices grouped by room
     */
    public Map<String, List<Device>> getDevicesGroupedByRoom() {
        Map<String, List<Device>> devicesPerRoom = new HashMap<>();
        for (Room room : roomList) {
            List<Device> deviceList = room.getDeviceList();
            devicesPerRoom.put(room.getName(), deviceList);
        }
        return devicesPerRoom;
    }
}