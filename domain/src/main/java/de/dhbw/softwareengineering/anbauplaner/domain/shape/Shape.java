package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

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

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
