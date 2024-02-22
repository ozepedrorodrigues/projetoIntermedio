package Factories;

import Domain.Dimensions;

public interface DimensionsFactory {
    Dimensions createDimensions(double width, double length, double height);
}
