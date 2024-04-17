package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public final class Point{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    protected Point() {
        //for Spring JPA
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    //for Spring JPA --> private such that class becomes immutable in public
    private void setX(double x) {
        this.x = x;
    }

    //for Spring JPA --> private such that class becomes immutable in public
    private void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Double.compare(point.getX(), getX()) == 0 && Double.compare(point.getY(), getY()) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
