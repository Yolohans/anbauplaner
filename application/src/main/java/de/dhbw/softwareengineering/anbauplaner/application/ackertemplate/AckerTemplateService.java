package de.dhbw.softwareengineering.anbauplaner.application.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplateRepository;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplateRepository;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AckerTemplateService {

    private final AckerTemplateRepository ackerTemplateRepository;
    private final TunnelTemplateRepository tunnelTemplateRepository;

    @Autowired
    public AckerTemplateService(final AckerTemplateRepository ackerTemplateRepository, TunnelTemplateRepository tunnelTemplateRepository) {
        this.ackerTemplateRepository = ackerTemplateRepository;
        this.tunnelTemplateRepository = tunnelTemplateRepository;
    }

    public List<AckerTemplate> findAllAckerTemplates() {
        return this.ackerTemplateRepository.findAllAckerTemplates();
    }

    public AckerTemplate findAckerTemplateById(UUID ackerId) {
        return this.ackerTemplateRepository.findAckerTemplateById(ackerId).orElseThrow(() -> new EntityNotFoundException("AckerTemplate with id " + ackerId + " does not exist"));
    }

    public AckerTemplate createAcker(String name, double xLength, double yLength, double posX, double posY) {
        Point position = new Point(posX, posY);
        Rectangle rectangle = new Rectangle(xLength, yLength, position);
        AckerTemplate acker = new AckerTemplate(new Name(name), rectangle);
        return this.ackerTemplateRepository.save(acker);
    }

    public AckerTemplate changeAckerDimensions(UUID ackerId, double x, double y) {
        AckerTemplate acker = findAckerTemplateById(ackerId);
        acker.changeAckerDimension(x, y);
        acker = this.ackerTemplateRepository.save(acker);
        return acker;
    }

    public void deleteAckerTemplateById(UUID ackerId) {
        AckerTemplate acker = findAckerTemplateById(ackerId);
        this.ackerTemplateRepository.deleteById(ackerId);
    }

    public List<TunnelTemplate> findTunnelTemplatesByAckerId(UUID ackerId) {
        return this.tunnelTemplateRepository.findTunnelTemplatesByAckerId(ackerId);
    }

    public TunnelTemplate findTunnelTemplateById(UUID tunnelId) {
        return this.tunnelTemplateRepository.findTunnelTemplateById(tunnelId).orElseThrow(() -> new EntityNotFoundException("AckerTemplate with id " + tunnelId + " does not exist"));
    }

    public TunnelTemplate createTunnel(UUID ackerId, String name, double xLength, double yLength, double posX, double posY) {
        AckerTemplate acker = findAckerTemplateById(ackerId);

        Point position = new Point(posX, posY);
        Rectangle rectangle = new Rectangle(xLength, yLength, position);
        TunnelTemplate tunnel = acker.createTunnel(new Name(name), rectangle);

        return this.tunnelTemplateRepository.save(tunnel);
    }

    public void deleteTunnelById(UUID tunnelId, boolean keepBeete) {
        TunnelTemplate tunnel = findTunnelTemplateById(tunnelId);
        AckerTemplate acker = findAckerTemplateById(tunnel.getAckerId());

        acker.deleteTunnel(tunnelId,keepBeete);
        if (!keepBeete) {
            // not implemented -> call beetTemplateRepository.deleteAllBeeteByTunnelId(...)
        }
        this.tunnelTemplateRepository.deleteById(tunnelId);
        return;
    }

    public TunnelTemplate moveTunnelToPosition(UUID ackerId, UUID tunnelId, Double posX, Double posY) {
        AckerTemplate acker = findAckerTemplateById(ackerId);

        Point position = new Point(posX, posY);
        TunnelTemplate tunnel = acker.moveTunnelToPosition(tunnelId, position);
        return tunnel;
    }
}