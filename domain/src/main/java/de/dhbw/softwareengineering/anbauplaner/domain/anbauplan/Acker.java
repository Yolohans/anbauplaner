package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.UUID;

@Entity
public class Acker extends AAcker {
    @ManyToOne
    @JoinColumn(name = "anbauplanId")
    protected Anbauplan anbauplan;

    protected Acker() {}

    protected Acker(Name name, Shape shape, Anbauplan anbauplan) {
        super(name,shape);
        this.anbauplan = anbauplan;
    }

    protected Anbauplan getAnbauplan() {
        return anbauplan;
    }

    protected void setAnbauplan(Anbauplan anbauplan) {
        this.anbauplan = anbauplan;
    }

    protected void addTunnel(Tunnel tunnel) {
        this.getTunnels().put(tunnel.getTunnelId(),tunnel);
    }

    protected void addBeet(Beet beet) {
        this.getBeete().put(beet.getBeetId(), beet);
    }

    protected void removeTunnel(Tunnel tunnel) {
        this.getTunnels().remove(tunnel.getTunnelId());
    }

    protected void removeBeet(Beet beet) {
        this.getBeete().remove(beet.getBeetId());
    }

    @Override
    protected UUID getAckerId() {
        return super.getAckerId();
    }

    @Override
    protected Name getName() {
        return super.getName();
    }

    @Override
    protected Shape getShape() {
        return super.getShape();
    }

    @Override
    protected HashMap<UUID, ATunnel> getTunnels() {
        return super.getTunnels();
    }

    @Override
    protected HashMap<UUID, ABeet> getBeete() {
        return super.getBeete();
    }

    @Override
    protected void setName(Name name) {
        super.setName(name);
    }

    @Override
    protected void setShape(Shape shape) {
        super.setShape(shape);
    }

    @Override
    protected void setTunnels(HashMap<UUID, ATunnel> tunnels) {
        super.setTunnels(tunnels);
    }

    @Override
    protected void setBeete(HashMap<UUID, ABeet> beete) {
        super.setBeete(beete);
    }

    @Override
    protected void addBeet(ABeet beet) {
        super.addBeet(beet);
    }

    @Override
    protected void addTunnel(ATunnel tunnel) {
        super.addTunnel(tunnel);
    }

    @Override
    protected void removeBeet(ABeet beet) {
        super.removeBeet(beet);
    }

    @Override
    protected void removeBeet(UUID beetId) {
        super.removeBeet(beetId);
    }

    @Override
    protected void removeTunnel(ATunnel tunnel) {
        super.removeTunnel(tunnel);
    }

    @Override
    protected void removeTunnel(UUID tunnelId) {
        super.removeTunnel(tunnelId);
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
