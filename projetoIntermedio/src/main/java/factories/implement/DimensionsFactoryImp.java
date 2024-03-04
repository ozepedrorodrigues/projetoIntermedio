package factories.implement;

import domain.Dimensions;
import factories.DimensionsFactory;

/**
 * An implementation of the DimensionsFactory interface.
 * This class provides a concrete implementation of the createDimensions method.
 */
public class DimensionsFactoryImp implements DimensionsFactory {
    /**
     * Default constructor for DimensionsFactoryImp.
     */
    public DimensionsFactoryImp() {
    }

    /**
     * Creates a Dimensions object with the specified width, length, and height.
     *
     * @param width  the width of the room (must be a non-negative value)
     * @param length the length of the room (must be a non-negative value)
     * @param height the height of the room
     * @return a Dimensions object with the specified width, length, and height
     * @throws IllegalArgumentException if any of the dimensions are non-positive
     */
    public Dimensions createDimensions(double width, double length, double height) {
            return new Dimensions(width, length, height);}
}
