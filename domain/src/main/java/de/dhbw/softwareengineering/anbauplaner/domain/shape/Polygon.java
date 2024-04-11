package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Objects;

@Entity
public final class Polygon extends Shape {


    private final String shapeType = "polygon";

    @ElementCollection
    private ArrayList<Point> points;

    public Polygon() {}

    public Polygon(ArrayList<Point> points) {
        this.points = points;
    }

    public String getShapeType() {
        return shapeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        return Objects.equals(getShapeType(), polygon.getShapeType()) && Objects.equals(points, polygon.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShapeType(), points);
    }
}
