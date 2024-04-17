package de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AAcker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String ackerId;

    @Convert(converter = NameAttributeConverter.class)
    protected Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    protected Shape shape;

    @OneToMany(mappedBy = "acker")
    protected List<ATunnel> tunnels;

    @OneToMany(mappedBy = "acker")
    protected List<ABeet> beete;
    public AAcker() {
    }

    public AAcker(Name name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }


    public String getAckerId() {
        return ackerId;
    }

    public Name getName() {
        return name;
    }
}
