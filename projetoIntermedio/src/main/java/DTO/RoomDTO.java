package DTO;

/**
 * This class is a Data Transfer Object (DTO) for Room.
 * It is used to transfer data about a device between processes or layers in an application.
 * The RoomDTO class includes fields for the name, floor, width, length, and height of a room.
 */
public class RoomDTO {

    /**
     * The name of the room.
     */
    private String name;

    /**
     * The floor number of the room.
     */
    private int floor;

    /**
     * The width of the room.
     */
    private double width;

    /**
     * The length of the room.
     */
    private double length;

    /**
     * The height of the room.
     */
    private double height;

    /**
     * Constructs a new RoomDTO with the given name, floor, width, length, and height.
     *
     * @param name   the name of the room
     * @param floor  the floor number of the room
     * @param width  the width of the room
     * @param length the length of the room
     * @param height the height of the room
     */
    public RoomDTO(String name, int floor, double width, double length, double height) {
        this.name = name;
        this.floor = floor;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    /**
     * Returns the name of the room.
     *
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the floor number of the room.
     *
     * @return the floor number of the room
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Returns the width of the room.
     *
     * @return the width of the room
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the length of the room.
     *
     * @return the length of the room
     */
    public double getLength() {
        return length;
    }

    /**
     * Returns the height of the room.
     *
     * @return the height of the room
     */
    public double getHeight() {
        return height;
    }
}
