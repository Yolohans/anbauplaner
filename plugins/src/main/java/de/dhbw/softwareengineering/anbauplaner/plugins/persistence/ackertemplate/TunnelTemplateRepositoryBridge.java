package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TunnelTemplateRepositoryBridge implements TunnelTemplateRepository {
    private final SpringDataTunnelTemplateRepository springDataTunnelTemplateRepository;

    @Autowired
    public TunnelTemplateRepositoryBridge(final SpringDataTunnelTemplateRepository springDataTunnelTemplateRepository) {
        this.springDataTunnelTemplateRepository = springDataTunnelTemplateRepository;
    }

    @Override
    public List<TunnelTemplate> findAllTunnelTemplates() {
        return this.springDataTunnelTemplateRepository.findAll();
    }

    @Override
    public Optional<TunnelTemplate> findTunnelTemplateById(final UUID tunnelTemplateId) {
        return this.springDataTunnelTemplateRepository.findById(tunnelTemplateId);
    }

    @Override
    public TunnelTemplate save(final TunnelTemplate tunnelTemplate) {
        return this.springDataTunnelTemplateRepository.save(tunnelTemplate);
    }
}