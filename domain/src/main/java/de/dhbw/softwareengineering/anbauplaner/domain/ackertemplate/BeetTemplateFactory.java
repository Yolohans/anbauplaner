package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;

import java.util.UUID;

public class BeetTemplateFactory {
    private BeetTemplate beet;

    public BeetTemplateFactory() {
        this.beet = new BeetTemplate();
    }

    public BeetTemplateFactory withAckerId(UUID ackerId) {
        this.beet.setAckerId(ackerId);
        return this;
    }

    public BeetTemplateFactory withTunnelId(UUID tunnelId) {
        this.beet.setTunnelId(tunnelId);
        return this;
    }

    public BeetTemplateFactory withName(Name name) {
        this.beet.setName(name);
        return this;
    }

    public BeetTemplateFactory withShape(Shape shape) {
        this.beet.setShape(shape);
        return this;
    }

    public BeetTemplate build() {
        return beet;
    }
}
