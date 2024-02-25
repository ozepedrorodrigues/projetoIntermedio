package factories;

import domain.Dimensions;

/**
 * A factory interface for creating Dimensions objects.
 * This interface provides a method to create a Dimensions object with specified width, length, and height.
 */
public interface DimensionsFactory {
    /**
     * Creates a Dimensions object with the specified width, length, and height.
     *
     * @param width  the width of the room (must be a non-negative value)
     * @param length the length of the room (must be a non-negative value)
     * @param height the height of the room
     * @return a Dimensions object with the specified width, length, and height
     * @throws IllegalArgumentException if any of the dimensions are non-positive
     */
    Dimensions createDimensions(double width, double length, double height);
}
