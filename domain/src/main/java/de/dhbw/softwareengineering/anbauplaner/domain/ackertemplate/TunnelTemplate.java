package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Entity
public class TunnelTemplate extends ATunnel {
    private LocalDateTime createdAt;

    protected TunnelTemplate() {
    }

    protected TunnelTemplate(Name name, Shape shape, AckerTemplate acker) {
        super(name,shape,acker);
        this.createdAt = LocalDateTime.now();
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    protected UUID getTunnelId() {
        return super.getTunnelId();
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
    public HashMap<UUID, ABeet> getBeete() {
        return super.getBeete();
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
    protected void setAcker(AAcker acker) {
        super.setAcker(acker);
    }

    @Override
    protected void setBeete(HashMap<UUID, ABeet> beete) {
        super.setBeete(beete);
    }

    @Override
    public void add(ABeet beet) {
        super.add(beet);
    }

    @Override
    public void remove(ABeet beet) {
        super.remove(beet);
    }

    @Override
    public void remove(UUID beetId) {
        super.remove(beetId);
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
