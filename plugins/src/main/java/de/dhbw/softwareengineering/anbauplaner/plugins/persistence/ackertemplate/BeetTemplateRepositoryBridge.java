package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BeetTemplateRepositoryBridge implements BeetTemplateRepository {
    private final SpringDataBeetTemplateRepository springDataBeetTemplateRepository;

    @Autowired
    public BeetTemplateRepositoryBridge(final SpringDataBeetTemplateRepository springDataBeetTemplateRepository) {
        this.springDataBeetTemplateRepository = springDataBeetTemplateRepository;
    }

    @Override
    public List<BeetTemplate> findAllBeetTemplates() {
        return this.springDataBeetTemplateRepository.findAll();
    }

    @Override
    public Optional<BeetTemplate> findBeetTemplateById(final UUID beetTemplateId) {
        return this.springDataBeetTemplateRepository.findById(beetTemplateId);
    }

    @Override
    public BeetTemplate save(final BeetTemplate beetTemplate) {
        return this.springDataBeetTemplateRepository.save(beetTemplate);
    }
}