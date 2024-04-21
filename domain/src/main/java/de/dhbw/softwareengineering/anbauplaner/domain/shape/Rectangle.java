package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.Entity;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

@Entity
public final class Rectangle extends Shape {

    private Double xLength;
    private Double yLength;
    private Point position;

    public Rectangle() {
        super("rectangle");
    }

    public Rectangle(Double xLength, Double yLength, Point position) {
        super("rectangle");
        Objects.requireNonNull(xLength, "Double value cannot be null");
        Objects.requireNonNull(yLength, "Double value cannot be null");
        Objects.requireNonNull(position, "Double value cannot be null");
        Validate.isTrue(xLength > 0, "xLength must be larger than 0");
        Validate.isTrue(yLength > 0, "yLength must be larger than 0");

        this.xLength = xLength;
        this.yLength = yLength;
        this.position = position;
    }

    public Double getXLength() {
        return xLength;
    }

    public Double getYLength() {
        return yLength;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(getXLength(), rectangle.getXLength()) && Objects.equals(getYLength(), rectangle.getYLength()) && Objects.equals(getPosition(), rectangle.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getXLength(), getYLength(), getPosition());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rectangle{");
        sb.append("xLength=").append(xLength);
        sb.append(", yLength=").append(yLength);
        sb.append(", position=").append(position);
        sb.append(", shapeType='").append(shapeType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}