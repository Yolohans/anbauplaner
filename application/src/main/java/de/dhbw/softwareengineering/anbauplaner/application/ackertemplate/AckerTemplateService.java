package de.dhbw.softwareengineering.anbauplaner.application.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AckerTemplateService {

    private final AckerTemplateRepository ackerTemplateRepository;

    @Autowired
    public AckerTemplateService(final AckerTemplateRepository ackerTemplateRepository) {
        this.ackerTemplateRepository = ackerTemplateRepository;
    }

    public List<AckerTemplate> findAllAckerTemplates() {
        return this.ackerTemplateRepository.findAllAckerTemplates();
    }

    public Optional<AckerTemplate> findAckerTemplateById(UUID ackerTemplateId) {
        return this.ackerTemplateRepository.findAckerTemplateById(ackerTemplateId);
    }

    public AckerTemplate save(AckerTemplate ackerTemplate) {
        return this.ackerTemplateRepository.save(ackerTemplate);
    }
}