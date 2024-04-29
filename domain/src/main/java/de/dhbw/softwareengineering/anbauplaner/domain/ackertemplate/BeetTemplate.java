package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BeetTemplate implements Collidable {
    @Id
    @GeneratedValue
    private UUID beetId;
    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    private UUID ackerId;

    private UUID tunnelId;

    private LocalDateTime createdAt;

    protected BeetTemplate() {
        this.createdAt = LocalDateTime.now();
    }

    public BeetTemplate(Name name, Shape shape, UUID tunnelId, UUID ackerId) {
        this.name = name;
        this.shape = shape;
        this.ackerId = ackerId;
        this.tunnelId = tunnelId;
        this.createdAt = LocalDateTime.now();
    }

    protected void detachFromTunnel(TunnelTemplate tunnel) {
        if (this.tunnelId == tunnel.getTunnelId()) {
            this.tunnelId = null;
            this.ackerId = tunnel.getAckerId();
            this.shape = shape.translatePosition(tunnel.getShape().getPosition());
        }
    }

    protected void attachToTunnel(TunnelTemplate tunnel, Point position) {
        this.ackerId = null;
        this.tunnelId = tunnel.getTunnelId();
        this.shape = shape.replacePosition(position);
    }

    protected void moveToPosition(Point position) {
        this.shape = this.shape.replacePosition(position);
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getBeetId() {
        return beetId;
    }

    public Name getName() {
        return name;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public UUID getAckerId() {
        return ackerId;
    }

    public UUID getTunnelId() {
        return tunnelId;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    protected void setAckerId(UUID ackerId) {
        this.ackerId = ackerId;
    }

    protected void setTunnelId(UUID tunnelId) {
        this.tunnelId = tunnelId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BeetTemplate{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", beetId='").append(this.getBeetId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", acker=").append(this.getAckerId());
        sb.append(", tunnel=").append(this.getTunnelId());
        sb.append('}');
        return sb.toString();
    }
}
