package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Acker;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Tunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class BeetTemplate extends ABeet {
    private LocalDateTime createdAt;

    protected BeetTemplate() {}
    public BeetTemplate(Name name, Tunnel tunnel, Acker acker, LocalDateTime createdAt) {
        this.name = name;
        this.tunnel = tunnel;
        this.acker = acker;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BeetTemplate{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", beetId='").append(beetId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", shape=").append(shape);
        sb.append(", acker=").append(acker);
        sb.append(", tunnel=").append(tunnel);
        sb.append('}');
        return sb.toString();
    }
}