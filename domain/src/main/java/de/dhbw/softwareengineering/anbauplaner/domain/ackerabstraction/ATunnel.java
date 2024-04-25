package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    protected HashMap<UUID, ABeet> beete;

    protected ATunnel() {}

    protected ATunnel(Name name, Shape shape, AAcker acker) {
        this.name = name;
        this.shape = shape;
        this.acker = acker;
        this.beete = new HashMap<UUID, ABeet>();
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

    public HashMap<UUID, ABeet> getBeete() {
        return beete;
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

    protected void setBeete(HashMap<UUID, ABeet> beete) {
        this.beete = beete;
    }

    public void add(ABeet beet) {
        this.beete.put(beet.getBeetId(),beet);
    }

    public void remove(ABeet beet) {
        this.beete.remove(beet.getBeetId());
    }

    public void remove(UUID beetId) {
        this.beete.remove(beetId);
    }

    @Override
    public abstract String toString();


}
