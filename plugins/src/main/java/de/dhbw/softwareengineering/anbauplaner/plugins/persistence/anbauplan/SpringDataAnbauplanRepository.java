package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Anbauplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataAnbauplanRepository extends JpaRepository<Anbauplan, UUID> {
}
