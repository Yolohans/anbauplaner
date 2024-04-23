package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Acker;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class TunnelTemplate extends ATunnel {
    private LocalDateTime createdAt;

    protected TunnelTemplate() {
    }

    protected TunnelTemplate(String id, Name name, Acker acker, LocalDateTime createdAt) {
        this.tunnelId = id;
        this.name = name;
        this.acker = acker;
        this.Beete = new ArrayList<ABeet>(); //TODO
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TunnelTemplate{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", tunnelId='").append(tunnelId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", shape=").append(shape);
        sb.append(", acker=").append(acker);
        sb.append(", Beete=").append(Beete);
        sb.append('}');
        return sb.toString();
    }
}
