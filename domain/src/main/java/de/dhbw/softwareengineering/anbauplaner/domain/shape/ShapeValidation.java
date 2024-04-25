package de.dhbw.softwareengineering.anbauplaner.domain.shape;

public class ShapeValidation {
    public static boolean fitsIn(Rectangle child, Rectangle parent) {
        boolean dimensionsFit = child.getXLength() <= parent.getXLength() && child.getYLength() <= parent.getYLength();
        return dimensionsFit;
    }

    public static boolean isIn(Rectangle child, Rectangle parent) {
        boolean positionFits =
                parent.getPosition().getX() <= child.getPosition().getX() &&
                parent.getPosition().getY() <= child.getPosition().getY() &&
                parent.getPosition().getX() + parent.getXLength() >= child.getPosition().getX() + child.getXLength() &&
                parent.getPosition().getY() + parent.getYLength() >= child.getPosition().getY() + child.getYLength();
        return positionFits;
    }
}
