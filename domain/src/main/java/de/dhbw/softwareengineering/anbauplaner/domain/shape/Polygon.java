package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Entity
public final class Polygon extends Shape {

/*
    @ElementCollection
    private ArrayList<Point> points;
*/

    public Polygon() {
        super.shapeType = "polygon";
    }

  /*  public Polygon(ArrayList<Point> points) {
        this.points = points;
    }*/

    public String getShapeType() {
        return shapeType;
    }

   /* @Override
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
*/
}
