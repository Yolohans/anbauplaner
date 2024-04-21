package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shapeId;

    @Column
    protected String shapeType;

    public Shape() {}

    public Shape(String shapeType){
        this.shapeType = shapeType;
    }

    public UUID getShapeId() {
        return shapeId;
    }

    public String getShapeType() {
        return shapeType;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
