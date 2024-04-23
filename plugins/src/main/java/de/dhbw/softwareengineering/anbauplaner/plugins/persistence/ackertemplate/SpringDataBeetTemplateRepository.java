package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.BeetTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBeetTemplateRepository extends JpaRepository<BeetTemplate, UUID> {
}