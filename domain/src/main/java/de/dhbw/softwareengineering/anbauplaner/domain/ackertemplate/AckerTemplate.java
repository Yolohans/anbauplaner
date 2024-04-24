package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class AckerTemplate extends AAcker {
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private Boolean collisionManagementActive;

    public AckerTemplate() {}

    public AckerTemplate(Name name, Shape shape) {
        super(name, shape);
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.lastUpdateAt = now;
        this.collisionManagementActive = false;
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

    public void setLastUpdateAt() {
        this.lastUpdateAt = LocalDateTime.now();
    }

    public void setCollisionManagementActive(Boolean collisionManagementActive) {
        this.collisionManagementActive = collisionManagementActive;
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
