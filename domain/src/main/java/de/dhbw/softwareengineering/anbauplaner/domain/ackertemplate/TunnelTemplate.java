package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.ChildDoesNotFitException;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.CollisionException;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Entity
public class TunnelTemplate implements Collidable {
    @Id
    @GeneratedValue
    private UUID tunnelId;

    @Convert(converter = NameAttributeConverter.class)
    private Name name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    private UUID ackerId;

    @OneToMany
    protected HashMap<UUID, BeetTemplate> beete;

    private LocalDateTime createdAt;

    protected TunnelTemplate() {
    }

    protected TunnelTemplate(Name name, Shape shape, UUID ackerId) {
        this.name = name;
        this.shape = shape;
        this.ackerId = ackerId;
        this.beete = new HashMap<UUID, BeetTemplate>();
        this.createdAt = LocalDateTime.now();
    }

    protected void moveToPosition(Point position) {
        shape = shape.replacePosition(position);
    }

    protected void attachBeetAtPosition(BeetTemplate beet, Point position) {
        if (beet.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(beet,this,"Position and dimension of the beet exceed the acker's dimensions.");
        }

        List<Collidable> collidables = getCollidables();
        if (!beet.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(beet, collidables, "Beet collides with other beete.");
        }

        this.beete.put(beet.getBeetId(),beet);
    }

    protected void removeBeetById(UUID beetId) {
        this.beete.remove(beetId);
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getTunnelId() {
        return tunnelId;
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

    public HashMap<UUID, BeetTemplate> getBeete() {
        return beete;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setAckerId(UUID ackerId) {
        this.ackerId = ackerId;
    }

    private List<Collidable> getCollidables() {
        List<Collidable> collidables = new ArrayList<>();
        for (Collidable elem : this.getBeete().values()) {
            collidables.add(elem);
        }
        return collidables;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TunnelTemplate{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", tunnelId='").append(this.getTunnelId()).append('\'');
        sb.append(", name=").append(this.getTunnelId());
        sb.append(", shape=").append(this.getShape());
        sb.append(", acker=").append(this.getAckerId());
        sb.append(", Beete=").append(this.getBeete());
        sb.append('}');
        return sb.toString();
    }
}
