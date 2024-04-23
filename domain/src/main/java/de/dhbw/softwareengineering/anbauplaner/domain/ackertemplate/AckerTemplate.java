package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AckerTemplate extends AAcker {
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private Boolean collisionManagementActive = false;

    public AckerTemplate() {}
    public AckerTemplate(Name name, List<ATunnel> tunnels, List<ABeet> beete) {
        LocalDateTime now = LocalDateTime.now();
        this.name = name;
        this.tunnels = tunnels;
        this.beete = beete;
        this.createdAt = now;
        this.lastUpdateAt = now;
    }

    @Override
    public String getAckerId() {
        return ackerId;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public List<ATunnel> getTunnels() {
        return tunnels;
    }

    @Override
    public List<ABeet> getBeete() {
        return beete;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public Boolean getCollisionManagementActive() {
        return collisionManagementActive;
    }

    @Override
    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void setTunnels(List<ATunnel> tunnels) {
        this.tunnels = tunnels;
    }

    @Override
    public void setBeete(List<ABeet> beete) {
        this.beete = beete;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastUpdateAt(LocalDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public void setCollisionManagementActive(Boolean collisionManagementActive) {
        this.collisionManagementActive = collisionManagementActive;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AckerTemplate{");
        sb.append("ackerId='").append(ackerId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", shape=").append(shape);
        sb.append(", tunnels=").append(tunnels);
        sb.append(", beete=").append(beete);
        sb.append('}');
        return sb.toString();
    }
}
