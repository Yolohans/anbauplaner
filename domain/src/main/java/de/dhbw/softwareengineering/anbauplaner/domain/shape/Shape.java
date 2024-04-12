package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private UUID shapeId;

    @Column
    protected String shapeType;

    public Shape() {}

    public UUID getShapeId() {
        return shapeId;
    }

    public String getShapeType() {
        return shapeType;
    }

}
