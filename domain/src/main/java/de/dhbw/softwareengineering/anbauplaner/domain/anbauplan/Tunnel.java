package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.UUID;

@Entity
public class Tunnel {
    @Id
    @GeneratedValue
    private UUID tunnelId;

    @Convert(converter = NameAttributeConverter.class)
    private Name name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;

    @ManyToOne
    @JoinColumn(name = "ackerId")
    private Acker acker;

    @OneToMany(mappedBy = "tunnel")
    protected HashMap<UUID, Beet> beete;

    protected Tunnel() {}
    protected Tunnel(Name name, Shape shape, Acker acker) {
        this.name = name;
        this.shape = shape;
        this.acker = acker;
        this.beete = new HashMap<UUID, Beet>();
    }

    public UUID getTunnelId() {
        return tunnelId;
    }

    public Name getName() {
        return name;
    }

    public Shape getShape() {
        return shape;
    }

    public Acker getAcker() {
        return acker;
    }

    public HashMap<UUID, Beet> getBeete() {
        return beete;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    protected void setAcker(Acker acker) {
        this.acker = acker;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tunnel{");
        sb.append("tunnelId='").append(this.getTunnelId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", acker=").append(this.getAcker());
        sb.append(", Beete=").append(beete);
        sb.append('}');
        return sb.toString();
    }
}
