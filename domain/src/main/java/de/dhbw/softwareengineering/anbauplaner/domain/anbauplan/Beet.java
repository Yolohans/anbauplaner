package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Beet {
    @Id
    @GeneratedValue
    private UUID beetId;
    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="shapeId", referencedColumnName = "shapeId")
    private Shape shape;
    @ManyToOne
    @JoinColumn(name = "ackerId")
    private Acker acker;
    @ManyToOne
    @JoinColumn(name = "tunnelId")
    private Tunnel tunnel;
    @OneToMany
    private List<Belegung> Belegungen;

    protected Beet(){};

    protected Beet(Name name, Shape shape, Acker acker, Tunnel tunnel) {
        this.name = name;
        this.shape = shape;
        this.acker = acker;
        this.tunnel = tunnel;
        this.Belegungen = new ArrayList<Belegung>();
    }

    protected List<Belegung> getBelegungen() {
        return Belegungen;
    }

    protected void setBelegungen(List<Belegung> belegungen) {
        Belegungen = belegungen;
    }

    protected void addBelegung(Belegung belegung) {
        this.Belegungen.add(belegung);
    }

    protected void removeBelegung(Belegung belegung) {
        this.Belegungen.remove(belegung);
    }

    public UUID getBeetId() {
        return beetId;
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

    public Tunnel getTunnel() {
        return tunnel;
    }

    //TODO is this the way to remove tunnel?
    // if every position is relative, must adjust shape --> new position
    protected void removeTunnel() {
        // more logic needed for shape of beet
        this.tunnel = null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Beet{");
        sb.append("Belegungen=").append(Belegungen);
        sb.append(", beetId='").append(this.getBeetId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", acker=").append(this.getAcker());
        sb.append(", tunnel=").append(this.getTunnel());
        sb.append('}');
        return sb.toString();
    }
}
