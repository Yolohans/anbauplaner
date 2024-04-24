package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Acker;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Tunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class BeetTemplate extends ABeet {
    private LocalDateTime createdAt;

    protected BeetTemplate() {}
    public BeetTemplate(Name name, Shape shape, Tunnel tunnel, Acker acker) {
        super(name,shape,acker,tunnel);
        this.createdAt = LocalDateTime.now();
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BeetTemplate{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", beetId='").append(this.getBeetId()).append('\'');
        sb.append(", name=").append(this.getName());
        sb.append(", shape=").append(this.getShape());
        sb.append(", acker=").append(this.getAcker());
        sb.append(", tunnel=").append(this.getTunnel());
        sb.append('}');
        return sb.toString();
    }
}
