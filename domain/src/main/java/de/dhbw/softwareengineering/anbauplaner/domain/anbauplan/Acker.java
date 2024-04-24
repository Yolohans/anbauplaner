package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.*;

@Entity
public class Acker extends AAcker {
    @ManyToOne
    @JoinColumn(name = "anbauplanId")
    protected Anbauplan anbauplan;

    protected Acker() {}

    protected Acker(Name name, Shape shape, Anbauplan anbauplan) {
        super(name,shape);
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
        sb.append(", ackerId='").append(this.getAckerId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", tunnels=").append(this.getTunnels());
        sb.append(", beete=").append(this.getBeete());
        sb.append('}');
        return sb.toString();
    }
}
