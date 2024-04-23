package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
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

    protected String getAckerId() {
        return ackerId;
    }

    protected Name getName() {
        return name;
    }

    protected Shape getShape() {
        return shape;
    }

    protected List<ATunnel> getTunnels() {
        return tunnels;
    }

    protected List<ABeet> getBeete() {
        return beete;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    protected void setTunnels(List<ATunnel> tunnels) {
        this.tunnels = tunnels;
    }

    protected void setBeete(List<ABeet> beete) {
        this.beete = beete;
    }

    @Override
    public abstract String toString();
}
