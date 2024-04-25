package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.AAcker;
import de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction.ATunnel;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

public class BeetTemplateMockBuilder {

    private class BeetTemplateCopy extends BeetTemplate{
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

    public BeetTemplateMockBuilder withAcker(AAcker acker) {
        Mockito.when(mockBeetTemplate.getAcker()).thenReturn(acker);
        return this;
    }

    public BeetTemplateMockBuilder withTunnel(ATunnel tunnel) {
        Mockito.when(mockBeetTemplate.getTunnel()).thenReturn(tunnel);
        return this;
    }

    public BeetTemplateCopy build() {
        return mockBeetTemplate;
    }
}

