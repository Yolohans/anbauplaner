package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shape_type")
public abstract class Shape implements Collidable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shapeId;

    public Shape() {}

    public UUID getShapeId() {
        return shapeId;
    }

    public abstract Point getPosition();

    public abstract Shape translatePosition(Point position);

    public abstract Shape subtractPosition(Point position);

    public abstract Shape replacePosition(Point position);

    public abstract boolean collidesWith(Shape other);

    public abstract boolean collidesWithRectangle(Rectangle other);

    public abstract boolean fitsIn(Shape other);

    public abstract boolean fitInRectangle(Rectangle other);

    public abstract boolean covers(Shape other);

    public abstract boolean coversRectangle(Rectangle other);

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public Shape getShape() {
        return this;
    }
}
