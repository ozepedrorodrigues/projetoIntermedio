package domain;

import factories.LocationFactory;
import factories.RoomFactory;

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
    private IdGenerator idGenerator;

    /**
     * Constructs a new House object with the specified location factory and room factory.
     *
     * @param locationFactory the factory for creating locations
     * @param roomFactory     the factory for creating rooms
     */
    public House(LocationFactory locationFactory, RoomFactory roomFactory) {
        if (!validFactory(locationFactory) || !validFactory(roomFactory)) {
            throw new IllegalArgumentException();
        }
        this.locationFactory = locationFactory;
        this.roomFactory = roomFactory;
        this.rooms = new ArrayList<>();
        this.idGenerator = new IdGenerator();
    }

    /**
     * Defines the location of the house using the injected LocationFactory and location details.
     * Attempts to create a new Location object using the injected LocationFactory and the specified
     * address, zip code, latitude, and longitude parameters. If successful, updates the current location
     * of the house. If the creation process fails due to invalid parameters or other issues,
     * an error message is printed, and no changes to the house's state are made.
     *
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

    private boolean validFactory(Object factory) {
        return factory != null;
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
     * Adds a new room to the house using the injected RoomFactory and room details.
     * Attempts to create a new Room object using the injected RoomFactory and the specified
     * room name, floor, width, length, and height parameters. If successful, adds the room
     * to the list of rooms in the house. If a room with the same name already exists,
     * returns null without adding the room. If the creation process fails due to invalid
     * parameters or other issues, an error message is printed, and no changes to the
     * house's state are made.
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
            List<Device> deviceList = room.getDevicesInRoom();
            devicesPerRoom.put(room.getRoomName(), deviceList);
        }
        return devicesPerRoom;
    }
}