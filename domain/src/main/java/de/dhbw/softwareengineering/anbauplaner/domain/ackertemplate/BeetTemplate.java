package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BeetTemplate extends ABeet {
    private LocalDateTime createdAt;

    protected BeetTemplate() {
        this.createdAt = LocalDateTime.now();
    }

    public BeetTemplate(Name name, Shape shape, TunnelTemplate tunnel, AckerTemplate acker) {
        super(name,shape,acker,tunnel);
        this.createdAt = LocalDateTime.now();
    }

    protected LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    protected UUID getBeetId() {
        return super.getBeetId();
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
    protected ATunnel getTunnel() {
        return super.getTunnel();
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
    protected void setTunnel(ATunnel tunnel) {
        super.setTunnel(tunnel);
    }

    @Override
    protected void setAcker(AAcker acker) {
        super.setAcker(acker);
    }

    @Override
    protected void removeTunnel() {
        super.removeTunnel();
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
