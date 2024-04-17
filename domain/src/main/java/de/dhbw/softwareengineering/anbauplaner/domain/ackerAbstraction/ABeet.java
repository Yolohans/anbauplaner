package de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABeet {
    @Id
    protected String beetId;
    @Convert(converter = NameAttributeConverter.class)
    protected Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    protected Shape shape;
    @ManyToOne
    @JoinColumn(name = "ackerId")
    protected AAcker acker;

    @ManyToOne
    @JoinColumn(name = "tunnelId")
    protected ATunnel tunnel;

    public ABeet(String id, Name name) {
        this.beetId = id;
        this.name = name;
    }

    protected ABeet() {
    }

    public String getId() {
        return beetId;
    }

    public Name getName() {
        return name;
    }


}
