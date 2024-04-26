package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.UUID;

@Entity
public class Acker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ackerId;

    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    @OneToMany(mappedBy = "acker", cascade=CascadeType.ALL)
    private HashMap<UUID,Tunnel> tunnels;

    @OneToMany(mappedBy = "acker")
    private HashMap<UUID,Beet> beete;

    @ManyToOne
    @JoinColumn(name = "anbauplanId")
    protected Anbauplan anbauplan;

    protected Acker() {}

    protected Acker(Name name, Shape shape, Anbauplan anbauplan) {
        this.name = name;
        this.shape = shape;
        this.tunnels = new HashMap<UUID, Tunnel>();
        this.beete = new HashMap<UUID, Beet>();
        this.anbauplan = anbauplan;
    }

    protected Anbauplan getAnbauplan() {
        return anbauplan;
    }

    protected void setAnbauplan(Anbauplan anbauplan) {
        this.anbauplan = anbauplan;
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

    public HashMap<UUID, Tunnel> getTunnels() {
        return tunnels;
    }

    public HashMap<UUID, Beet> getBeete() {
        return beete;
    }

    protected void addBeet(Beet beet) {
        this.getBeete().put(beet.getBeetId(), beet);
    }

    protected void addTunnel(Tunnel tunnel) {
        this.getTunnels().put(tunnel.getTunnelId(), tunnel);
    }

    protected void removeBeet(Beet beet) {
        this.getBeete().remove(beet.getBeetId());
    }

    protected void removeBeet(UUID beetId) {
        this.getBeete().remove(beetId);
    }

    protected void removeTunnel(Tunnel tunnel) {
        this.getTunnels().remove(tunnel.getTunnelId());
    }

    protected void removeTunnel(UUID tunnelId) {
        this.getTunnels().remove(tunnelId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acker{");
        sb.append("anbauplan=").append(anbauplan);
        sb.append(", ackerId='").append(this.getAckerId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", tunnels=").append(this.getTunnels());
        sb.append(", beete=").append(this.getBeete());
        sb.append('}');
        return sb.toString();
    }
}
