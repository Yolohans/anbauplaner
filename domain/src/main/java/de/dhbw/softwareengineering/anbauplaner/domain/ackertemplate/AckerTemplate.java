package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Tunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Entity
public class AckerTemplate extends AAcker {
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private Boolean collisionManagementActive;

    public AckerTemplate(Name name, Shape shape) {
        super(name, shape);
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.lastUpdateAt = now;
        this.collisionManagementActive = false;
    }

    public AckerTemplate(Name name) {
        this(name, new Rectangle(1000.,750., new Point(0.,0.)));
    }

    public AckerTemplate() {}


    public void createTunnel(Name name, Shape shape) {
        TunnelTemplate tunnel = new TunnelTemplate(name, shape, this);
        this.addTunnel(tunnel);
    }

    public void createBeetInAcker(Name name, Shape shape) {
        BeetTemplate beet = new BeetTemplateFactory()
                .withAcker(this)
                .withName(name)
                .withShape(shape)
                .build();
        this.addBeet(beet);
    }

    public void createBeetInTunnel(Name name, Shape shape, TunnelTemplate tunnel) {
        BeetTemplate beet = new BeetTemplateFactory()
                .withTunnel(tunnel)
                .withName(name)
                .withShape(shape)
                .build();
        tunnel.add(beet);
    }

    public void deleteBeet(BeetTemplate beet) {
        if (beet.getTunnel() != null) {
            beet.getTunnel().remove(beet);
        }
        if (beet.getAcker() != null) {
            this.removeBeet(beet);
        }
    }

    public void deleteTunnel(UUID tunnelId, boolean keepBeete) {
        ATunnel tunnel = this.getTunnels().get(tunnelId);
        HashMap<UUID,ABeet> beete = tunnel.getBeete();
        if (keepBeete) {
            for (HashMap.Entry<UUID,ABeet> beet : tunnel.getBeete().entrySet()) {
                BeetTemplate beetTemplate = (BeetTemplate) beet;
                beetTemplate.removeTunnel();
            }
        }
        this.removeTunnel(tunnelId);
    }


    public void moveTunnelToPosition(UUID tunnelId, Point position) {
        //collision
        //outofbounds
    }

    //TODO tunnelId
    public void moveBeetToTunnel(BeetTemplate beet, TunnelTemplate tunnel) {
        if (beet.getTunnel() != null) {
            beet.getTunnel().remove(beet);
        }
        beet.setTunnel(tunnel);
        tunnel.add(beet);
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    protected LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    protected Boolean getCollisionManagementActive() {
        return collisionManagementActive;
    }

    protected void setLastUpdateAt() {
        this.lastUpdateAt = LocalDateTime.now();
    }

    protected void setCollisionManagementActive(Boolean collisionManagementActive) {
        this.collisionManagementActive = collisionManagementActive;
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
        final StringBuilder sb = new StringBuilder("AckerTemplate{");
        sb.append("ackerId='").append(this.getAckerId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", tunnels=").append(this.getTunnels());
        sb.append(", beete=").append(this.getBeete());
        sb.append('}');
        return sb.toString();
    }
}
