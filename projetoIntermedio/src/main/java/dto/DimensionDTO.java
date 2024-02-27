package dto;

public class DimensionDTO {

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
     * DimensionDTO constructor with width, length and height.
     *
     * @param width the width of the room
     * @param length the length of the room
     * @param height the height of the room
     */
    public DimensionDTO(double width, double length, double height) {
        this.width = width;
        this.length = length;
        this.height = height;
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
