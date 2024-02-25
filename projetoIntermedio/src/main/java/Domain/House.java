package Domain;

import Factories.LocationFactory;
import Factories.RoomFactory;

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

    private Location location;
    private LocationFactory locationFactory;
    private RoomFactory roomFactory;
    private List<Room> rooms;

    /**
     * Constructs a new House object with an empty list of rooms.
     */
    public House() {
        this.rooms = new ArrayList<>();
    }

    /**
     * Constructs a new House object with the specified location factory.
     *
     * @param locationFactory the factory for creating locations
     */
    public House(LocationFactory locationFactory) {
        this.locationFactory = locationFactory;
    }

    /**
     * Constructs a new House object with the specified location factory and room factory.
     *
     * @param locationFactory the factory for creating locations
     * @param roomFactory the factory for creating rooms
     */
    public House(LocationFactory locationFactory, RoomFactory roomFactory) {
        this.locationFactory = locationFactory;
        this.roomFactory = roomFactory;
        this.rooms = new ArrayList<>();
    }

    /**
     * Defines the location of the house using the provided LocationFactory and location details.
     * Attempts to create a new Location object using the specified address, zip code, latitude, and longitude
     * parameters through the provided LocationFactory. If successful, updates the current location of the house
     * and the associated LocationFactory. If the creation process fails due to invalid parameters or other issues,
     * an error message is printed, and no changes to the house's state are made.
     *
     * @param locationFactory the LocationFactory instance responsible for creating Location objects.
     * @param address the address of the location.
     * @param zipCode the zip code of the location.
     * @param latitude the latitude coordinate of the location.
     * @param longitude the longitude coordinate of the location.
     * @return the newly created Location object if the creation process is successful, or null if creation fails.
     */
    public Location defineLocation(LocationFactory locationFactory, String address, String zipCode, double latitude, double longitude) {
        Location newLocation = null;
        try {
            newLocation = locationFactory.createLocation(address, zipCode, latitude, longitude);
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to define location: " + e.getMessage());
        }
        // Update the current location only if the creation was successful
        if (newLocation != null) {
            this.location = newLocation;
            this.locationFactory = locationFactory;
        }
        return newLocation;
    }

    /**
     * Retrieves the location of the house.
     *
     * @return the location of the house
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Adds a new room to the house using the provided room factory and room parameters.
     *
     * @param roomFactory the factory for creating rooms
     * @param roomName    the name of the room
     * @param floor       the floor number of the room
     * @param width       the width of the room
     * @param length      the length of the room
     * @param height      the height of the room
     * @return the added room, or null if addition fails
     */
    public Room addRoom(RoomFactory roomFactory, String roomName, int floor, double width, double length, double height) {
        if (roomNameAlreadyExists(roomName)) {
            System.err.println("Failed to add room: Room with the name '" + roomName + "' already exists");
            // throw new IllegalArgumentException("Invalid room name");
            return null;
        }
        try {
            Room room = roomFactory.createRoom(roomName, floor, width, length, height);
            this.rooms.add(room);
            return room;
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add room: " + e.getMessage());
            // throw new IllegalArgumentException("Failed to add room: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if a room with the given name already exists in the house.
     *
     * @param roomName the name of the room to check
     * @return true if a room with the given name exists, false otherwise
     */
    private boolean roomNameAlreadyExists(String roomName) {
        for (Room room : this.rooms) {
            if (room.getRoomName().equalsIgnoreCase(roomName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the list of rooms in the house.
     *
     * @return the list of rooms in the house
     */
    public List<Room> getRooms() {
        return rooms;
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
                return room;
            }
        }
        return null;
    }

    /**
     * Method to get devices in the house grouped by room
     *
     * @return a map of devices grouped by room
     */
    public Map<String, List<Device>> getDevicesGroupedByRoom() {
        Map<String, List<Device>> devicesPerRoom = new HashMap<>();
        for (Room room : rooms) {
            List<Device> deviceList = room.getDeviceList();
            devicesPerRoom.put(room.getRoomName(), deviceList);
        }
        return devicesPerRoom;
    }
}