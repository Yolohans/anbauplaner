package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ABeet;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class TunnelTemplateMockBuilder {

    private class TunnelTemplateCopy extends TunnelTemplate {
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
        protected LocalDateTime getCreatedAt() {
            return super.getCreatedAt();
        }

    }

    private TunnelTemplateCopy mockTunnelTemplate;

    public TunnelTemplateMockBuilder() {
        mockTunnelTemplate = Mockito.mock(TunnelTemplateCopy.class);
    }

    public TunnelTemplateMockBuilder withTunnelTemplateId(UUID tunnelId) {
        Mockito.when(mockTunnelTemplate.getTunnelId()).thenReturn(tunnelId);
        return this;
    }

    public TunnelTemplateMockBuilder withName(Name name) {
        Mockito.when(mockTunnelTemplate.getName()).thenReturn(name);
        return this;
    }

    public TunnelTemplateMockBuilder withCreatedAt(LocalDateTime createdAt) {
        Mockito.when(mockTunnelTemplate.getCreatedAt()).thenReturn(createdAt);
        return this;
    }

    public TunnelTemplateMockBuilder withBeete(BeetTemplate... beete) {
        HashMap<UUID, ABeet> beetMap = new HashMap<>();
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

