package Domain;

/**
 * Represents the dimensions of a room in a house.
 * The dimensions include width, length, and height.
 */
public class Dimensions {
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
     * Constructor for creating a Dimensions instance with specified width, length, and height.
     *
     * @param width  the width of the room (must be a non-negative value)
     * @param length the length of the room (must be a non-negative value)
     * @param height the height of the room
     */
    public Dimensions(double width, double length, double height) {
        if (width <= 0 || length <= 0 || height < 0)
            throw new IllegalArgumentException("Invalid dimensions");
        this.width = width;
        this.length = length;
        this.height = height;
    }

    /**
     * Getter method to retrieve the width of the room.
     *
     * @return the width of the room
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter method to retrieve the length of the room.
     *
     * @return the length of the room
     */
    public double getLength() {
        return this.length;
    }

    /**
     * Getter method to retrieve the height of the room.
     *
     * @return the height of the room
     */
    public double getHeight() {
        return this.height;
    }
}