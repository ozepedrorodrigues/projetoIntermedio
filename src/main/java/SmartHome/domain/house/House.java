package SmartHome.domain.house;

import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.domain.room.RoomFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The House class represents a house with a location and a list of rooms.
 * The location of the house is defined using a LocationFactory, and rooms are added to the house using a RoomFactory.
 * The house can also retrieve a list of all rooms, retrieve a room by its name, and get devices in the house grouped by room.
 */
public class House {

    /**
     * The location of the house.
     */
    private Location location;
    /**
     * The factory for creating locations.
     */
    private LocationFactory locationFactory;
    /**
     * The factory for creating Rooms
     */
    private RoomFactory roomFactory;
    /**
     * The list of rooms in the house.
     */
    private List<Room> rooms;

    /**
     * Constructs a new House object with the specified location factory and room factory.
     *
     * @param locationFactory the factory for creating locations
     * @param roomFactory     the factory for creating rooms
     * @throws IllegalArgumentException at least if one of the Factories is not valid
     */
    public House(LocationFactory locationFactory, RoomFactory roomFactory) throws IllegalArgumentException{
        if (!validLocationFactory(locationFactory) || !validRoomFactory(roomFactory)) {
            throw new IllegalArgumentException();
        }
        this.locationFactory = locationFactory;
        this.roomFactory = roomFactory;
        this.rooms = new ArrayList<>();}

    /**
     * Checks if the given LocationFactory is valid.
     *
     * @param locationFactory the location factory to check
     * @return true if the locationFactory is valid, false otherwise
     */
    private boolean validLocationFactory(LocationFactory locationFactory) {
        return locationFactory != null;
    }

    /**
     * Checks if the given RoomFactory is valid.
     *
     * @param roomFactory the room factory to check
     * @return true if the roomFactory is valid, false otherwise
     */
    private boolean validRoomFactory(RoomFactory roomFactory) {
        return roomFactory != null;
    }

    /**
     * Defines the location of the house using the injected LocationFactory and location details.
     * Attempts to create a new Location and updates the Location of the House.
     * @param address   the address of the location.
     * @param zipCode   the zip code of the location.
     * @param latitude  the latitude coordinate of the location.
     * @param longitude the longitude coordinate of the location.
     * @return the newly created Location object if the creation process is successful, or null if creation fails.
     */
    public Location defineLocation(String address, String zipCode, double latitude, double longitude) {
        try {
            this.location = locationFactory.createLocation(address, zipCode, latitude, longitude);
            return this.location;

        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Retrieves the location of the house.
     * @return the location of the house
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Adds a new room to the house using the injected RoomFactory and room details.
     * Attempts to create a new Room and add it to the rooms List.
     *
     * @param roomName the name of the room.
     * @param floor    the floor of the room.
     * @param width    the width of the room.
     * @param length   the length of the room.
     * @param height   the height of the room.
     * @return the newly created Room object if the creation process is successful, or null if creation fails.
     */
    public Room addRoom(String roomName, int floor, double width, double length, double height) {
        if (!validRoomName(roomName)) {
            return null;
        }
        try {
            Room room = roomFactory.createRoom(roomName, floor, width, length, height);
            if (room != null) {
                this.rooms.add(room);
            }
            return room;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Checks if a room with the given name already exists in the house.
     *
     * @param roomName the name of the room to check
     * @return true if a room with the given name exists, false otherwise
     */
    private boolean validRoomName(String roomName) {
        for (Room room : this.rooms) {
            if (room.getRoomName().equalsIgnoreCase(roomName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves a list of all rooms in the house.
     *
     * @return a copy of the list of all rooms in the house
     */
    public List<Room> getRooms() {
        return List.copyOf(this.rooms);
    }

    /**
     * Retrieves a room from the house by its name.
     *
     * @param roomName the name of the room to retrieve
     * @return the room with the given name, or null if not found
     */
    public Room getRoomByName(String roomName) {
        for (Room room : rooms) {
            if (roomName.equals(room.getRoomName())) {
                return room;}}
        return null;
    }

    /**
     * Method to get devices in the house grouped by room
     *
     * @return a Map<String,List<Device>> of devices grouped by roomName
     */
    public Map<String, List<Device>> getDevicesGroupedByRoom() {
        Map<String, List<Device>> devicesPerRoom = new HashMap<>();
        // For each room, get the devices in the room and add them to the map
        for (Room room : rooms) {
            // Get the devices in the room
            List<Device> deviceList = room.getDevicesInRoom();
            // Add the devices to the map
            devicesPerRoom.put(room.getRoomName(), deviceList);
        }
        return devicesPerRoom;
    }
}