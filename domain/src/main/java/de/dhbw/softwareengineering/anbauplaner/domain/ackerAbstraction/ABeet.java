package de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.converters.NameAttributeConverter;
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

    protected String getId() {
        return beetId;
    }

    protected Name getName() {
        return name;
    }

    protected Shape getShape() {
        return shape;
    }

    protected AAcker getAcker() {
        return acker;
    }

    protected ATunnel getTunnel() {
        return tunnel;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    protected void setTunnel(ATunnel tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public abstract String toString();
}
