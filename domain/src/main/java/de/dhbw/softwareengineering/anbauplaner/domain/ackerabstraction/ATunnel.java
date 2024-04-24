package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ATunnel {

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
    private AAcker acker;

    @OneToMany(mappedBy = "tunnel")
    protected List<ABeet> Beete;

    protected ATunnel() {}

    protected ATunnel(Name name, Shape shape, AAcker acker) {
        this.name = name;
        this.shape = shape;
        this.acker = acker;
        Beete = new ArrayList<ABeet>();
    }

    protected UUID getTunnelId() {
        return tunnelId;
    }

    protected Name getName() {
        return name;
    }

    protected Shape getShape() {
        return shape;
    }

    protected AAcker getAcker() {
        return acker;
    }

    public List<ABeet> getBeete() {
        return Beete;
    }

    protected void setName(Name name) {
        this.name = name;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    protected void setAcker(AAcker acker) {
        this.acker = acker;
    }

    protected void setBeete(List<ABeet> beete) {
        Beete = beete;
    }

    protected boolean addBeet(ABeet beet) {
        return this.Beete.add(beet);
    }

    protected boolean removeBeet(ABeet beet) {
        return this.Beete.remove(beet);
    }

    @Override
    public abstract String toString();
}
