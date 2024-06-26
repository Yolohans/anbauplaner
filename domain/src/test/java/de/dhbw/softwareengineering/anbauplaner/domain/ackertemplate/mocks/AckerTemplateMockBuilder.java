package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class AckerTemplateMockBuilder {

    private AckerTemplate mockAckerTemplate;

    public AckerTemplateMockBuilder() {
        mockAckerTemplate = Mockito.mock(AckerTemplate.class);
    }

    public AckerTemplateMockBuilder withAckerId(UUID ackerId) {
        Mockito.when(mockAckerTemplate.getAckerId()).thenReturn(ackerId);
        return this;
    }

    public AckerTemplateMockBuilder withName(Name name) {
        Mockito.when(mockAckerTemplate.getName()).thenReturn(name);
        return this;
    }

    public AckerTemplateMockBuilder withShape(Shape shape) {
        Mockito.when(mockAckerTemplate.getShape()).thenReturn(shape);
        return this;
    }

    public AckerTemplateMockBuilder withTunnels(TunnelTemplate... tunnels) {
        HashMap<UUID, TunnelTemplate> tunnelMap = new HashMap<>();
        for (TunnelTemplate tunnel : tunnels) {
            tunnelMap.put(UUID.randomUUID(), tunnel);
        }
        Mockito.when(mockAckerTemplate.getTunnels()).thenReturn(tunnelMap);
        return this;
    }

    public AckerTemplateMockBuilder withBeete(BeetTemplate... beete) {
        HashMap<UUID, BeetTemplate> beetMap = new HashMap<>();
        for (BeetTemplate beet : beete) {
            beetMap.put(UUID.randomUUID(), beet);
        }
        Mockito.when(mockAckerTemplate.getBeete()).thenReturn(beetMap);
        return this;
    }

    public AckerTemplateMockBuilder withCreatedAt(LocalDateTime createdAt) {
        Mockito.when(mockAckerTemplate.getCreatedAt()).thenReturn(createdAt);
        return this;
    }

    public AckerTemplateMockBuilder withLastUpdateAt(LocalDateTime lastUpdateAt) {
        Mockito.when(mockAckerTemplate.getLastUpdateAt()).thenReturn(lastUpdateAt);
        return this;
    }

    public AckerTemplate build() {
        return mockAckerTemplate;
    }
}

