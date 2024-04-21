package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Beet extends ABeet {
    @OneToMany
    private List<Belegung> Belegungen;
    protected Beet(){};
    protected Beet(Name name, Tunnel tunnel, Acker acker, List<Belegung> belegungen) {
        this.name = name;
        this.acker = acker;
        this.tunnel = tunnel;
        this.Belegungen = belegungen;
    }
    protected List<Belegung> getBelegungen() {
        return Belegungen;
    }
    protected void setBelegungen(List<Belegung> belegungen) {
        Belegungen = belegungen;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Beet{");
        sb.append("Belegungen=").append(Belegungen);
        sb.append(", beetId='").append(beetId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", shape=").append(shape);
        sb.append(", acker=").append(acker);
        sb.append(", tunnel=").append(tunnel);
        sb.append('}');
        return sb.toString();
    }
}
