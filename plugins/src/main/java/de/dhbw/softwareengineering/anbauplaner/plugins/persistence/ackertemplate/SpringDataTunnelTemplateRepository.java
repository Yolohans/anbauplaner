package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.ackertemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackertemplate.TunnelTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataTunnelTemplateRepository extends JpaRepository<TunnelTemplate, UUID> {
}
