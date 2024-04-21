package de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(mappedBy = "tunnel")
    protected List<ABeet> Beete;

    protected String getId() {
        return tunnelId;
    }

    protected Name getName() {
        return name;
    }

    protected String getTunnelId() {
        return tunnelId;
    }

    protected Shape getShape() {
        return shape;
    }

    protected AAcker getAcker() {
        return acker;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public abstract String toString();
}
