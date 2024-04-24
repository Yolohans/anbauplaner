package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AAcker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ackerId;

    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    @OneToMany(mappedBy = "acker")
    private List<ATunnel> tunnels;

    @OneToMany(mappedBy = "acker")
    private List<ABeet> beete;

    public AAcker(){}

    public AAcker(Name name, Shape shape) {
        this.name = name;
        this.shape = shape;
        this.tunnels = new ArrayList<ATunnel>();
        this.beete = new ArrayList<ABeet>();
    }

    public UUID getAckerId() {
        return ackerId;
    }

    public Name getName() {
        return name;
    }

    public Shape getShape() {
        return shape;
    }

    public List<ATunnel> getTunnels() {
        return tunnels;
    }

    public List<ABeet> getBeete() {
        return beete;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setTunnels(List<ATunnel> tunnels) {
        this.tunnels = tunnels;
    }

    public void setBeete(List<ABeet> beete) {
        this.beete = beete;
    }

    public void addTunnel(ATunnel tunnel) {
        this.tunnels.add(tunnel);
    }

    public void addBeet(ABeet beet) {
        this.beete.add(beet);
    }

    public boolean removeTunnel(ATunnel tunnel) {
        return this.tunnels.remove(tunnel);
    }

    public boolean removeBeet(ABeet beet) {
        return this.beete.remove(beet);
    }

    @Override
    public abstract String toString();
}
