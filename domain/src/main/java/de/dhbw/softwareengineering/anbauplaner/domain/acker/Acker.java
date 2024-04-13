package de.dhbw.softwareengineering.anbauplaner.domain.acker;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

@Entity
@Table(name = "acker")
public class Acker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ackerId;
    @Column
    private String name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    public Acker() {

    }

    public Acker(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }




    public String getAckerId() {
        return ackerId;
    }

    public String getName() {
        return name;
    }
}
