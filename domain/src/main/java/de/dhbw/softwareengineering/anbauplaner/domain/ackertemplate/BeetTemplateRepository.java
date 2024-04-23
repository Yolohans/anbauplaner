package de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeetTemplateRepository {
    List<BeetTemplate> findAllBeetTemplates();

    Optional<BeetTemplate> findBeetTemplateById(UUID beetTemplateId);

    BeetTemplate save(BeetTemplate beetTemplate);
}
