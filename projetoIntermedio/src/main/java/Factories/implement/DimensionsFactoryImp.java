package Factories.implement;

import Domain.Dimensions;
import Factories.DimensionsFactory;

public class DimensionsFactoryImp implements DimensionsFactory {
    public DimensionsFactoryImp() {
    }

    public Dimensions createDimensions(double width, double length, double height) {
        try{return new Dimensions(width, length, height);}
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());}
    }
}
