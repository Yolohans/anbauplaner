package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Tunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

public class BeetTemplateMockBuilder {

    private class BeetTemplateCopy extends BeetTemplate{
        @Override
        public UUID getBeetId() {
            return super.getBeetId();
        }

        @Override
        public Name getName() {
            return super.getName();
        }

        @Override
        public Shape getShape() {
            return super.getShape();
        }

        @Override
        public UUID getAckerId() {
            return super.getAckerId();
        }

        @Override
        public UUID getTunnelId() {
            return super.getTunnelId();
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
        protected LocalDateTime getCreatedAt() {
            return super.getCreatedAt();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private BeetTemplateCopy mockBeetTemplate;

    public BeetTemplateMockBuilder() {
        mockBeetTemplate = Mockito.mock(BeetTemplateCopy.class);
    }

    public BeetTemplateMockBuilder withBeetTemplateId(UUID beetId) {
        Mockito.when(mockBeetTemplate.getBeetId()).thenReturn(beetId);
        return this;
    }

    public BeetTemplateMockBuilder withName(Name name) {
        Mockito.when(mockBeetTemplate.getName()).thenReturn(name);
        return this;
    }

    public BeetTemplateMockBuilder withShape(Shape shape) {
        Mockito.when(mockBeetTemplate.getShape()).thenReturn(shape);
        return this;
    }

    public BeetTemplateMockBuilder withAckerId(UUID ackerId) {
        Mockito.when(mockBeetTemplate.getAckerId()).thenReturn(ackerId);
        return this;
    }

    public BeetTemplateMockBuilder withTunnelId(UUID tunnelId) {
        Mockito.when(mockBeetTemplate.getTunnelId()).thenReturn(tunnelId);
        return this;
    }

    public BeetTemplateCopy build() {
        return mockBeetTemplate;
    }
}

