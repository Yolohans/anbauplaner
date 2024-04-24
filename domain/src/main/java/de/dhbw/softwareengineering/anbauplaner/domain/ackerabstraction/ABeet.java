package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABeet {
    @Id
    @GeneratedValue
    private UUID beetId;
    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;
    @ManyToOne
    @JoinColumn(name = "ackerId")
    private AAcker acker;
    @ManyToOne
    @JoinColumn(name = "tunnelId")
    private ATunnel tunnel;

    protected ABeet() {}

    protected ABeet(Name name, Shape shape, AAcker acker, ATunnel tunnel) {
        this.name = name;
        this.shape = shape;
        this.acker = acker;
        this.tunnel = tunnel;
    }

    protected UUID getBeetId() {
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

    protected void setAcker(AAcker acker) {
        this.acker = acker;
    }

    @Override
    public abstract String toString();
}
