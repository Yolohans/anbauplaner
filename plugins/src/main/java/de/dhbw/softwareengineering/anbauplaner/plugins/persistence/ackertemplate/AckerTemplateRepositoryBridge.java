package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AckerTemplateRepositoryBridge implements AckerTemplateRepository {
    private final SpringDataAckerTemplateRepository springDataAckerTemplateRepository;

    @Autowired
    public AckerTemplateRepositoryBridge(final SpringDataAckerTemplateRepository springDataAckerTemplateRepository) {
        this.springDataAckerTemplateRepository = springDataAckerTemplateRepository;
    }

    @Override
    public List<AckerTemplate> findAllAckerTemplates() {
        return this.springDataAckerTemplateRepository.findAll();
    }

    @Override
    public Optional<AckerTemplate> findAckerTemplateById(final UUID ackerTemplateId) {
        return this.springDataAckerTemplateRepository.findById(ackerTemplateId);
    }

    @Override
    public AckerTemplate save(final AckerTemplate ackerTemplate) {
        return this.springDataAckerTemplateRepository.save(ackerTemplate);
    }

    @Override
    public void deleteById(final UUID ackerTemplateId) {
        this.springDataAckerTemplateRepository.deleteById(ackerTemplateId);
    }
}
