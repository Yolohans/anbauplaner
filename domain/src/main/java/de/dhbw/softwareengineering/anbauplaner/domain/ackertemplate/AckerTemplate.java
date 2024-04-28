package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.ChildDoesNotFitException;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.CollisionException;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.ParentDoesNotCoverException;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Entity
public class AckerTemplate implements Collidable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ackerId;

    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    @OneToMany(mappedBy = "ackerId", cascade=CascadeType.ALL)
    private HashMap<UUID,TunnelTemplate> tunnels;

    @OneToMany(mappedBy = "ackerId")
    private HashMap<UUID,BeetTemplate> beete;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;

    public AckerTemplate(Name name, Shape shape) {
        this.name = name;
        this.shape = shape;
        this.tunnels = new HashMap<UUID,TunnelTemplate>();
        this.beete = new HashMap<UUID,BeetTemplate>();
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.lastUpdateAt = now;
    }

    public AckerTemplate(Name name) {
        this(name, new Rectangle(1000.,750., new Point(0.,0.)));
    }

    public AckerTemplate() {}

    public void createTunnel(Name name, Shape shape) {

        TunnelTemplate tunnel = new TunnelTemplate(name, shape, this.getAckerId());

        if (tunnel.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(tunnel,this,"Position and dimension of the tunnel exceed the acker's dimensions.");
        }

        List<Collidable> colliders = tunnel.collidesWith(getCollidables());
        if (!colliders.isEmpty()) {
            throw new CollisionException(tunnel, colliders, "Tunnel collides with other tunnels or beete.");
        }

        this.addTunnel(tunnel);
    }

    public void createBeetInAcker(Name name, Shape beetShape) {
        if (beetShape.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(beetShape,this,"Position and dimension of the beet exceed the acker's dimensions.");
        }

        List<Collidable> colliders = beetShape.collidesWith(this.getCollidables());
        if (!colliders.isEmpty()) {
            throw new CollisionException(beetShape, colliders, "Beet collides with other tunnels or beete.");
        }

        BeetTemplate beet = new BeetTemplateFactory()
                .withAckerId(this.getAckerId())
                .withName(name)
                .withShape(beetShape)
                .build();

        this.addBeet(beet);
    }

    public void createBeetInTunnel(Name name, Shape beetShape, UUID tunnelId) {
        TunnelTemplate tunnel = this.getTunnelById(tunnelId);

        if (beetShape.doesNotFitInto(tunnel)) {
            throw new ChildDoesNotFitException(beetShape,this,"Position and dimension of the beet's shape exceed the acker's dimensions.");
        }

        List<Collidable> colliders = beetShape.collidesWith(tunnel.getCollidables());
        if (!colliders.isEmpty()) {
            throw new CollisionException(beetShape, colliders, "The beet's shape collides with other beete.");
        }

        BeetTemplate beet = new BeetTemplateFactory()
                .withTunnelId(tunnelId)
                .withName(name)
                .withShape(beetShape)
                .build();

        tunnel.createBeetAtPosition(beet);
    }

    public void deleteBeet(UUID beetId) {
        UUID tunnelId = this.getBeetById(beetId).getTunnelId();

        if (tunnelId != null) {
            this.getTunnelById(tunnelId).removeBeetById(beetId);
        } else if (this.getBeetById(beetId) != null) {
            this.removeBeetById(beetId);
        }
    }

    public void deleteTunnel(UUID tunnelId, boolean keepBeete) {
        TunnelTemplate tunnel = this.getTunnelById(tunnelId);

        if (keepBeete) {
            for (HashMap.Entry<UUID, BeetTemplate> entry : tunnel.getBeete().entrySet()) {
                BeetTemplate beet = entry.getValue();
                beet.detachFromTunnel(tunnel);
            }
        }
        this.removeTunnelById(tunnelId);
    }

    public void attachBeetToTunnelAtPosition(UUID beetId, UUID targetTunnelId, Point position) {
        // future: collision and inbound validation in TunnelTemplate --> shall I put it here?
        BeetTemplate beet = this.getBeetById(beetId);
        TunnelTemplate sourceTunnel = this.getTunnelById(beet.getTunnelId());
        TunnelTemplate targetTunnel = this.getTunnelById(targetTunnelId);

        if (targetTunnel != null && beet != null) {
            targetTunnel.attachBeetAtPosition(beet, position);
            if (sourceTunnel != null) {
                sourceTunnel.removeBeetById(beetId);
            }
            beet.attachToTunnel(targetTunnel, position);
        }
    }

    public void moveBeetToPosition(UUID beetId, Point position) {
        BeetTemplate beet = this.getBeetById(beetId);
        UUID tunnelId = beet.getTunnelId();
        Shape targetShape = beet.getShape().replacePosition(position);

        if (tunnelId != null) {
            TunnelTemplate tunnel = this.getTunnelById(tunnelId);
            tunnel.moveBeetToPosition(beet, position);
        } else {
            if (targetShape.doesNotFitInto(this)) {
                throw new ChildDoesNotFitException(targetShape,this,"Position and dimension of the beet exceed the acker's dimensions.");
            }

            List<Collidable> colliders = targetShape.collidesWith(this.getCollidables());
            if (!colliders.isEmpty()) {
                throw new CollisionException(targetShape, colliders, "Beet collides with other tunnels or beete.");
            }

            beet.moveToPosition(position);
        }

    }

    public void moveTunnelToPosition(UUID tunnelId, Point position) {
        TunnelTemplate tunnel = this.getTunnelById(tunnelId);
        Shape targetShape = tunnel.getShape().replacePosition(position);

        if (targetShape.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(targetShape,this,"Position and dimension of the tunnel exceed the acker's dimensions.");
        }

        List<Collidable> colliders = targetShape.collidesWith(this.getCollidables());
        if (!colliders.isEmpty()) {
            throw new CollisionException(targetShape, colliders, "Tunnel collides with other tunnels or beete.");
        }

        tunnel.moveToPosition(position);
    }

    public void changeName(Name name) {
        this.name = name;
    }

    public void changeAckerDimension(double x, double y) {
        Shape redimensionedShape = new Rectangle(x,y,this.shape.getPosition());
        List<Collidable> containedColliders = redimensionedShape.doesNotCover(this.getCollidables());

        if(!containedColliders.isEmpty()) {
            throw new ParentDoesNotCoverException(redimensionedShape, containedColliders, "After redimensioning the acker does not cover its children.");
        }
        this.shape = redimensionedShape;
    }

    public void changeTunnelDimensions(double x, double y, UUID tunnelId) {
        TunnelTemplate tunnel = this.getTunnelById(tunnelId);
        Shape redimensionedShape = tunnel.getShape();
        List<Collidable> containedColliders = redimensionedShape.doesNotCover(tunnel.getCollidables());
        List<Collidable> colliders = redimensionedShape.collidesWith(this.getCollidables());

        if(!containedColliders.isEmpty()) {
            throw new ParentDoesNotCoverException(redimensionedShape, containedColliders, "After redimensioning the tunnel does not cover its children.");
        }

        if(redimensionedShape.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(redimensionedShape, this, "Position and dimension of the redimensioned tunnel exceed the acker's dimensions.");
        }

        if(!colliders.isEmpty()) {
            throw new CollisionException(redimensionedShape,colliders,"Redimensioned tunnel collides with other beete or tunnel.");
        }

        tunnel.setShape(redimensionedShape);
    }

    public void changeBeetDimensions(double x, double y, UUID beetId) {
        BeetTemplate beet = this.getBeetById(beetId);
        Shape redimensionedShape = beet.getShape();
        UUID tunnelId = beet.getTunnelId();
        List<Collidable> colliders;
        Collidable parent;

        if (tunnelId != null) {
            TunnelTemplate tunnel = this.getTunnelById(tunnelId);
            parent = tunnel;
            colliders = redimensionedShape.collidesWith(tunnel.getCollidables());
        } else {
            colliders = redimensionedShape.collidesWith(this.getCollidables());
            parent = this;
        }

        if(redimensionedShape.doesNotFitInto(parent)) {
            throw new ChildDoesNotFitException(redimensionedShape, parent, "Position and dimension of the redimensioned beet exceed the parents dimensions.");
        }

        if(!colliders.isEmpty()) {
            throw new CollisionException(redimensionedShape,colliders,"Redimensioned beet collides with other beete or tunnels.");
        }

        beet.setShape(redimensionedShape);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public UUID getAckerId() {
        return ackerId;
    }

    public Name getName() {
        return name;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public TunnelTemplate getTunnelById(UUID tunnelId) {
        return this.getTunnels().get(tunnelId);
    }

    public BeetTemplate getBeetById(UUID beetId) {
        return this.getBeete().get(beetId);
    }

    public HashMap<UUID, TunnelTemplate> getTunnels() {
        return tunnels;
    }

    public HashMap<UUID, BeetTemplate> getBeete() {
        return beete;
    }

    private void addBeet(BeetTemplate beet) {
        this.getBeete().put(beet.getBeetId(), beet);
    }

    private void addTunnel(TunnelTemplate tunnel) {
        this.getTunnels().put(tunnel.getTunnelId(), tunnel);
    }

    private void removeBeetById(UUID beetId) {
        this.getBeete().remove(beetId);
    }

    private void removeTunnelById(UUID tunnelId) {
        this.getTunnels().remove(tunnelId);
    }

    private List<Collidable> getCollidables() {
        List<Collidable> collidables = new ArrayList<>();
        for (Collidable elem : this.getTunnels().values()) {
            collidables.add(elem);
        }
        for (Collidable elem : this.getBeete().values()) {
            collidables.add(elem);
        }
        return collidables;
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
