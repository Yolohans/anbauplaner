package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shape_type")
public abstract class Shape {
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

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
