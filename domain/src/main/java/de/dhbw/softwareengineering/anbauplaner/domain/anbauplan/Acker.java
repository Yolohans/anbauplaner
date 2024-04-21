package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

@Entity
public class Acker extends AAcker {
    @ManyToOne
    @JoinColumn(name = "anbauplanId")
    protected Anbauplan anbauplan;

    protected Acker() {}

    protected Acker(Name name, Shape shape, Anbauplan anbauplan) {
        this.name = name;
        this.shape = shape;
        this.anbauplan = anbauplan;
    }

    protected Anbauplan getAnbauplan() {
        return anbauplan;
    }

    private void setAnbauplan(Anbauplan anbauplan) {
        this.anbauplan = anbauplan;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acker{");
        sb.append("anbauplan=").append(anbauplan);
        sb.append(", ackerId='").append(ackerId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", shape=").append(shape);
        sb.append(", tunnels=").append(tunnels);
        sb.append(", beete=").append(beete);
        sb.append('}');
        return sb.toString();
    }
}
