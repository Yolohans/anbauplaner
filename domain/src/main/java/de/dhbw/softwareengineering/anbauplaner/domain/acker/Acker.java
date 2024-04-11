package de.dhbw.softwareengineering.anbauplaner.domain.acker;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Polygon;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

@Entity
@Table(name = "acker")
public class Acker {

    @Id
    private String id;
    @Column
    private String name;

    private Shape shape;

    public Acker() {

    }

    public Acker(String name, Polygon polygon) {
        this.id = id;
        this.name = name;
        this.shape = polygon;
    }




    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
