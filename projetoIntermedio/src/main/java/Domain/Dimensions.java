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
     * Constructor to create a Dimensions instance with specified width, length, and height.
     *
     * @param width  the width of the room (must be a non-negative value)
     * @param length the length of the room (must be a non-negative value)
     * @param height the height of the room
     * @throws IllegalArgumentException if the provided dimensions are invalid
     */
    public Dimensions(double width, double length, double height) throws IllegalArgumentException {
        if (!validWidth(width) || !validLength(length) || !validHeight(height))
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

    /**
     * Method to validate the provided width.
     *
     * @param width the width to be validated
     * @return true if the width is valid, false otherwise
     */
    private boolean validWidth(double width) {
        return width > 0;
    }

    /**
     * Method to validate the provided length.
     *
     * @param length the length to be validated
     * @return true if the length is valid, false otherwise
     */
    private boolean validLength(double length) {
        return length > 0;
    }

    /**
     * Method to validate the provided height.
     *
     * @param height the height to be validated
     * @return true if the height is valid, false otherwise
     */
    private boolean validHeight(double height) {
        return height >= 0;
    }
}