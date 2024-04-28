package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.ChildDoesNotFitException;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.CollisionException;
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

    @OneToMany(mappedBy = "acker", cascade=CascadeType.ALL)
    private HashMap<UUID,TunnelTemplate> tunnels;

    @OneToMany(mappedBy = "acker")
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

        List<Collidable> collidables = getCollidables();
        if (!tunnel.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(tunnel, collidables, "Tunnel collides with other tunnels or beete.");
        }

        this.addTunnel(tunnel);
    }

    public void createBeetInAcker(Name name, Shape shape) {
        if (shape.doesNotFitInto(this)) {
            throw new ChildDoesNotFitException(shape,this,"Position and dimension of the beet exceed the acker's dimensions.");
        }

        List<Collidable> collidables = getCollidables();
        if (!shape.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(shape, collidables, "Beet collides with other tunnels or beete.");
        }

        BeetTemplate beet = new BeetTemplateFactory()
                .withAckerId(this.getAckerId())
                .withName(name)
                .withShape(shape)
                .build();

        this.addBeet(beet);
    }

    public void createBeetInTunnel(Name name, Shape shape, UUID tunnelId) {
        TunnelTemplate tunnel = this.getTunnelById(tunnelId);

        if (shape.doesNotFitInto(tunnel)) {
            throw new ChildDoesNotFitException(shape,this,"Position and dimension of the beet's shape exceed the acker's dimensions.");
        }

        List<Collidable> collidables = tunnel.getCollidables();
        if (!shape.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(shape, collidables, "The beet's shape collides with other beete.");
        }

        BeetTemplate beet = new BeetTemplateFactory()
                .withTunnelId(tunnelId)
                .withName(name)
                .withShape(shape)
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
        // collision and inbound validation in TunnelTemplate --> shall I put it here?
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
        if (tunnelId != null) {
            TunnelTemplate tunnel = this.getTunnelById(tunnelId);
            //TODO Validate tunnel_shape can hold beet_shape at new position.
        } else {
            // TODO Validate acker_shape can hold beet_shape at new position.
        }
        beet.moveToPosition(position);
    }

    public void moveTunnelToPosition(UUID tunnelId, Point position) {
        TunnelTemplate tunnel = this.getTunnelById(tunnelId);
        //TODO Validate acker_shape can hold tunnel_shape
        tunnel.moveToPosition(position);
    }

    public void changeName(Name name) {
        this.name = name;
    }

    public void changeDimension(double x, double y) {
        //TODO validate that new acker_shape can hold its elements
        Shape redimensionedShape = new Rectangle(x,y,this.shape.getPosition());

        List<Collidable> collidables = this.getCollidables();
        /*if (!shape.collidesWith(collidables).isEmpty()) {
            throw new CollisionException(shape, collidables, "The beet's shape collides with other beete.");
        }*/

        //TODO end. I did already think of checking that every collidable in acker fits into shape.

        this.shape = redimensionedShape;
    }

    public void changeTunnelDimension(double x, double y, UUID tunnelId) {
        //TODO
    }

    public void changeBeetDimension(double x, double y, UUID beetId) {
        //TODO
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
