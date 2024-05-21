package de.dhbw.softwareengineering.anbauplaner.adapters.shape.representations;

public class PointDTO {
    private double x;
    private double y;

    public PointDTO(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
