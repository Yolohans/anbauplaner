package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Entity
public class AckerTemplate {

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

    @ElementCollection
    @CollectionTable(name = "beet_id_to_tunnel_id_map", joinColumns = @JoinColumn(name = "tunnel_id"))
    @MapKeyColumn(name = "beet_id")
    @Column(name = "tunnel_id")
    private HashMap<UUID,UUID> beetIdToTunnelIdMap;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateAt;
    private Boolean collisionManagementActive;

    public AckerTemplate(Name name, Shape shape) {
        this.name = name;
        this.shape = shape;
        this.tunnels = new HashMap<UUID,TunnelTemplate>();
        this.beete = new HashMap<UUID,BeetTemplate>();
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
        TunnelTemplate tunnel = new TunnelTemplate(name, shape, this.getAckerId());
        this.addTunnel(tunnel);
    }

    public void createBeetInAcker(Name name, Shape shape) {
        BeetTemplate beet = new BeetTemplateFactory()
                .withAcker(this.getAckerId())
                .withName(name)
                .withShape(shape)
                .build();
        this.addBeet(beet);
    }

    public void createBeetInTunnel(Name name, Shape shape, UUID tunnelId) {
        BeetTemplate beet = new BeetTemplateFactory()
                .withTunnel(tunnelId)
                .withName(name)
                .withShape(shape)
                .build();
        this.getTunnelById(tunnelId).add(beet);
    }

    public void deleteBeet(UUID beetId) {
        UUID tunnelId = this.getTunnelIdByBeetId(beetId);

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

    public void attachBeetToTunnelAtPosition(UUID beetId, UUID tunnelId, Point position) {
        //--> collision management
        BeetTemplate beet = this.getBeetById(beetId);
        TunnelTemplate formerTunnel = this.getTunnelById(this.getTunnelIdByBeetId(beetId));
        TunnelTemplate newTunnel = this.getTunnelById(tunnelId);

        if (newTunnel != null && beet != null) {
            if (formerTunnel != null) {
                formerTunnel.removeBeetById(beetId);
            }
            newTunnel.add(beet);
            beet.attachToTunnel(newTunnel, position);
        }
    }

    public void moveBeetToPosition(UUID beetId, Point position) {
        //collision --> in acker
        //outofbounds --> in tunnel
    }

    public void moveTunnelToPosition(UUID tunnelId, Point position) {
        //collision --> in acker
        //outofbounds --> in tunnel
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

    public UUID getAckerId() {
        return ackerId;
    }

    public Name getName() {
        return name;
    }

    public Shape getShape() {
        return shape;
    }

    public TunnelTemplate getTunnelById(UUID tunnelId) {
        return this.getTunnels().get(tunnelId);
    }

    public BeetTemplate getBeetById(UUID beetId) {
        return this.getBeete().get(beetId);
    }

    public UUID getTunnelIdByBeetId(UUID beetId){
        return this.beetIdToTunnelIdMap.get(beetId);
    }

    public HashMap<UUID, TunnelTemplate> getTunnels() {
        return tunnels;
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

    protected void addBeet(BeetTemplate beet) {
        this.getBeete().put(beet.getBeetId(), beet);
    }

    protected void addTunnel(TunnelTemplate tunnel) {
        this.getTunnels().put(tunnel.getTunnelId(), tunnel);
    }

    protected void removeBeetById(UUID beetId) {
        this.getBeete().remove(beetId);
    }

    private void removeTunnelById(UUID tunnelId) {

    }

    protected void setCollisionManagementActive(Boolean collisionManagementActive) {
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
