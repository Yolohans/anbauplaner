package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
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

    @OneToMany(mappedBy = "acker", cascade=CascadeType.ALL)
    private HashMap<UUID,ATunnel> tunnels;

    @OneToMany(mappedBy = "acker")
    private HashMap<UUID,ABeet> beete;

    protected AAcker(){}

    protected AAcker(Name name, Shape shape) {
        this.name = name;
        this.shape = shape;
        this.tunnels = new HashMap<UUID,ATunnel>();
        this.beete = new HashMap<UUID,ABeet>();
    }

    protected UUID getAckerId() {
        return ackerId;
    }

    protected Name getName() {
        return name;
    }

    protected Shape getShape() {
        return shape;
    }

    protected HashMap<UUID,ATunnel> getTunnels() {
        return tunnels;
    }

    protected HashMap<UUID,ABeet> getBeete() {
        return beete;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    protected void setTunnels(HashMap<UUID,ATunnel> tunnels) {
        this.tunnels = tunnels;
    }

    protected void setBeete(HashMap<UUID,ABeet> beete) {
        this.beete = beete;
    }

    protected void addBeet(ABeet beet) {
        this.getBeete().put(beet.getBeetId(), beet);
    }

    protected void addTunnel(ATunnel tunnel) {
        this.getTunnels().put(tunnel.getTunnelId(), tunnel);
    }

    protected void removeBeet(ABeet beet) {
        this.getBeete().remove(beet.getBeetId());
    }

    protected void removeBeet(UUID beetId) {
        this.getBeete().remove(beetId);
    }

    protected void removeTunnel(ATunnel tunnel) {
        this.getTunnels().remove(tunnel.getTunnelId());
    }

    protected void removeTunnel(UUID tunnelId) {
        this.getTunnels().remove(tunnelId);
    }

    @Override
    public abstract String toString();
}
