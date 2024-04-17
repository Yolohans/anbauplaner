package de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ATunnel {
    @Id
    protected String tunnelId;
    @Convert(converter = NameAttributeConverter.class)
    protected Name name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    protected Shape shape;
    @ManyToOne
    @JoinColumn(name = "ackerId")
    protected AAcker acker;

    public ATunnel(String id, Name name) {
        this.tunnelId = id;
        this.name = name;
    }

    protected ATunnel() {
    }

    public String getId() {
        return tunnelId;
    }

    public Name getName() {
        return name;
    }


}
