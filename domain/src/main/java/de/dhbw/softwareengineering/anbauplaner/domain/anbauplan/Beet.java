package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

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
