package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks.BeetTemplateMockBuilder;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.mocks.TunnelTemplateMockBuilder;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.ChildDoesNotFitException;
import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.CollisionException;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AckerTemplateTunnelCreationTests {


    private AckerTemplate ackerTemplate;
    private UUID ackerId = UUID.randomUUID();
    private UUID tunnelId = UUID.randomUUID();
    private UUID beetId = UUID.randomUUID();
    private BeetTemplate beetTemplateMock;
    private TunnelTemplate tunnelTemplateMock;
    private BeetTemplate innerBeetMock;
    private Name ackerName = new Name("AckerName");
    private Shape ackerShape = new Rectangle(1000.,1000., new Point(0.,0.));


    @BeforeEach
    public void setup() {
        beetTemplateMock = new BeetTemplateMockBuilder()
                .withAckerId(ackerId)
                .withBeetId(beetId)
                .withShape(new Rectangle(450.,450.,new Point(10.,10.)))
                .build();

        tunnelTemplateMock = new TunnelTemplateMockBuilder()
                .withAckerId(ackerId)
                .withTunnelId(tunnelId)
                .withShape(new Rectangle(450., 450., new Point(500.,10.)))
                .build();

        ackerTemplate = new AckerTemplate(this.ackerName, this.ackerShape);
        ackerTemplate.getBeete().put(beetId,beetTemplateMock);
        ackerTemplate.getTunnels().put(tunnelId,tunnelTemplateMock);

    }

    @Test
    void testAckerConstructor() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(this.ackerName, ackerTemplate.getName()),
                () -> Assertions.assertEquals(this.ackerShape, ackerTemplate.getShape())
        );
    }

    @Test
    public void testCreateTunnelDoesNotFit() {
        Assertions.assertThrows(ChildDoesNotFitException.class, () -> ackerTemplate.createTunnel(new Name("Name"),new Rectangle(510.,510.,new Point(500., 500.))));
    }

    @Test
    public void testCreateTunnelCollides_withTunnel() {
        Assertions.assertThrows(CollisionException.class, () -> ackerTemplate.createTunnel(new Name("Name"),new Rectangle(100.,100.,new Point(800., 10.))));
    }
}

