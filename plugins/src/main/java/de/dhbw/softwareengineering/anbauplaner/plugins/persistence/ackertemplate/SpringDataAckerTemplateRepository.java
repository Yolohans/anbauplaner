package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.ackertemplate;


import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.AckerTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataAckerTemplateRepository extends JpaRepository<AckerTemplate, UUID> {
}