package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Beet extends ABeet {
    @OneToMany
    private List<Belegung> Belegungen;

    protected Beet(){};

    protected Beet(Name name, Shape shape, Acker acker, Tunnel tunnel) {
        super(name, shape, acker, tunnel);
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

    @Override
    protected UUID getBeetId() {
        return super.getBeetId();
    }

    @Override
    protected Name getName() {
        return super.getName();
    }

    @Override
    protected Shape getShape() {
        return super.getShape();
    }

    @Override
    protected AAcker getAcker() {
        return super.getAcker();
    }

    @Override
    protected ATunnel getTunnel() {
        return super.getTunnel();
    }

    @Override
    protected void setName(Name name) {
        super.setName(name);
    }

    @Override
    protected void setShape(Shape shape) {
        super.setShape(shape);
    }

    @Override
    protected void setTunnel(ATunnel tunnel) {
        super.setTunnel(tunnel);
    }

    @Override
    protected void setAcker(AAcker acker) {
        super.setAcker(acker);
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
