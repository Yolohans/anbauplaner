package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Acker;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class TunnelTemplate extends ATunnel {
    private LocalDateTime createdAt;

    protected TunnelTemplate() {
    }

    protected TunnelTemplate(Name name, Shape shape, Acker acker) {
        super(name,shape,acker);
        this.Beete = new ArrayList<ABeet>();
        this.createdAt = LocalDateTime.now();
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TunnelTemplate{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", tunnelId='").append(this.getTunnelId()).append('\'');
        sb.append(", name=").append(this.getTunnelId());
        sb.append(", shape=").append(this.getShape());
        sb.append(", acker=").append(this.getAcker());
        sb.append(", Beete=").append(this.getBeete());
        sb.append('}');
        return sb.toString();
    }
}
