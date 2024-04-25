package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;

public class BeetTemplateFactory {
    private BeetTemplate beet;

    public BeetTemplateFactory() {
        this.beet = new BeetTemplate();
    }

    public BeetTemplateFactory withName(Name name) {
        this.beet.setName(name);
        return this;
    }

    public BeetTemplateFactory withShape(Shape shape) {
        this.beet.setShape(shape);
        return this;
    }

    public BeetTemplateFactory withTunnel(TunnelTemplate tunnel) {
        this.beet.setTunnel(tunnel);
        return this;
    }

    public BeetTemplateFactory withAcker(AckerTemplate acker) {
        this.beet.setAcker(acker);
        return this;
    }

    public BeetTemplate build() {
        return beet;
    }
}
