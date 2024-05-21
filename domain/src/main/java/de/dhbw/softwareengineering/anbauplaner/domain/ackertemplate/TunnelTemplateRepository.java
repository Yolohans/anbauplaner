package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TunnelTemplateRepository {

    List<TunnelTemplate> findAllTunnelTemplates();

    Optional<TunnelTemplate> findTunnelTemplateById(UUID tunnelTemplateId);

    TunnelTemplate save(TunnelTemplate tunnelTemplate);

    List<TunnelTemplate> findTunnelTemplatesByAckerId(UUID ackerTemplateId);

    void deleteById(UUID tunnelTemplateId);
}