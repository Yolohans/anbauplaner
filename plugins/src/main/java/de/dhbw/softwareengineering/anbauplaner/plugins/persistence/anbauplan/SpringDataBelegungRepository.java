package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Belegung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBelegungRepository extends JpaRepository<Belegung, UUID> {
}