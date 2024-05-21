package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TunnelTemplateMockBuilder {

    private class TunnelTemplateCopy extends TunnelTemplate {
        @Override
        public UUID getTunnelId() {
            return super.getTunnelId();
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
        public Map<UUID, BeetTemplate> getBeete() {
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
        protected LocalDateTime getCreatedAt() {
            return super.getCreatedAt();
        }
    }

    private TunnelTemplateCopy mockTunnelTemplate;

    public TunnelTemplateMockBuilder() {
        mockTunnelTemplate = Mockito.mock(TunnelTemplateCopy.class);
    }

    public TunnelTemplateMockBuilder withAckerId(UUID ackerId) {
        Mockito.when(mockTunnelTemplate.getAckerId()).thenReturn(ackerId);
        return this;
    }

    public TunnelTemplateMockBuilder withTunnelId(UUID tunnelId) {
        Mockito.when(mockTunnelTemplate.getTunnelId()).thenReturn(tunnelId);
        return this;
    }

    public TunnelTemplateMockBuilder withName(Name name) {
        Mockito.when(mockTunnelTemplate.getName()).thenReturn(name);
        return this;
    }

    public TunnelTemplateMockBuilder withShape(Shape shape) {
        Mockito.when(mockTunnelTemplate.getShape()).thenReturn(shape);
        return this;
    }

    public TunnelTemplateMockBuilder withCreatedAt(LocalDateTime createdAt) {
        Mockito.when(mockTunnelTemplate.getCreatedAt()).thenReturn(createdAt);
        return this;
    }

    public TunnelTemplateMockBuilder withBeete(BeetTemplate... beete) {
        HashMap<UUID, BeetTemplate> beetMap = new HashMap<>();
        for (BeetTemplate beet : beete) {
            beetMap.put(UUID.randomUUID(), beet);
        }
        Mockito.when(mockTunnelTemplate.getBeete()).thenReturn(beetMap);
        return this;
    }

    public TunnelTemplateCopy build() {
        return mockTunnelTemplate;
    }
}

