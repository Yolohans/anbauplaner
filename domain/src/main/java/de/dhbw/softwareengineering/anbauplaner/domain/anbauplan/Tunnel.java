package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import jakarta.persistence.Entity;

import java.util.ArrayList;

@Entity
public class Tunnel extends ATunnel {
    public Tunnel() {}
    protected Tunnel(String id, Name name, Acker acker) {
        this.tunnelId = id;
        this.name = name;
        this.acker = acker;
        this.Beete = new ArrayList<ABeet>(); //TODO
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tunnel{");
        sb.append("tunnelId='").append(tunnelId).append('\'');
        sb.append(", name=").append(name);
        sb.append(", shape=").append(shape);
        sb.append(", acker=").append(acker);
        sb.append(", Beete=").append(Beete);
        sb.append('}');
        return sb.toString();
    }
}
