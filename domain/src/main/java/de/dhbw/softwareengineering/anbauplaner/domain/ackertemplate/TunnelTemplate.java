package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Beet;
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

    protected void createBeetAtPosition(BeetTemplate beet) {
        this.beete.put(beet.getBeetId(),beet);
    }

    protected void attachBeetAtPosition(BeetTemplate beet, Point targetPosition) {
        Shape beetShapeInTunnel = beet.getShape().replacePosition(targetPosition);

        if (beetShapeInTunnel.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(beet,this,"Position and dimension of the beet in it's final position exceed the acker's dimensions.");
        }

        List<Collidable> collidables = getCollidables();
        if (!beetShapeInTunnel.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(beet, collidables, "In it's final position beet collides with other beete.");
        }

        beet.setShape(beetShapeInTunnel);
        this.beete.put(beet.getBeetId(), beet);
    }

    protected void moveBeetToPosition(BeetTemplate beet, Point targetPosition) {
        Shape targetShape = beet.getShape().replacePosition(targetPosition);

        if (targetShape.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(beet,this,"The beet does not fit at the targeted position.");
        }

        List<Collidable> collidables = getCollidables();
        if (!targetShape.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(beet, collidables, "The beet collides with other beete at the targeted position.");
        }

        beet.setShape(targetShape);
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

    protected void setAckerId(UUID ackerId) {
        this.ackerId = ackerId;
    }

    protected List<Collidable> getCollidables() {
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
