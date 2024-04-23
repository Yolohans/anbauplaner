package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AckerTemplateRepository {
    List<AckerTemplate> findAllAckerTemplates();

    Optional<AckerTemplate> findAckerTemplateById(UUID ackerTemplateId);

    AckerTemplate save(AckerTemplate ackerTemplate);
}
